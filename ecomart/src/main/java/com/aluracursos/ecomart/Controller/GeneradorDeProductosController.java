package com.aluracursos.ecomart.Controller;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.EncodingType;
import org.springframework.ai.chat.client.ChatClient;
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

    private int contador(String system, String user){
        var registry = Encodings.newDefaultEncodingRegistry();
        var enc = registry.getEncoding(EncodingType.CL100K_BASE);
        return enc.countTokens(system + user);
    }

}

