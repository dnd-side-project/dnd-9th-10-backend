package com.dnd.bbok.infra.swagger;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.Server;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

  //Authorization이 필요없는 API
  @Bean
  public Docket nonSecuredApi() {
    return new Docket(DocumentationType.OAS_30)
        .groupName("Non-Security API")
        .select()
        .apis(withoutMethodAnnotation(PreAuthorize.class))
        .apis(RequestHandlerSelectors.basePackage("com.dnd.bbok.member"))
        .build()
        .apiInfo(apiInfo());
  }

  //Authorization이 필요한 API
  @Bean
  public Docket securedApi() {
    return new Docket(DocumentationType.OAS_30)
        .ignoredParameterTypes(AuthenticationPrincipal.class)
        .groupName("Security API")
        .securityContexts(List.of(securityContext()))
        .securitySchemes(List.of(apiKey()))
        .select()
        .apis(RequestHandlerSelectors.withMethodAnnotation(PreAuthorize.class))
        .apis(RequestHandlerSelectors.basePackage("com.dnd.bbok.domain")
                .or(RequestHandlerSelectors.basePackage("com.dnd.bbok.basicchecklist"))
                .or(RequestHandlerSelectors.basePackage("com.dnd.bbok.member"))
                .or(RequestHandlerSelectors.basePackage("com.dnd.bbok.friend"))
                .or(RequestHandlerSelectors.basePackage("com.dnd.bbok.diary"))
                .or(RequestHandlerSelectors.basePackage("com.dnd.bbok.saying")))
        .build()
        .apiInfo(apiInfo());
  }

  public static Predicate<RequestHandler> withoutMethodAnnotation(
      final Class<? extends Annotation> annotation) {
    return input -> !input.isAnnotatedWith(annotation);
  }

  private SecurityContext securityContext(){
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .build();
  }

  private List<SecurityReference> defaultAuth(){
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return List.of(new SecurityReference("Authorization", authorizationScopes));
  }

  // 버튼 클릭 시 입력 값 설정
  private ApiKey apiKey(){
    return new ApiKey("Authorization", "Bearer", "header");
  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("BBOK Swagger API")
        .description("DND 9th 10조의 Swagger docs 입니다.")
        .version("1.0")
        .build();
  }


}
