package com.ifpe.br.odontosys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permitir todas as origens
        configuration.addAllowedOriginPattern("*");
        
        // Permitir todos os métodos HTTP
        configuration.addAllowedMethod("*");
        
        // Permitir todos os headers
        configuration.addAllowedHeader("*");
        
        // Permitir credenciais (cookies, headers de autorização, etc.)
        configuration.setAllowCredentials(true);
        
        // Configurar headers expostos (opcional)
        configuration.addExposedHeader("Authorization");
        configuration.addExposedHeader("Content-Type");
        configuration.addExposedHeader("Accept");
        configuration.addExposedHeader("X-Requested-With");
        configuration.addExposedHeader("Cache-Control");
        
        // Aplicar configuração a todas as rotas
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        
        return source;
    }
}
