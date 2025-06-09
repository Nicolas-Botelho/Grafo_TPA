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
            System.out.println("vertices marcados " + verticesMarcados);
            System.out.println("fila " + verticesFila);
        }



        // System.out.println(verticeAlvo.getValor());
        // verticesMarcados.add(verticeAlvo);
        // for (Aresta<T> ares : verticeAlvo.getArestaList()) verticesFila.add(ares.getDestino());

        // for (Vertice<T> alvo : verticesFila) {
        //     System.out.println(alvo.getValor());
        //     System.out.println("vertices impressos " + verticesMarcados.toString());
        //     System.out.println("vertices a imprimir " + verticesFila.toString());
        //     verticesMarcados.add(alvo);

        //     for (Aresta<T> ares : alvo.getArestaList()) {
        //         if (!verticesMarcados.contains(ares.getDestino()) && !verticesFila.contains(ares.getDestino())) verticesFila.add(ares.getDestino());
        //     }
        // }
    }

    /*
     * print vertice
     * adicionar vertice em uma lista de printados
     * listar vertices das suas arestas
     * verificar lista
     * print vertice
     */
}