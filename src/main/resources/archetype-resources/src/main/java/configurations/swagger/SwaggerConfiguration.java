package ${package}.configurations.swagger;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi  myApi(){
        return  GroupedOpenApi.builder()
                .group("default")
                .pathsToMatch("/api/standard/V1/**")
                .build();
    }

    @Bean
    public GroupedOpenApi  myApi2(){
        return  GroupedOpenApi.builder()
                .group("mobile")
                .pathsToMatch("/api/mobile/V1/**")
                .build();
    }

    @Bean
    public OpenAPI infoApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Demo Test")
                        .description("Only test Demo")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Demo Wiki documentation")
                        .url("http://example.fake.doc"));
    }

}
