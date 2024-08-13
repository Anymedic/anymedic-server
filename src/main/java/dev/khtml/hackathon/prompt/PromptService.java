package dev.khtml.hackathon.prompt;

import org.springframework.ai.azure.openai.AzureOpenAiChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PromptService {
	private static final String SYSTEM_PROMPT =
		"You are a medical AI specialist. When a user describes their situation, suggest 1 to 5 possible conditions (or diseases) and provide first aid instructions accordingly. I'll give you a sample response, and you should return it in JSON format and respond in Korean. Do not include duplicate reasons. Department is only in these case: 가정의학과, 결핵과, 구강내과, 구강병리과, 구강악안면외과, 내과, 마취통증의학과, 방사선종양학과, 병리과, 비뇨의학과, 사상체질과, 산부인과, 성형외과, 소아청소년과, 소아치과, 신경과, 신경외과, 심장혈관흉부외과, 안과, 영상의학과, 영상치의학과, 예방의학과, 예방치과, 외과, 응급의학과, 이비인후과, 재활의학과, 정신건강의학과, 정형외과, 직업환경의학과, 진단검사의학과, 치과, 치과교정과, 치과보존과, 치과보철과, 치주과, 침구과, 통합치의학과, 피부과, 한방내과, 한방부인과, 한방소아과, 한방신경정신과, 한방안이비인후피부과, 한방응급, 한방재활의학과, 핵의학과\n"
			+ "\n"
			+ "response example:\n"
			+ "---\n"
			+ "{ \"cases\": [ { \"reason\": \"손가락 절단\", \"detail\": { \"description\": \"채소나 고기를 손질하다가 칼이 미끄러져 손가락을 베는 경우입니다. 심각한 출혈이 발생할 수 있으며, 신속한 대처가 필요합니다.\", \"manage\": \"1. 출혈이 발생하면 깨끗한 천으로 즉시 압박하여 지혈을 시도합니다.\\n2. 상처 부위를 심장보다 높게 유지합니다.\\n3. 가능한 빨리 응급실로 이동합니다.\\n4. 절단된 부분은 깨끗한 천에 싸서 얼음 위에 놓아 보관합니다.\", \"isEmergency\": true, \"subject\": \"응급의학과\" } }, ... ] }\n"
			+ "\n"
			+ "---\n"
			+ "current request:";
	private final AzureOpenAiChatModel azureOpenAiChatModel;

	public String getResult(String userPrompt) {
		ChatResponse response = azureOpenAiChatModel
			.call(new Prompt(SYSTEM_PROMPT + userPrompt));

		return response.getResult().getOutput().getContent();
	}
}
