package lib;

public class Teste {
    public static void main(String[] args) {
        Grafo<Integer> g = new Grafo<>();

        g.adicionarVertice(1);
        g.adicionarVertice(2);
        g.adicionarVertice(3);
        g.adicionarVertice(4);
        g.adicionarVertice(5);
        g.adicionarVertice(6);
        g.adicionarVertice(7);

        g.adicionarAresta(1, 2, 1);
        g.adicionarAresta(1, 3, 1);
        g.adicionarAresta(2, 4, 1);
        g.adicionarAresta(2, 5, 1);
        g.adicionarAresta(3, 6, 1);
        g.adicionarAresta(4, 7, 1);
        g.adicionarAresta(5, 6, 1);
        g.adicionarAresta(6, 7, 1);

        // Arestas adicionais
        g.adicionarAresta(3, 5, 1);
        g.adicionarAresta(5, 1, 1);
        g.adicionarAresta(7, 2, 1);
        g.adicionarAresta(6, 4, 1);
        g.adicionarAresta(4, 1, 1);
        g.adicionarAresta(7, 3, 1);

        g.caminhoEmLargura();
    }
}