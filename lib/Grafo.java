package lib;

import java.util.ArrayList;

public class Grafo<T> {
    private ArrayList<Vertice<T>> verticeList;
    private boolean direcionado;
    private boolean ponderado;

    public Grafo() {
        this.verticeList = new ArrayList<>();
        this.direcionado = true;
        this.ponderado = true;
    }

    public Grafo(boolean dir, boolean pon) {
        this.verticeList = new ArrayList<>();
        this.direcionado = dir;
        this.ponderado = pon;
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

        if (this.ponderado) {
            verticeOrigem.addAresta(verticeDestino, p);
            if (!this.direcionado) verticeDestino.addAresta(verticeOrigem, p);
        } else {
            verticeOrigem.addAresta(verticeDestino, 1);
            if (!this.direcionado) verticeDestino.addAresta(verticeOrigem, 1);
        }
        
        return true;
    }

    public boolean adicionarAresta(T orig, T dest) {
        Vertice<T> verticeOrigem = getVertice(orig);
        Vertice<T> verticeDestino = getVertice(dest);
        
        if (verticeOrigem == null || verticeDestino == null) {
            return false;
        }

        verticeOrigem.addAresta(verticeDestino, 1);
        if (!this.direcionado) verticeDestino.addAresta(verticeOrigem, 1);
        
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
        ArrayList<Vertice<T>> verticesMarcados = new ArrayList<>();
        ArrayList<Vertice<T>> verticesFila = new ArrayList<>();

        verticesFila.add(this.getVerticeList().getFirst());

        Vertice<T> verticeAlvo;

        while (!verticesFila.isEmpty()) {
            verticeAlvo = verticesFila.removeFirst();   //Remove vertice da fila
            System.out.println(verticeAlvo.toString()); //Imprime o vertice
            verticesMarcados.add(verticeAlvo);          //Adiciona o vertice como marcado (já foi impresso)

            // Adiciona na fila os vertices ligados ao Alvo desde que não estejam marcados e nem na fila
            for (Aresta<T> ares : verticeAlvo.getArestaList()) {
                Vertice<T> verticeDest = ares.getDestino();
                if (!verticesFila.contains(verticeDest) && !verticesMarcados.contains(verticeDest)) verticesFila.add(verticeDest);
            }
        }
    }

    public ArrayList<T> getAdjacentes(T valor) {
        Vertice<T> vertice = getVertice(valor);
        ArrayList<T> adjacentes = new ArrayList<>();
        
        if (vertice != null) {
            for (Aresta<T> aresta : vertice.getArestaList()) {
                adjacentes.add(aresta.getDestino().getValor());
            }
        }
        
        return adjacentes;
    }
}