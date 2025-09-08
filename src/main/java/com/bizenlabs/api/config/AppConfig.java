package com.bizenlabs.api.config;

import com.bizenlabs.api.platform.PlatformConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Value("${bizenlabs.app.data.dir}")
    private String appDataDir;

    @Bean
    public String getAppDataDir() {
        return appDataDir;
    }

    @Bean
    public PlatformConfig getPlatformConfig() {
        String osName = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();

        String os = "Unsupported platform";
        String architecture = "Unsupported architecture";

        if (osName.contains("win")) {
            os = "windows";
        } else if (osName.contains("mac") || osName.contains("darwin")) {
            os = "darwin";
        } else if (osName.contains("nux")) {
            os = "linux";
        }

        if (arch.contains("arm")) {
            architecture = "arm64";
        } else if (arch.contains("64")) {
            architecture = "amd64";
        }
        return new PlatformConfig(os, architecture, getAppDataDir());
    }
}
