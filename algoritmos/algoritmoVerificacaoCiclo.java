package algoritmos;

import java.util.ArrayList;

import lib.Aresta;
import lib.Grafo;
import lib.Vertice;

public class AlgoritmoVerificacaoCiclo<T> {
    private final int BRANCO = 0;
    private final int CINZA = 1;
    private final int PRETO = 2;
    private ArrayList<Integer> cor;

    // public ArrayList<ArrayList<Vertice<T>>> verificacaoCiclo(Grafo<T> g) {
    //     ArrayList<ArrayList<Vertice<T>>> ciclos = new ArrayList<>();
    //     ArrayList<Vertice<T>> visitados = new ArrayList<>();
        
    //     /*
    //      * Lista que armazena a cor de cada vertice
    //      * * BRANCO = 0 (não visitado)
    //      * * CINZA = 1 (visitado, mas não finalizado)
    //      * * PRETO = 2 (visitado e finalizado) 
    //      * Inicializada com BRANCO para todos os vértices
    //      */
    //     inicializarCor(g.getVerticeList().size());

    //     for (Vertice<T> v : g.getVerticeList()) {
    //         int index = g.getVerticeList().indexOf(v);
    //         if (cor.get(index) == BRANCO) {
    //             ArrayList<Vertice<T>> cicloAtual = new ArrayList<>();
    //             if (hasCycle(g, v)) {
    //                 ciclos.add(cicloAtual);
    //             }
    //         }
    //     }
    //     return ciclos;
        
    // }

    // private void inicializarCor(int tamanho) {
    //     cor = new ArrayList<>();
    //     for (int i = 0; i < tamanho; i++) {
    //         cor.add(BRANCO);
    //     }
    // }

    // private boolean hasCycle(Grafo<T> g, Vertice<T> v) {
    //     if (cor.get(g.getVerticeList().indexOf(v)) == CINZA) {
    //         return true; // Node is in the stack, cycle detected
    //     }
    //     if (cor.get(g.getVerticeList().indexOf(v)) == PRETO) {
    //         return false; // Node already processed, no cycle
    //     }
    
    //     cor.set(g.getVerticeList().indexOf(v), CINZA); // Mark node as in the stack
    //     for (Vertice<T> neighbor : g.) {
    //         if (hasCycle(g, neighbor)) {
    //             return true; // Propagate cycle detection
    //         }
    //     }
    //     cor.set(g.getVerticeList().indexOf(v), PRETO); // Mark node as processed
    //     return false;
    // }
}