package br.com.banco.utils;

public class Banco {

    int idBanco, tempID = 0;
    String Nome, Endereco;

    public Banco(String Nome, String Endereco) {
        this.Nome = Nome;
        this.Endereco = Endereco;
        setIdBanco();
    }

    public int getIdBanco() {
        return idBanco;
    }

    private void setIdBanco() {
        idBanco = tempID;
        tempID++;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getEndereco() {
        return Endereco;
    }

    public void setEndereco(String Endereco) {
        this.Endereco = Endereco;
    }

    @Override
    public String toString() {
        return "Nome: " + Nome
                + "\nEndereço: " + Endereco;
    }

}
