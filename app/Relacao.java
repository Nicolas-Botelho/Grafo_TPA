package app;

public class Relacao {

    private Pessoa p1, p2;
    private int peso;

    public Relacao(Pessoa p1, Pessoa p2, int peso){
        this.p1 = p1;
        this.p2 = p2;
        this.peso = peso;
    }

    public Pessoa getP1() {
        return p1;
    }

    public Pessoa getP2() {
        return p2;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return "Relação: " + p1.getNome() + " " + p1.getSobrenome() + " -[" + peso + "]- " + p2.getNome() + " " + p2.getSobrenome() + ".";
    }
}
