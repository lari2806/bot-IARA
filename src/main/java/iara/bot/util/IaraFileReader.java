package iara.bot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IaraFileReader {

    @Value("bot/src/main/java/iara/bot/mensagens-chatbot/mensagens.txt")
    private String filePath;


    public String leitorIara(String entradaUsuario) {
        
        entradaUsuario = RemoverAcentos.remover(entradaUsuario.toLowerCase());
        
        String palavraChave = encontrarPalavra(entradaUsuario);

        if (palavraChave == null) {
            System.out.println("Palavra chave não foi encontrada");
        }

        File file = new File(filePath);
        System.out.println("Caminho do arquivo: " + filePath);

        try (Scanner reader = new Scanner(file, "UTF-8")) {
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
        } catch (FileNotFoundException e) {
            System.out.println("Erro: Arquivo não encontrado.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Um erro ocorreu ao ler o arquivo.");
            e.printStackTrace();
        }

        return "Desculpe, não consegui encontrar uma resposta para sua pergunta. Tente novamente.";
    }

    public String encontrarPalavra(String entradaUsuario){
        final Set<String> palavrasChave = new HashSet<>(Arrays.asList("duvida", "pergunta", "ajuda"));

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