/*
 * This file is part of spring-boot-archetype-3x.
 *
 * Copyright (C) 2025 FixBox
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

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
