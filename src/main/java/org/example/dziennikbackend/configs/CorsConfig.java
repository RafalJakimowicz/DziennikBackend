// src/main/java/org/example/dziennikbackend/configs/CorsConfig.java
package org.example.dziennikbackend.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig {

    /**
     * Global CORS settings picked up by Spring Security.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200")); // Angular dev server
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));                     // any header
        config.setAllowCredentials(true);                           // send cookies / auth header
        config.setMaxAge(3600L);                                    // cache pre‑flight 1 h

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // apply to every path in the app
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
