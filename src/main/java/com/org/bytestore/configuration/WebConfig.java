package com.org.bytestore.configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/byteStore/**") // Adjust based on your API endpoints
                .allowedOrigins(
                        "http://localhost:5173",
                        "https://19piyush98.github.io"
                ) // Allow both local and deployed frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Add "OPTIONS" for preflight requests
                .allowCredentials(true) // Allow credentials (e.g., cookies, auth headers)
                .allowedHeaders("*"); // Allow all headers
    }
}
