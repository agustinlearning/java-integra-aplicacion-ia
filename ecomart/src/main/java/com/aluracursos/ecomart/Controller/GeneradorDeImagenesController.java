package com.aluracursos.ecomart.Controller;

import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImageOptionsBuilder;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Mistralai no ofrece una integracion directa con un generador de imagnes es solucin aplica para modelos con integracion
//para generar imagnes directamente como openai con dalle-e

@RestController
@RequestMapping("/imagen")
public class GeneradorDeImagenesController {

    private final ImageModel imageModel;

    public GeneradorDeImagenesController(ImageModel imageModel) {
        this.imageModel = imageModel;
    }

    @GetMapping
    public String generadorDeImagenes(String prompt){
        var options = ImageOptionsBuilder.builder()
                .height(1024)
                .width(1024)
                .build();
        var response = imageModel.call(new ImagePrompt(prompt, options));
        return response.getResult().getOutput().getUrl();
    }

}


