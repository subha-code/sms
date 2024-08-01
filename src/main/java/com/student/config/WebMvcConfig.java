package com.student.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebMvcConfig  implements WebMvcConfigurer {
    public void addRecourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/swager-ui.html**")
                .addResourceLocations("classpath:/META-INF/resource/");
}
}