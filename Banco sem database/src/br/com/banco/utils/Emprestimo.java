package br.com.banco.utils;

public class Emprestimo {

    private int id, idConta, idCliente, nextId = 0;
    private String tipo;
    private double valor;

    public Emprestimo() {
    }

    public Emprestimo(int idConta, int idCliente, String tipo, double valor) {
        this.idCliente = idCliente;
        this.idConta = idConta;
        this.tipo = tipo;
        this.valor = valor;
        setId();
    }

    public int getId() {
        return id;
    }

    private void setId() {
        id = nextId;
        nextId++;
    }

    public int getIdConta() {
        return idConta;
    }

    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
