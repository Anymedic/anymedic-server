package dev.khtml.hackathon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI api() {
		Server server = new Server().url("/");

		return new OpenAPI()
			.info(getSwaggerInfo())
			.addServersItem(server);
	}

	private Info getSwaggerInfo() {
		License license = new License();
		license.setName("MIT License");
		license.setUrl("https://opensource.org/licenses/MIT");

		return new Info()
			.title("K-HTML API Document")
			.description("K-HTML API 문서 입니다.")
			.version("v0.0.1")
			.license(license);
	}
}
