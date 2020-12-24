package br.com.banco.utils;

public class Caixa {

    private int id, nextId = 0;
    private String nome;

    public Caixa() {
    }

    public Caixa(String nome) {
        this.nome = nome;
        setId();
    }

    public int getId() {
        return id;
    }

    private void setId() {
        id = nextId;
        nextId++;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
