package iara.bot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import iara.bot.model.MensagemModel;
import iara.bot.util.IaraFileReader;

@Configuration
public class AppConfig {
    @Bean
    public IaraFileReader iaraFileReader(){
        return new IaraFileReader();
    }

    @Bean
    public MensagemModel mensagemModel(){
        return new MensagemModel();
    }

}
