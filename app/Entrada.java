package app;

import lib.Grafo;
import lib.Vertice;
import lib.Aresta;

import java.util.Scanner;

public class Entrada {
    private Grafo<Pessoa> grafo = new Grafo<>();

    public void inicializar() {
        GerarPessoasERelacoes.gerarArquivo(); // Cria o arquivo
        grafo = CarregarGrafo.carregarPessoasArquivo("social_graph.txt"); // Carrega no grafo
    }

    public void menu() {
        Scanner s = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Rede Social ===");
            System.out.println("1 - Listar Pessoas");
            System.out.println("2 - Mostrar Relações de uma Pessoa");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = s.nextInt();
            s.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 1:
                    listarPessoas();
                    break;
//                case 2:
//                    mostrarRelacoesDePessoa(s);
//                    break;
                case 3:
                    System.out.println("Encerrando programa.");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 3);
    }

    private void listarPessoas() {
        System.out.println("--- Pessoas cadastradas ---");
        for (Vertice<Pessoa> v : grafo.getVerticeList()) {
            Pessoa p = v.getValor();
            System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome());
        }
        System.out.println("---------------------------");
    }

//    private void mostrarRelacoesDePessoa(Scanner s) {
//        System.out.print("Digite o ID da pessoa: ");
//        Long id = s.nextLong();
//        s.nextLine();
//
//        Pessoa pessoa = buscarPessoaPorId(id);
//        if (pessoa == null) {
//            System.out.println("Pessoa não encontrada.");
//            return;
//        }
//
//        Vertice<Pessoa> vertice = grafo.getVertice(pessoa);
//        System.out.println("--- Relações de " + pessoa.getNome() + " ---");
//        for (Aresta<Pessoa> a : vertice.getArestaList()) {
//            System.out.println("-> " + a.getDestino().getValor().getNome() + " (peso: " + a.getPeso() + ")");
//        }
//        System.out.println("------------------------------------");
//    }

    private Pessoa buscarPessoaPorId(Long id) {
        for (Vertice<Pessoa> v : grafo.getVerticeList()) {
            if (v.getValor().getId().equals(id)) {
                return v.getValor();
            }
        }
        return null;
    }
}
