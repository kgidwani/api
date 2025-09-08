package com.bizenlabs.api.ollama;

import com.bizenlabs.api.platform.PlatformConfig;
import io.github.compress4j.archivers.ArchiveFormat;
import io.github.compress4j.archivers.Archiver;
import io.github.compress4j.archivers.ArchiverFactory;
import io.github.compress4j.archivers.CompressionType;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.CompletableFuture;


@Service
public class OllamaService {

    private static final String LATEST_RELEASE_BASE_URL = "https://github.com/ollama/ollama/releases/download/v0.11.10/";


    private final PlatformConfig platformConfig;

    @Autowired
    public OllamaService(final PlatformConfig platformConfig) {
        this.platformConfig = platformConfig;
    }

    /**
     * Ensures the zip entry is extracted safely (prevents Zip Slip vulnerability).
     */
    private static File newFile(File destinationDir, java.util.zip.ZipEntry zipEntry) throws java.io.IOException {
        File destFile = new File(destinationDir, zipEntry.getName());
        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();
        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            throw new java.io.IOException("Entry is outside of the target dir: " + zipEntry.getName());
        }
        return destFile;
    }

    public String downloadOllama() {

        String fileName = getAssetName();
        File downloadFilePath = new File(platformConfig.appDataDir, fileName);
        CompletableFuture.runAsync(() -> {
            try {
                URL ollamaReleaseUrl = new URI(LATEST_RELEASE_BASE_URL + fileName).toURL();
                URLConnection connection = ollamaReleaseUrl.openConnection();
                connection.setConnectTimeout(60000);
                connection.setReadTimeout(60000);
                FileUtils.copyURLToFile(
                        ollamaReleaseUrl,
                        downloadFilePath);

                File destination = new File(platformConfig.appDataDir + "/ollama");

                Archiver archiver = ArchiverFactory.createArchiver(ArchiveFormat.TAR, CompressionType.GZIP);
                archiver.extract(downloadFilePath, destination);
                System.out.println("Extracted to " + destination.getAbsolutePath());
                // set permission to execute of ollama binary
                File ollamaBinary = new File(destination, "ollama");
                if (ollamaBinary.exists()) {
                    var result = ollamaBinary.setExecutable(true);
                    System.out.println("Set executable permission: " + result);
                } else {
                    throw new RuntimeException("Ollama binary not found after extraction");
                }

//                new ZipFile(downloadFilePath).extractAll(platformConfig.appDataDir);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return downloadFilePath.getAbsolutePath();
    }


    private String getAssetName() {
        return switch (platformConfig.os) {
            case "darwin" -> "ollama-darwin.tgz";
            case "windows" -> "ollama-windows-" + platformConfig.arch + ".zip";
            default -> throw new RuntimeException("Unknown OS: " + platformConfig.os);
        };
    }

    public long startOllamaService() {
        try {
            Process p = new ProcessBuilder(platformConfig.appDataDir + "/ollama/ollama", "start").redirectErrorStream(true).start();
            System.out.println("Started ollama service with PID: " + p.pid());
            return p.pid();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}