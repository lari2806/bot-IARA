package iara.bot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import iara.bot.model.MensagemModel;
import iara.bot.util.IaraFileReader;
import org.springframework.web.bind.annotation.RequestBody;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("mensagens")
public class IaraController{

    @Autowired
    private IaraFileReader iaraFileReader;

    @PostMapping("/")
    public ResponseEntity<String> enviarMensagem(@RequestBody MensagemModel mensagemModel) {
        String mensagem = mensagemModel.getMensagem().toLowerCase();
        System.out.println(mensagem);
        String resposta = iaraFileReader.leitorIara(mensagem);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", StandardCharsets.UTF_8));

        return new ResponseEntity<>(resposta, headers, HttpStatus.OK);
    }

}
