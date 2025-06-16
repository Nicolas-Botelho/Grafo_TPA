package app;

import java.util.Scanner;

import algoritmos.AlgoritmoVerificacaoCiclo;
import lib.Grafo;
import lib.Vertice;
import models.Pessoa;
import lib.Aresta;

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
            System.out.println("=== Rede Social ===");
            System.out.println("0 - Sair");
            System.out.println("1 - Listar Pessoas");
            System.out.println("2 - Mostrar Relações de uma Pessoa");
            System.out.println("3 - Verificar Ciclos");
            System.out.print("Escolha uma opção: ");
            opcao = s.nextInt();
            s.nextLine(); // consumir quebra de linha

            switch (opcao) {
                case 0:
                    System.out.println("Saindo do programa.");
                    break; // Sair do programa
                case 1:
                    listarPessoas();
                    break;
                case 2:
                    mostrarRelacoesDePessoa(s);
                    break;
                case 3:
                    verificarCiclos();
                    break;
                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void listarPessoas() {
        System.out.println("--- Pessoas cadastradas ---");
        for (Vertice<Pessoa> v : grafo.getVerticeList()) {
            Pessoa p = v.getValor();
            System.out.println("ID: " + p.getId() + ", Nome: " + p.getNome());
        }
        System.out.println("---------------------------");
    }

   private void mostrarRelacoesDePessoa(Scanner s) {
       System.out.print("Digite o ID da pessoa: ");
       Long id = s.nextLong();
       s.nextLine();

       Pessoa pessoa = buscarPessoaPorId(id);
       if (pessoa == null) {
           System.out.println("Pessoa não encontrada.");
           return;
       }

       Vertice<Pessoa> vertice = grafo.getVertice(pessoa);
       System.out.println("--- Relações de " + pessoa.getNome() + " ---");
       for (Aresta<Pessoa> a : vertice.getArestaList()) {
           System.out.println("-> " + a.getDestino().getValor().getNome() + " (peso: " + a.getPeso() + ")");
       }
       System.out.println("------------------------------------");
   }

    private void verificarCiclos() {
        System.out.println("Verificando ciclos no grafo...");
        var ciclos = algoritmoDFS.verificacaoCiclo(grafo);

        if (ciclos.isEmpty()) {
            System.out.println("Nenhum ciclo encontrado.");
        } else {
            System.out.println("Ciclos encontrados:");
            for (var ciclo : ciclos) {
                System.out.print("Ciclo: ");
                for (Vertice<Pessoa> v : ciclo) {
                    System.out.print(v.getValor().getNome() + " -> ");
                }
                System.out.println("Fim do Ciclo");
            }
        }
    }

    private Pessoa buscarPessoaPorId(Long id) {
        for (Vertice<Pessoa> v : grafo.getVerticeList()) {
            if (v.getValor().getId().equals(id)) {
                return v.getValor();
            }
        }
        return null;
    }
}
