package usg.lostlink.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    final String securitySchemeName = "bearerAuth";

    return new OpenAPI()
        .info(new Info()
            .title("Swagger API")
            .description("Swagger for endpoints."))
        .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
        .components(new Components()
            .addSecuritySchemes(securitySchemeName,
                new SecurityScheme()
                    .name(securitySchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
  }
}
