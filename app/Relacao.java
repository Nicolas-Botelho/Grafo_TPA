package app;

public class Relacao {

    private Pessoa origem, destino;
    private int peso;

    public Relacao(Pessoa origem, Pessoa destino, int peso){
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public Pessoa getOrigem() {
        return origem;
    }

    public Pessoa getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return origem.getNome() + " → " + destino.getNome() + ": Peso = " + peso + ".";
    }
}
