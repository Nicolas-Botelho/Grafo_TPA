package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeradorDePessoas {

    private static final int NUM_REGISTROS = 20;

    private static final String[] nomes = {
            "Ana", "Bruno", "Carlos", "Daniela", "Eduardo",
            "Fernanda", "Gabriel", "Helena", "Igor", "Juliana",
            "Karla", "Lucas", "Mariana", "Nathan", "Olívia",
            "Paulo", "Quésia", "Renan", "Sabrina", "Tiago",
            "Ursula", "Vinícius", "Wesley", "Xênia", "Yuri", "Zilda"
    };

    private static final String[] sobrenomes = {
            "Silva", "Souza", "Oliveira", "Santos", "Pereira",
            "Lima", "Carvalho", "Almeida", "Costa", "Fernandes",
            "Gomes", "Ribeiro", "Martins", "Rodrigues", "Barbosa",
            "Freitas", "Araújo", "Moura", "Dias", "Cavalcante",
            "Monteiro", "Cardoso", "Teixeira", "Rocha", "Andrade",
            "Nascimento", "Machado", "Moreira", "Fonseca", "Melo"
    };

    public static List<Pessoa> gerarPessoas(int NUM_REGISTROS){
        List<Pessoa> pessoas = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= NUM_REGISTROS; i++){
            String nome = getAleatorio(nomes, random);
            String sobrenome = getAleatorio(sobrenomes, random);
            Pessoa p = new Pessoa(i, nome, sobrenome);
            pessoas.add(p);
        }

        return pessoas;
    }

    public static String getAleatorio(String[] array, Random random){
        return array[random.nextInt(NUM_REGISTROS)];
    }

}
