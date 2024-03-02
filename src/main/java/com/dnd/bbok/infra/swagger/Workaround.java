package com.dnd.bbok.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import springfox.documentation.oas.web.OpenApiTransformationContext;
import springfox.documentation.oas.web.WebMvcOpenApiTransformationFilter;
import springfox.documentation.spi.DocumentationType;

@Component
public class Workaround implements WebMvcOpenApiTransformationFilter {
  @Override
  public OpenAPI transform(OpenApiTransformationContext<HttpServletRequest> context) {
    OpenAPI openApi = context.getSpecification();

    Server localServer = new Server();
    localServer.setDescription("usages for local");
    localServer.setUrl("http://localhost:8080");

    Server prodServer = new Server();
    prodServer.setDescription("usages for real");
    prodServer.setUrl("https://bbok.kro.kr");

    openApi.setServers(Arrays.asList(localServer, prodServer));
    return openApi;
  }

  @Override
  public boolean supports(DocumentationType docType) {
    return docType.equals(DocumentationType.OAS_30);
  }
}
