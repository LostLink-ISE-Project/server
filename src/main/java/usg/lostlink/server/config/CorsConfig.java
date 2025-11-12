package usg.lostlink.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

  @Bean
  public WebMvcConfigurer corsConfigurer() {
    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:5173",
                "http://127.0.0.1:3000",
                "https://lostlink-form.usg.az/",
                "https://lostlink.usg.az/",
                "https://lostlink-form-dev.usg.az/",
                "https://lostlink-dev.usg.az/")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS")
            .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "Accept")
            .exposedHeaders("Authorization")
            .allowCredentials(true)
            .maxAge(86400);
      }
    };
  }
}
