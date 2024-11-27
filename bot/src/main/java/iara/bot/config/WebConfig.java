package iara.bot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir em todos os endpoints
            .allowedOrigins(
                "http://127.0.0.1:5500/",
                "https://back-end-iara-production.up.railway.app",
                "https://mrdudae.github.io/IARA-2.0/",
                "https://mrdudae.github.io/IARA-2.0/chat.html"
            ) // Origem do frontend permitida
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*") // Permitir todos os headers
            .allowCredentials(true); // Caso tenha cookies/sessão
    }

    public CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }
}
