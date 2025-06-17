package app;

import algoritmos.AlgoritmoDijkstra;
import algoritmos.AlgoritmoVerificacaoCiclo;
import java.util.ArrayList;
import java.util.Scanner;
import lib.Grafo;
import lib.Vertice;
import models.Pessoa;

public class Entrada {
    private Grafo<Pessoa> grafo = new Grafo<>();
    private AlgoritmoVerificacaoCiclo<Pessoa> algoritmoDFS = new AlgoritmoVerificacaoCiclo<>();

    public void inicializar() {
        GerarPessoasERelacoes.gerarArquivo(); // Cria o arquivo
        grafo = CarregarGrafo.carregarPessoasArquivo("social_graph.txt"); // Carrega no grafo
    }

    public void menu() {
        Scanner s = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===================================");
            System.out.println("         FAKE REDE SOCIAL          ");
            System.out.println("===================================");
            System.out.println(" 0 - Sair");
            System.out.println(" 1 - Listar Pessoas");
            System.out.println(" 2 - Mostrar Relações de uma Pessoa");
            System.out.println(" 3 - Verificar Ciclos");
            System.out.println(" 4 - Calcular Caminhos Mínimos (Dijkstra)");
            System.out.println("===================================");
            System.out.print("Escolha uma opção: ");
            while (!s.hasNextInt()) {
                System.out.print("Por favor, digite um número: ");
                s.next();
            }
            opcao = s.nextInt();
            s.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 0:
                    System.out.println("\nSaindo do programa. Até logo!");
                    break;
                case 1:
                    listarPessoas();
                    break;
                case 2:
                    mostrarRelacoesDePessoa(s);
                    break;
                case 3:
                    verificarCiclos();
                    break;
                case 4:
                    executarDijkstra(s);
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);
    }


    private Pessoa buscarPessoaPorId(int id) {
        for (Vertice<Pessoa> v : grafo.getVerticeList()) {
            if (v.getValor().getId().equals(id)) {
                return v.getValor();
            }
        }
        return null;
    }

    private void listarPessoas() {
        System.out.println("\n--- Pessoas cadastradas ---");
        for (Vertice<Pessoa> v : grafo.getVerticeList()) {
            Pessoa p = v.getValor();
            System.out.printf("ID: %-3s | Nome: %s\n", p.getId(), p.getNome());
        }
        System.out.println("-------------------------------");
    }

    private void mostrarRelacoesDePessoa(Scanner s) {
        System.out.print("Digite o ID da pessoa: ");
        while (!s.hasNextInt()) {
            System.out.print("Por favor, digite um número: ");
            s.next();
        }
        Integer id = s.nextInt();
        s.nextLine();

        Pessoa pessoa = buscarPessoaPorId(id);
        if (pessoa == null) {
            System.out.println("Pessoa não encontrada.");
            return;
        }

        ArrayList<Pessoa> relacoes = grafo.getAdjacentes(pessoa);

        if (relacoes.isEmpty()) {
            System.out.println("Nenhuma relação encontrada para " + pessoa.getNome() + ".");
        } else {
            System.out.println("Relações de " + pessoa.getNome() + ":");
            for (Pessoa relacao : relacoes) {
                System.out.println("  -> " + relacao.getNome());
            }
        }
    }

    private void verificarCiclos() {
        System.out.println("Verificando ciclos no grafo...");
        var ciclos = algoritmoDFS.verificacaoCiclo(grafo);

        if (ciclos.isEmpty()) {
            System.out.println("Nenhum ciclo encontrado.");
        } else {
            System.out.println("Ciclos encontrados:");
            int count = 1;
            for (var ciclo : ciclos) {
                System.out.print("Ciclo " + count++ + ": ");
                for (int i = 0; i < ciclo.size(); i++) {
                    System.out.print(ciclo.get(i).getValor().getNome());
                    if (i < ciclo.size() - 1) System.out.print(" -> ");
                }
                System.out.println();
            }
        }
    }

    private void executarDijkstra(Scanner s) {
        System.out.print("Digite o ID da pessoa origem: ");
        while (!s.hasNextInt()) {
            System.out.print("Por favor, digite um número: ");
            s.next();
        }
        int idOrigem = s.nextInt();
        s.nextLine();

        System.out.print("Digite o ID da pessoa destino: ");
        while (!s.hasNextInt()) {
            System.out.print("Por favor, digite um número: ");
            s.next();
        }
        int idDestino = s.nextInt();
        s.nextLine();

        Pessoa origem = buscarPessoaPorId(idOrigem);
        Pessoa destino = buscarPessoaPorId(idDestino);

        if (origem == null || destino == null) {
            System.out.println("Pessoa de origem ou destino não encontrada.");
            return;
        }

        Vertice<Pessoa> verticeOrigem = null;
        ArrayList<Vertice<Pessoa>> vertices = grafo.getVerticeList();
        for (Vertice<Pessoa> v : vertices) {
            if (v.getValor().equals(origem)) {
                verticeOrigem = v;
                break;
            }
        }

        if (verticeOrigem == null) {
            System.out.println("Erro interno: pessoa origem não encontrada.");
            return;
        }

        AlgoritmoDijkstra<Pessoa> dijkstra = new AlgoritmoDijkstra<>();
        dijkstra.executar(grafo, verticeOrigem);

        int destinoIndex = -1;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).getValor().equals(destino)) {
                destinoIndex = i;
                break;
            }
        }

        if (destinoIndex == -1) {
            System.out.println("Erro interno: pessoa destino não encontrada.");
            return;
        }

        double distancia = dijkstra.getDistancia(destinoIndex);
        ArrayList<Integer> caminho = dijkstra.getCaminho(destinoIndex);

        if (distancia == Double.POSITIVE_INFINITY) {
            System.out.println("Não há caminho entre " + origem.getNome() + " e " + destino.getNome() + ".");
        } else {
            System.out.printf("\nDistância mínima entre %s e %s: %.2f\n", origem.getNome(), destino.getNome(), distancia);
            System.out.print("Caminho: ");
            for (int j = 0; j < caminho.size(); j++) {
                Pessoa p = vertices.get(caminho.get(j)).getValor();
                System.out.print(p.getNome());
                if (j < caminho.size() - 1) System.out.print(" -> ");
            }
            System.out.println();
        }
    }

}
