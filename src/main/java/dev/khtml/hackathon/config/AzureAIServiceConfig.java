// package dev.khtml.hackathon.config;
//
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// import com.azure.core.credential.AzureKeyCredential;
// import com.azure.search.documents.indexes.SearchIndexClient;
// import com.azure.search.documents.indexes.SearchIndexClientBuilder;
//
// @Configuration
// public class AzureAIServiceConfig {
//
// 	@Value("${spring.ai.vectorstore.azure.api-key}")
// 	private String apiKey;
//
// 	@Value("${spring.ai.vectorstore.azure.url}")
// 	private String endpoint;
//
// 	@Bean
// 	public SearchIndexClient searchIndexClient() {
// 		return new SearchIndexClientBuilder().endpoint(endpoint)
// 			.credential(new AzureKeyCredential(apiKey))
// 			.buildClient();
// 	}
// }
