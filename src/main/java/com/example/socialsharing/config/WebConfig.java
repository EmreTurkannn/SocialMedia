
package com.example.socialsharing.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Fotoğrafların kaydedileceği dizin
        registry.addResourceHandler("/profilePhotos/**")
                .addResourceLocations("file:src/main/resources/static/profilePhotos/");
    }
}
