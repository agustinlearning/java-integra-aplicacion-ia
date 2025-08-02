package com.aluracursos.ecomart.Controller;

import com.knuddels.jtokkit.Encodings;
import com.knuddels.jtokkit.api.EncodingType;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/categoria")
public class CategorizadorDeProductosController {

    private final ChatClient chatClient;

    public CategorizadorDeProductosController(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    @GetMapping
    public String categorizarDeProducto(String producto){
        var system = """
                Tu eres un categorizador de producto y solo debes responder el nombre de la categoria del producto
                Escoge una categoria de la siguiente lista
                
                1.Higiene personal
                2.Electrodomesticos
                3.Deportes
                4.otros
                """;

        var tokens = contador(system, producto);
        System.out.println("tokens " + tokens);

        return this.chatClient.prompt()
                .system(system)
                .user(producto)
                .options(ChatOptions.builder().model("mistral-tiny").temperature(0.8).build())
                .call()
                .content();
    }

    private int contador(String system, String user){
        var registry = Encodings.newDefaultEncodingRegistry();
        var enc = registry.getEncoding(EncodingType.CL100K_BASE);
        return enc.countTokens(system + user);
    }

}

