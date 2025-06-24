package app;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import models.Pessoa;
import models.Relacao;

public class GerarPessoasERelacoes {

    private static final int NUM_PESSOAS = 50;
    private static final int NUM_RELACOES = 500;
    private static final String NOME_ARQUIVO = "social_graph.txt";

    static String[] primeiroNome = {
        "Lucas", "Ana", "Pedro", "Mariana", "João", "Camila", "Felipe", "Laura", "Rafael", "Juliana",
        "Mateus", "Bianca", "Gustavo", "Fernanda", "Bruno", "Amanda", "Daniel", "Isabela", "Thiago", "Letícia",
        "Gabriel", "Carolina", "Vinícius", "Patrícia", "André", "Larissa", "Rodrigo", "Beatriz", "Eduardo", "Natália",
        "Leonardo", "Priscila", "Marcelo", "Tatiane", "Alexandre", "Vanessa", "Ricardo", "Aline", "Diego", "Renata",
        "Murilo", "Sabrina", "Caio", "Paula", "Vitor", "Helena", "Samuel", "Bruna", "Otávio", "Cecília"
    };

    static String[] segundoNome = {
        "Silva", "Souza", "Costa", "Oliveira", "Pereira", "Lima", "Gomes", "Ribeiro", "Alves", "Carvalho",
        "Rocha", "Fernandes", "Barros", "Moura", "Campos", "Cardoso", "Teixeira", "Martins", "Freitas", "Duarte",
        "Monteiro", "Batista", "Dias", "Moreira", "Nunes", "Mendes", "Araujo", "Cavalcante", "Pinto", "Farias",
        "Ferreira", "Machado", "Melo", "Ramos", "Vieira", "Castro", "Almeida", "Pires", "Santos", "Borges",
        "Rezende", "Peixoto", "Assis", "Tavares", "Barbosa", "Correia", "Miranda", "Sales", "Antunes", "Aguiar"
    };

    private static String getAleatorio(String[] array, Random random) {
        return array[random.nextInt(array.length)];
    }

    private static String gerarNomeAleatorio(Random random){
        String s1 = getAleatorio(primeiroNome, random);
        String s2 = getAleatorio(segundoNome, random);

        return s1 + ' ' + s2;
    }

    private static void gerarArquivoDePessoasERelacoes() {
        Random random = new Random();
        List<Pessoa> pessoas = new ArrayList<>();
        List<Relacao> relacoes = new ArrayList<>();
        Set<String> relacoesCriadas = new HashSet<>();

        for (int i = 0; i < NUM_PESSOAS; i++) {
            String nome = gerarNomeAleatorio(random);
            Integer id = i;
            pessoas.add(new Pessoa(id, nome));
        }

        while (relacoes.size() < NUM_RELACOES) {
            Pessoa p1 = pessoas.get(random.nextInt(pessoas.size()));
            Pessoa p2 = pessoas.get(random.nextInt(pessoas.size()));

            if (!p1.equals(p2)) {
                String chave = p1.getId() + "-" + p2.getId();
                String chaveInvertida = p2.getId() + "-" + p1.getId();

                if (!relacoesCriadas.contains(chave) && !relacoesCriadas.contains(chaveInvertida)) {
                    int peso = 1 + random.nextInt(10); // Peso de 1 a 10
                    relacoes.add(new Relacao(p1, p2, peso));
                    relacoesCriadas.add(chave);
                }
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOME_ARQUIVO, StandardCharsets.UTF_8))) {

            for (Pessoa p : pessoas) {
                writer.write("P;" + p.getId() + ";" + p.getNome());
                writer.newLine();
            }

            for (Relacao r : relacoes) {
                writer.write("R;" + r.getOrigem().getId() + ";" + r.getDestino().getId() + ";" + r.getPeso());
                writer.newLine();
            }

            System.out.println("Arquivo gerado com sucesso: " + NOME_ARQUIVO);
        } catch (IOException e) {
            System.err.println("Erro ao escrever arquivo: " + e.getMessage());
        }
    }

    public static void gerarArquivo() {
        gerarArquivoDePessoasERelacoes();
    }




}