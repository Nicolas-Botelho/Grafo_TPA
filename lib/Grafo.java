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
        // imprimir o caminho em altura
    }
}
//  O
// /|\
//  |
// / \