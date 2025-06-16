package app;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import lib.Grafo;
import models.Pessoa;

public class CarregarGrafo {

    public static Grafo<Pessoa> carregarPessoasArquivo(String nomeArquivo) {
        Grafo<Pessoa> grafo = new Grafo<>();
        Map<Long, Pessoa> mapaPessoas = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(nomeArquivo), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] partes = linha.split(";");
                if (partes.length == 0) continue;

                if (partes[0].equals("P")) {
                    // Linha de Pessoa: P;id;nome
                    Long id = Long.parseLong(partes[1]);
                    String nome = partes[2];
                    Pessoa pessoa = new Pessoa(id, nome);
                    grafo.adicionarVertice(pessoa);
                    mapaPessoas.put(id, pessoa);

                } else if (partes[0].equals("R")) {
                    // Linha de Relação: R;idOrigem;idDestino;peso
                    Long idOrigem = Long.parseLong(partes[1]);
                    Long idDestino = Long.parseLong(partes[2]);
                    int peso = Integer.parseInt(partes[3]);

                    Pessoa origem = mapaPessoas.get(idOrigem);
                    Pessoa destino = mapaPessoas.get(idDestino);

                    if (origem != null && destino != null) {
                        grafo.adicionarAresta(origem, destino, peso);
                    }
                }
            }

            System.out.println("Grafo carregado com sucesso a partir do arquivo: " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao carregar grafo: " + e.getMessage());
        }

        return grafo;
    }
}
