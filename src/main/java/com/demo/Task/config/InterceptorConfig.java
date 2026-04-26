package com.demo.Task.config;

import com.demo.Task.interceptor.AutenticacionInterceptor;
import com.demo.Task.interceptor.LoggingInterceptor;
import com.demo.Task.interceptor.RendimientoInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private AutenticacionInterceptor autenticacionInterceptor;

    @Autowired
    private RendimientoInterceptor rendimientoInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 1. Logging interceptor - se ejecuta para TODAS las rutas
        registry.addInterceptor(loggingInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/public/**", "/static/**");

        // 2. Autenticación interceptor - SOLO para rutas privadas
        registry.addInterceptor(autenticacionInterceptor)
                .addPathPatterns("/api/private/**")  // SOLO /api/private/*
                .excludePathPatterns("/api/public/**", "/api/exceptions/**", "/api/ioc/**");  // Excluir IoC y excepciones

        // 3. Rendimiento interceptor - para monitorear endpoints específicos
        registry.addInterceptor(rendimientoInterceptor)
                .addPathPatterns("/api/ioc/**", "/api/exceptions/**", "/api/private/**")
                .excludePathPatterns("/api/public/**");

        System.out.println("✅ Interceptores registrados correctamente");
    }
}