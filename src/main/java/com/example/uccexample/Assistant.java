package com.example.uccexample;

import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

@AiService
public interface Assistant {
    @UserMessage ("necesito que digas locuras y chistes porfa , 150 caracteres maximun")
    String chat();
}

