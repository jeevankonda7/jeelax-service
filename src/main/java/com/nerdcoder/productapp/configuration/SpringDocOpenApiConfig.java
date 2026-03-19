package com.nerdcoder.productapp.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SpringDocOpenApiConfig {

  @Bean
  public OpenAPI myCustomConfig() {
    return new OpenAPI()
            .info(new Info()
                    .title("Product Inventory APIs")
                    .description("by Jeevan")
            );
//            .servers(List.of(
//                    new Server().url("http:localhost:3125").description("local"),
//                    new Server().url("http:localhost:3124").description("deployed")
//            ));
  }
}
