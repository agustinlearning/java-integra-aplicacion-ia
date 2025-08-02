package com.aluracursos.ecomart.Controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.mistralai.api.MistralAiApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/generate")
public class GeneradorDeProductosController {

    private final ChatClient chatClient;

    public GeneradorDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }



    @GetMapping
    public String generadorDeProducto(){
        String userInput = "genera 5 productos ecologicos";
        return this.chatClient.prompt()
                .user(userInput)
                .call()
                .content();
    }


//    @GetMapping("/ai/generate")
//    public Map<String,String> generate(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
//        return Map.of("generation", this.chatClient.call(message));
//    }
//
//    @GetMapping("/ai/generateStream")
//    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
//        Prompt prompt = new Prompt(new UserMessage(message));
//        return this.chatClient.stream(prompt);
//    }
}

