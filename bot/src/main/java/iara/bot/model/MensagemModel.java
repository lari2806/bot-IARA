package iara.bot.model;

import org.springframework.stereotype.Component;

@Component
public class MensagemModel {

    private String mensagem;

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
}