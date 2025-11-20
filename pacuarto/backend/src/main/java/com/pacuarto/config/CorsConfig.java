package com.pacuarto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permite CORS para todos los controladores
        registry.addMapping("/**")
                // Permite solicitudes desde el puerto de Angular
                .allowedOrigins("http://localhost:4200")
                // Permite todos los métodos HTTP (GET, POST, PUT, DELETE, etc.)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                // Permite enviar credenciales (cookies, encabezados de autenticación)
                .allowCredentials(true)
                // Permite todos los encabezados
                .allowedHeaders("*");
    }
}