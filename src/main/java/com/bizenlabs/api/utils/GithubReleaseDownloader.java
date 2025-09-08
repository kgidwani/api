package com.bizenlabs.api.utils;

import org.springframework.web.client.RestTemplate;

public class GithubReleaseDownloader {

//    public static downloadRelease(String repo, String version, String assetName, String downloadPath) {
//        // Placeholder for actual implementation
//        String apiUrl = String.format(
//                "https://api.github.com/repos/%s/releases/tags/%s", repo, version);
//
//        try {
//            // Get release info
//            java.net.URL url = new java.net.URL(apiUrl);
//            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
//            conn.setRequestProperty("Accept", "application/vnd.github.v3+json");
//            conn.connect();
//
//            if (conn.getResponseCode() != 200) {
//                throw new RuntimeException("Failed to get release info: HTTP " + conn.getResponseCode());
//            }
//
//            // Parse JSON response
//            StringBuilder response = new StringBuilder();
//            try (java.io.BufferedReader reader = new java.io.BufferedReader(
//                    new java.io.InputStreamReader(conn.getInputStream()))) {
//                String line;
//                while ((line = reader.readLine()) != null) {
//                    response.append(line);
//                }
//            }
//            conn.disconnect();
//
//            // Find asset download URL
//            String json = response.toString();
//            String assetUrl = null;
//            // Simple parsing (for demo; use a JSON library like Jackson/Gson in production)
//            String assetMarker = "\"name\":\"" + assetName + "\"";
//            int idx = json.indexOf(assetMarker);
//            if (idx != -1) {
//                int urlIdx = json.lastIndexOf("\"browser_download_url\":\"", idx);
//                if (urlIdx != -1) {
//                    int start = urlIdx + 25;
//                    int end = json.indexOf("\"", start);
//                    assetUrl = json.substring(start, end);
//                }
//            }
//            if (assetUrl == null) {
//                throw new RuntimeException("Asset not found: " + assetName);
//            }
//
//            // Download asset
//            java.net.URL downloadUrl = new java.net.URL(assetUrl);
//            try (java.io.InputStream in = downloadUrl.openStream();
//                 java.io.FileOutputStream out = new java.io.FileOutputStream(downloadPath)) {
//                byte[] buffer = new byte[8192];
//                int bytesRead;
//                while ((bytesRead = in.read(buffer)) != -1) {
//                    out.write(buffer, 0, bytesRead);
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException("Error downloading release asset", e);
//        }
//        System.out.println("Downloading " + assetName + " from " + repo + " version " + version + " to " + downloadPath);
//    }

    public void getMetadata(String version) {
        String releaseUrlPath = version.equals("latest") ? "latest" : "tags/" + version;
        String url = "https://api.github.com/repos/ollama/ollama/releases/" + releaseUrlPath;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url, String.class);
    }

}

