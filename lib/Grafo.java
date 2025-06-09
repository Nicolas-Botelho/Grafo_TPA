package lib;

import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> verticeList;

    public Grafo() {
        this.verticeList = new ArrayList<>();
    }

    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> vert = getVertice(valor);
        if (vert != null) {
            return vert;
        }
        
        Vertice<T> newVert = new Vertice<>(valor);
        this.verticeList.add(newVert);
        return newVert;
    }

    public boolean adicionarAresta(T orig, T dest, double p) {
        Vertice<T> verticeOrigem = getVertice(orig);
        Vertice<T> verticeDestino = getVertice(dest);
        
        if (verticeOrigem == null || verticeDestino == null) {
            return false;
        }

        verticeOrigem.addAresta(verticeDestino, p);
        return true;
    }

    public ArrayList<Vertice<T>> getVerticeList() {
        return this.verticeList;
    }

    public Vertice<T> getVertice(T valor) {
        for (Vertice<T> v : this.verticeList) {
            if (v.getValor().equals(valor)) return v;
        }

        return null;
    }

    public void caminhoEmLargura() {
        System.out.println("  O\n" + " /|\\\n" + "  |\n" + " / \\");

        ArrayList<Vertice<T>> verticesImpressos = new ArrayList<>();
        ArrayList<Vertice<T>> verticesAImprimir = new ArrayList<>();

        Vertice<T> verticeAlvo = this.getVerticeList().getFirst();

        System.out.println(verticeAlvo.getValor());
        verticesImpressos.add(verticeAlvo);
        for (Aresta<T> ares : verticeAlvo.getArestaList()) verticesAImprimir.add(ares.getDestino());

        for (Vertice<T> alvo : verticesAImprimir) {
            System.out.println(alvo.getValor());
            System.out.println("vertices impressos " + verticesImpressos.toString());
            System.out.println("vertices a imprimir " + verticesAImprimir.toString());
            verticesImpressos.add(alvo);

            for (Aresta<T> ares : alvo.getArestaList()) {
                if (!verticesImpressos.contains(ares.getDestino()) && !verticesAImprimir.contains(ares.getDestino())) verticesAImprimir.add(ares.getDestino());
            }
        }
    }

    /*
     * print vertice
     * adicionar vertice em uma lista de printados
     * listar vertices das suas arestas
     * verificar lista
     * print vertice
     */
}