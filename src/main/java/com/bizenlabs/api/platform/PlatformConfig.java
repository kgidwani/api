package com.bizenlabs.api.platform;

public class PlatformConfig {
    public String os;
    public String arch;
    public String appDataDir;

    public PlatformConfig(String os, String arch, String appDataDir) {
        this.os = os;
        this.arch = arch;
        this.appDataDir = appDataDir;
    }
}
