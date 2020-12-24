package br.com.banco.utils;

public class Conta {

    private int id, idCliente, nextID = 0;
    boolean status = false;

    public Conta() {
    }

    public Conta(int idCliente, boolean status) {
        this.idCliente = idCliente;
        this.status = status;
        setId();
    }

    public int getId() {
        return id;
    }

    private void setId() {
        id = nextID;
        nextID++;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}
