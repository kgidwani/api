package com.bizenlabs.api.platform;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Service
public class PlatformService {

    @Autowired
    PlatformConfig platformConfig;

}
