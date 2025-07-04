package models;

import java.util.Objects;

public class Pessoa {

    private Integer id;
    private String nome;

    public Pessoa(){
        this.id = null;
        this.nome = null;
    }

    public Pessoa(Integer id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " - " + nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pessoa)) return false;
        Pessoa outra = (Pessoa) o;
        return Objects.equals(this.id, outra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}