package com.ctw.workstation.goodbye;

import io.smallrye.config.ConfigMapping;
import io.smallrye.config.WithName;

@ConfigMapping(prefix = "goodbye")
public interface GoodbyeConfig {

    @WithName("message")
    String message();

}