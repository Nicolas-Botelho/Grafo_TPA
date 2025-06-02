package app;

public class Pessoa {

    private Integer id;
    private String nome, sobrenome;

    public Pessoa(){
        this.id = null;
        this.nome = null;
        this.sobrenome = null;
    }

    public Pessoa(Integer id, String nome, String sobrenome){
        this.id = id;
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + nome + " " + sobrenome + ".";
    }
}
