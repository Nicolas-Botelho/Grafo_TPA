package algoritmos;

import java.util.ArrayList;
import lib.Aresta;
import lib.Grafo;
import lib.Vertice;

public class AlgoritmoDijkstra<T> {
    private double[] distancia;
    private int[] predecessores;

    public void executar(Grafo<T> grafo, Vertice<T> origem) {
        int n = grafo.getVerticeList().size();
        distancia = new double[n];
        predecessores = new int[n];
        boolean[] visitado = new boolean[n];

        for (int i = 0; i < n; i++) {
            distancia[i] = Double.POSITIVE_INFINITY;
            predecessores[i] = -1;
            visitado[i] = false;
        }

        int origemIndex = grafo.getVerticeList().indexOf(origem);
        distancia[origemIndex] = 0;

        for (int i = 0; i < n; i++) {
            int u = menorDistancia(distancia, visitado);
            if (u == -1) break;

            visitado[u] = true;
            Vertice<T> atual = grafo.getVerticeList().get(u);

            for (Aresta<T> aresta : atual.getArestaList()) {
                Vertice<T> vizinho = aresta.getDestino();
                int v = grafo.getVerticeList().indexOf(vizinho);
                double peso = aresta.getPeso();

                if (!visitado[v] && distancia[u] + peso < distancia[v]) {
                    distancia[v] = distancia[u] + peso;
                    predecessores[v] = u;
                }
            }
        }
    }

    public double getDistancia(int indice) {
        return distancia[indice];
    }

    public ArrayList<Integer> getCaminho(int destino) {
        ArrayList<Integer> caminho = new ArrayList<>();
        while (destino != -1) {
            caminho.add(0, destino);
            destino = predecessores[destino];
        }
        return caminho;
    }

    private int menorDistancia(double[] distancia, boolean[] visitado) {
        double menor = Double.POSITIVE_INFINITY;
        int indice = -1;
        for (int i = 0; i < distancia.length; i++) {
            if (!visitado[i] && distancia[i] < menor) {
                menor = distancia[i];
                indice = i;
            }
        }
        return indice;
    }
}