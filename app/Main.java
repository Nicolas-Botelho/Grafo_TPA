package app;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Pessoa> pessoas = GeradorDePessoas.gerarPessoas(20);

        for (Pessoa p: pessoas){
            System.out.println(p);
        }
    }
}
