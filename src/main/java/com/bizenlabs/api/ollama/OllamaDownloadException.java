package com.bizenlabs.api.ollama;

public class OllamaDownloadException extends Exception {
    public OllamaDownloadException(String message) {
        super(message);
    }
    public OllamaDownloadException(String message, Throwable cause) {
        super(message, cause);
    }
}
