package br.com.regifelix.jogodavelha.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;


import lombok.Generated;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * Configuração do Swagger
 * @author Reginaldo Donizete Felix
 *
 */
@Configuration
@EnableSwagger2
@Generated
public class SwaggerConfig {	
	
	
	@Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())

                .build().apiInfo(apiInfo());
    }

	private ApiInfo apiInfo() {		
		StringBuilder titulo = new StringBuilder();
		titulo.append("PROJETO JOGO DA VELHA -1.0.0");
		StringBuilder versionSB = new StringBuilder();

		return new ApiInfoBuilder().title(titulo.toString()).description("<br>Projeto Teste Comunidade Pagamentos - Itaú Unibanco <br>" +
				"O objetivo dessa aplicação é identificar se houve ou não um vencedor a partir de um jogo da velha. ")
				.contact(new Contact("Reginaldo D. Felix", "https://regifelix.wordpress.com/", "regifelix@gmail.com"))
				.version(versionSB.toString()).termsOfServiceUrl("http://terms-of-services.url").license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0").build();
	}

}
