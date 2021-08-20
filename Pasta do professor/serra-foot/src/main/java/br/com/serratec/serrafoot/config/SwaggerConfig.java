package br.com.serratec.serrafoot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket swagger() {
		return new Docket(DocumentationType.SWAGGER_2) // Tipo de documentação, no caso swagger2
				.select() // Utilizado para pega ro projeto inteiro.
				.apis(RequestHandlerSelectors.any()) // Filtra as apis que desejar, nesse caso pega todos os controllers
				.paths(regex("/api.*")) // Filtra somente os controllers que iniciam com /api
				.build() // Constroi o objeto
				.apiInfo(info()); // Adiciona informações complementares a documentação.
	}
	
	private ApiInfo info() {
		return new ApiInfo(
				"SerraFoot API REST", //Titulo da API
				"API REST de cadastro de Jogadores", // Descrição da API
				"1.0", // Versão
				"Termos de Serviços", // Termos da licença, pode ser a URL
				new Contact("Weberson Rodrigues", //Nome da pessoa de contato
						"https://www.linkedin.com/in/weberson-rodrigues/", // Perfil da pessoa.
						"weberson.oliveira@docente.firjan.senai.br"), // Email da pessoa
				"Apache License Version 2.0", // Descrição da licensa
				"https://www.apache.org/licesen.html", // URL da licença
				new ArrayList<VendorExtension>());
	}
	

	
}
