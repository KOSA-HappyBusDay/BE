package com.example.service;

import com.example.config.ChatBotConfig;
import com.example.dto.ChatBotMessage;
import com.example.dto.ChatBotRequest;
import com.example.dto.ChatBotResponse;
import com.example.dto.QuestionRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatBotService {

    private  final RestTemplate restTemplate;

    @Value("${openai.api-key}")
    private String apiKey;

    public HttpEntity<ChatBotRequest> buildHttpEntity(ChatBotRequest chatBotRequest){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(ChatBotConfig.MEDIA_TYPE));
        httpHeaders.add(ChatBotConfig.AUTHORIZATION, ChatBotConfig.BEARER + apiKey);
        return new HttpEntity<>(chatBotRequest, httpHeaders);
    }

    public ChatBotResponse getResponse(HttpEntity<ChatBotRequest> chatBotRequestHttpEntity){

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(60000);
        //답변이 길어질 경우 TimeOut Error가 발생, 1분 설정해줍니다.
        requestFactory.setReadTimeout(60 * 1000);   //  1min
        restTemplate.setRequestFactory(requestFactory);

        ResponseEntity<ChatBotResponse> responseEntity = restTemplate.postForEntity(
                ChatBotConfig.CHAT_URL,
                chatBotRequestHttpEntity,
                ChatBotResponse.class);

        return responseEntity.getBody();
    }

    public ChatBotResponse askQuestion(QuestionRequest questionRequest){
        String roleInstruction = "너의 이름은 클리닉(cleanic)이야. GPT-4 모델이 탑재된, cleanzone 웹페이지의 챗봇이지. "
                + "피부 관련된 질문을 받으면 성실히 답변해주고 다른 내용의 질문이라면 답변드리기 어렵다고 친절히 안내해줘. "
                + "전체 답변은 300자에서 400자 이내로, 문장은  답변해줘. ";
        String userQuestion = questionRequest.getQuestion();
        String prompt = roleInstruction + "\n" + userQuestion;


        List<ChatBotMessage> messages = new ArrayList<>();
        messages.add(ChatBotMessage.builder()
                .role("system")
                .content(prompt)
                .build());
        return this.getResponse(
                this.buildHttpEntity(
                        new ChatBotRequest(
                                ChatBotConfig.CHAT_MODEL,
                                ChatBotConfig.MAX_TOKEN,
                                ChatBotConfig.TEMPERATURE,
                                ChatBotConfig.STREAM,
                                messages,
                                ChatBotConfig.TOP_P
                        )
                )
        );
    }
}
