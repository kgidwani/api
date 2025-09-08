package com.bizenlabs.api.controllers;

import com.bizenlabs.api.platform.PlatformConfig;
import com.bizenlabs.api.platform.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platform")
public class PlatformController {

    @Autowired
    private PlatformService platformService;

    @GetMapping
    public PlatformConfig getPlatformConfig() {
        return platformService.getPlatformConfig();
    }
}