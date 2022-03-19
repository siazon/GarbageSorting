package com.tus.garbagesorting.garbagesorting.Common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 将拦截器注入到SpringMVC中
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/upload/{iType}",
                        "/GetInviteCode");        // Verify API
        //.excludePathPatterns("/user/login"); // Skip API
    }
}
