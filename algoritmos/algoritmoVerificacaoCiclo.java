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

    public ArrayList<ArrayList<Vertice<T>>> verificacaoCiclo(Grafo<T> g) {
        ArrayList<ArrayList<Vertice<T>>> ciclos = new ArrayList<>();

        /*
         * Lista que armazena a cor de cada vertice
         * * BRANCO = 0 (não visitado)
         * * CINZA = 1 (visitado, mas não finalizado)
         * * PRETO = 2 (visitado e finalizado) 
         * Inicializada com BRANCO para todos os vértices
         */
        inicializarCor(g.getVerticeList().size());

        for (Vertice<T> v : g.getVerticeList()) {
            int index = g.getVerticeList().indexOf(v);
            if (cor.get(index) == BRANCO) {
                ArrayList<Vertice<T>> caminhoAtual = new ArrayList<>();
                buscaCiclo(g, v, caminhoAtual, ciclos);
            }
        }
        return ciclos;
        
    }

    private void inicializarCor(int tamanho) {
        cor = new ArrayList<>();
        for (int i = 0; i < tamanho; i++) {
            cor.add(BRANCO);
        }
    }

    private void buscaCiclo(Grafo<T> g, Vertice<T> v, ArrayList<Vertice<T>> caminhoAtual, ArrayList<ArrayList<Vertice<T>>> ciclos) {
        int idx = g.getVerticeList().indexOf(v);

        if (cor.get(idx) == CINZA) {
            // Encontrou ciclo: copia o caminho do ciclo
            int cicloInicio = caminhoAtual.indexOf(v);
            if (cicloInicio != -1) {
                ArrayList<Vertice<T>> ciclo = new ArrayList<>(caminhoAtual.subList(cicloInicio, caminhoAtual.size()));
                ciclo.add(v); // fecha o ciclo
                ciclos.add(ciclo);
            }
            return;
        }
        if (cor.get(idx) == PRETO) {
            return;
        }

        cor.set(idx, CINZA);
        caminhoAtual.add(v);
        for (Aresta<T> aresta : v.getArestaList()) {
            Vertice<T> vizinho = aresta.getDestino();
            buscaCiclo(g, vizinho, caminhoAtual, ciclos);
        }
        caminhoAtual.remove(caminhoAtual.size() - 1);
        cor.set(idx, PRETO);
    }
}