package dev.khtml.hackathon.prompt;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PromptService {
	@Value("classpath:prompt.txt")
	private Resource promptPath;

	private final AzureOpenAiChatModel azureOpenAiChatModel;

	public String getResult(String userPrompt) {
		ChatResponse response = azureOpenAiChatModel
			.call(new Prompt(getPromptText(userPrompt)));
		return response.getResult().getOutput().getContent();
	}

	private String getPromptText(String userPrompt) {
		try {
			return new String(Files.readAllBytes(Paths.get(promptPath.getURI()))) + userPrompt;
		} catch (Exception e) {
			throw new RuntimeException("Something is wrong while reading prompt text");
		}
	}
}
