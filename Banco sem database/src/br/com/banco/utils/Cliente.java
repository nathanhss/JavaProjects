package br.com.banco.utils;

import br.com.banco.data.DataBase;
import java.util.ArrayList;

public class Cliente {

    private int id, tempID = 0;
    private String nome, NoConta, endereco, telefone, senha;
    private double saldo = 0, limite = 3000;
    private double valorEmprestimo = 0;

    private Conta conta;
    private DataBase db;

    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public Cliente(String nome, String endereco, String telefone, String NoConta, String senha, DataBase db) {
        this.nome = nome;
        this.NoConta = NoConta;
        this.endereco = endereco;
        this.telefone = telefone;
        this.senha = senha;
        this.db = db;
        setId();
        AbrirConta();
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    private void setId() {
        id = tempID;
        tempID++;
    }

    public String getNoConta() {
        return NoConta;
    }

    public void setNoConta(String NoConta) {
        this.NoConta = NoConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    @Override
    public String toString() {
        return "ID: " + id
                + "\nNúmero da conta: " + NoConta
                + "\nNOME: " + nome
                + "\nENDEREÇO: " + endereco
                + "\nTELEFONE: " + telefone;
    }

    public boolean Depositar(double valor) {
        if (valor > 0) {
            saldo += valor;
            return true;
        }
        return false;
    }

    public boolean Sacar(double valorSaque) {
        if (valorSaque > 0 && saldo > valorSaque) {
            saldo -= valorSaque;
            System.out.println("Saque de " + valorSaque + " realizado");
            System.out.println("Valor restante: " + saldo);
            return true;
        }
        return false;
    }

    private void AbrirConta() {
        conta = new Conta(id, true);
        db.getContas().add(conta);
        System.out.println("Conta cria!");
    }

    public boolean EncerrarConta() {
        if (conta.isStatus()) {
            conta.setStatus(false);
            return true;
        }
        return false;
    }

    public void VerificaSaldo() {
        System.out.println("Saldo em conta: R$" + saldo);
        if (valorEmprestimo > 0) {
            System.out.println("Saldo do emprestimo: R$" + valorEmprestimo);
        }

    }
}
