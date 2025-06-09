package lib;

public class Aresta<T> {
    private Vertice<T> destino;
    private double peso;

    public Aresta(Vertice<T> dest, double p) {
        this.destino = dest;
        this.peso = p;
    }

    public double getPeso() {
        return peso;
    }

    public Vertice<T> getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return this.getDestino().toString() + " - " + this.getPeso();
    }
}
