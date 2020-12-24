package br.com.banco.data;

import br.com.banco.utils.Banco;
import br.com.banco.utils.Caixa;
import br.com.banco.utils.Cliente;
import br.com.banco.utils.Conta;

import java.util.ArrayList;

public class DataBase {

    private ArrayList<Cliente> clientes = new ArrayList();
    private ArrayList<Banco>   bancos   = new ArrayList(); //filias 
    private ArrayList<Caixa>   caixas   = new ArrayList(); //caixas
    private ArrayList<Conta>   contas   = new ArrayList();

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ArrayList<Banco> getBancos() {
        return bancos;
    }

    public void setBancos(ArrayList<Banco> bancos) {
        this.bancos = bancos;
    }

    public ArrayList<Caixa> getCaixas() {
        return caixas;
    }

    public void setCaixas(ArrayList<Caixa> caixas) {
        this.caixas = caixas;
    }

    public ArrayList<Conta> getContas() {
        return contas;
    }

    public void setContas(ArrayList<Conta> contas) {
        this.contas = contas;
    }

    public Cliente SelectCliente(String NoConta) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNoConta().equals(NoConta)) {
                return clientes.get(i);
            }
        }
        return null;
    }

    public Caixa SelectCaixa(String NomeCaixa) {
        for (int i = 0; i < caixas.size(); i++) {
            if (caixas.get(i).getNome().equals(NomeCaixa)) {
                return caixas.get(i);
            }
        }
        return null;
    }

    public Banco SelectBanco(String NomeBanco) {
        for (int i = 0; i < bancos.size(); i++) {
            if (bancos.get(i).getNome().equals(NomeBanco)) {
                return bancos.get(i);
            }
        }
        return null;
    }

    public Conta SelectConta(int idConta) {
        for (int i = 0; i < contas.size(); i++) {
            if (contas.get(i).getIdCliente() == idConta) {
                return contas.get(i);
            }
        }
        return null;
    }

    public String getNomeBanco(int idBanco) {
        for (int i = 0; i < bancos.size(); i++) {
            if (bancos.get(i).getIdBanco() == idBanco) {
                return bancos.get(i).getNome();
            }
        }
        return null;
    }

    public String getNomeCaixa(int idCaixa) {
        for (int i = 0; i < caixas.size(); i++) {
            if (caixas.get(i).getId() == idCaixa) {
                return caixas.get(i).getNome();
            }
        }
        return null;
    }

    public boolean verificaCliente(String NoConta) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getNoConta().equals(NoConta)) {
                return true;
            }
        }
        return false;
    }
}
