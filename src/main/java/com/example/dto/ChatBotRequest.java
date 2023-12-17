package com.example.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatBotRequest implements Serializable {
    private String model;

    @JsonProperty("max_tokens")

    private Integer maxTokens;
    private Double temperature;
    private Boolean stream;
    private List<ChatBotMessage> messages;

    @JsonProperty("top_p")
    private Double topP;

    @Builder
    public ChatBotRequest(String model, Integer maxTokens, Double temperature,
                          Boolean stream, List<ChatBotMessage> messages
            ,Double topP) {
        this.model = model;
        this.maxTokens = maxTokens;
        this.temperature = temperature;
        this.stream = stream;
        this.messages = messages;
        this.topP = topP;
    }

}
