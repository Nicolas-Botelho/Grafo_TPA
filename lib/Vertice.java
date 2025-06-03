package lib;

import java.util.ArrayList;

public class Vertice<T> {
    private T valor;
    private ArrayList<Aresta<T>> arestaList;

    public Vertice(T val) {
        this.valor = val;
        this.arestaList = new ArrayList<>();
    }

    public T getValor() {
        return valor;
    }

    public void addAresta(Vertice<T> dest, double p) {
        Aresta<T> ares = getAresta(dest);

        if (ares == null) {
            Aresta<T> newAresta = new Aresta<>(dest, p);
            this.arestaList.add(newAresta);
        }

    }

    public Aresta<T> getAresta(Vertice<T> vert) {
        for (Aresta<T> a : this.arestaList) {
            if (a.getDestino().getValor().equals(vert.getClass())) return a;
        }

        return null;
    }
}
