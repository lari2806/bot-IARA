package iara.bot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

@Component
public class IaraFileReader {

    // Injetar o arquivo mensagens.txt como um Resource do classpath
    @Value("classpath:/mensagens.txt")
    private Resource fileResource;

    public String leitorIara(String entradaUsuario) {
        
        // Remover acentos da entrada
        entradaUsuario = RemoverAcentos.remover(entradaUsuario.toLowerCase());

        // Encontrar a palavra-chave na entrada
        String palavraChave = encontrarPalavra(entradaUsuario);

        if (palavraChave == null) {
            System.out.println("Palavra chave não foi encontrada");
        }

        // Abrir o arquivo usando o Resource
        try (InputStream inputStream = fileResource.getInputStream();
             Scanner reader = new Scanner(inputStream, "UTF-8")) {

            while (reader.hasNextLine()) {
                String linha = reader.nextLine();
                System.out.println(linha);
                String linhaSemAcento = RemoverAcentos.remover(linha.toLowerCase());
                System.out.println(linhaSemAcento);

                if (linhaSemAcento.startsWith("user:") && palavraChave != null && linhaSemAcento.contains(palavraChave) || linhaSemAcento.contains(entradaUsuario)) {

                    if (reader.hasNextLine()) {
                        
                        String resposta = reader.nextLine().trim();
                        System.out.println(resposta);

                        if (resposta.startsWith("Iara:")) {
                            return resposta;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao processar o arquivo.");
            e.printStackTrace();
        }

        return "Desculpe, não consegui encontrar uma resposta para sua pergunta. Tente novamente.";
    }

    public String encontrarPalavra(String entradaUsuario) {
        // Definir as palavras-chave
        final Set<String> palavrasChave = new HashSet<>(Arrays.asList("duvida", "pergunta", "ajuda"));

        // Separar as palavras da entrada do usuário
        String[] palavrasUsuario = entradaUsuario.split("\\s+");

        String palavraChaveEncontrada = null;
        for (String palavra : palavrasUsuario) {
            if (palavrasChave.contains(palavra)) {
                palavraChaveEncontrada = palavra;
                break;
            }
        }

        if (palavraChaveEncontrada == null) {
            System.out.println("Desculpe, não consegui identificar a palavra-chave. Tente ser mais específico.");
        }

        return palavraChaveEncontrada;
    }
}
