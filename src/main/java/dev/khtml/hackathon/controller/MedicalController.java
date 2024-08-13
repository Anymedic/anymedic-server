package dev.khtml.hackathon.controller;

import java.util.List;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/medical")
public class MedicalController {
	private final AzureOpenAiChatModel azureOpenAiChatModel;
	private final VectorStore vectorStore;

	public MedicalController(AzureOpenAiChatModel AzureOpenAiChatModel, VectorStore vectorStore) {
		this.azureOpenAiChatModel = AzureOpenAiChatModel;
		this.vectorStore = vectorStore;
	}

	@GetMapping
	public List<Generation> chat(@RequestParam String question) {
		Prompt prompt = new Prompt(question);
		ChatResponse response = azureOpenAiChatModel
			.call(prompt);
		return response.getResults();
	}
	//
	// @GetMapping("/search")
	// public List<Document> search(@RequestParam String question) {
	// 	List<Document> documents = vectorStore.similaritySearch(SearchRequest.query("The World"));
	// 	return documents;
	// }
}
