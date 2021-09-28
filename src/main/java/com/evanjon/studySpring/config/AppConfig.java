package com.evanjon.studySpring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${app.origin}")
    private String origin = "";

    public String getOrigin() {
        return origin;
    }
}
