package br.com.banco.main;

import br.com.banco.data.DataBase;
import br.com.banco.utils.Cliente;
import br.com.banco.utils.Conta;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteMenu {

    private Scanner sc = new Scanner(System.in);
    private String NoConta, Senha;
    private DataBase dataBase;
    private Cliente cliente;
    private Conta conta;
    
    ArrayList<Cliente> clientes = new ArrayList();

    public ClienteMenu(DataBase dataBase) {
        this.dataBase = dataBase;
        if(!dataBase.getClientes().isEmpty())
            cliente = dataBase.getClientes().get(dataBase.getClientes().size()-1);
        preMenu();
    }
    
    
    private void preMenu(){
        System.out.println("|----- Menu do Cliente -----|");
        System.out.println("1. Acessar conta");
        System.out.println("2. Criar conta");
        System.out.println("0. Voltar");
        int op = sc.nextInt();
        switch (op) {
            case 1:
                VerificaCredenciais();
                break;
            case 2:
                CadastraCliente();
                showMenu();
                break;
            default:
                Main main = new Main();
                break;
        }
    }

    private void VerificaCredenciais() {
        int op = 0;

        do {
            System.out.println("|----- Login Cliente -----|");
//            sc.nextLine();
            System.out.print("Número da conta: ");
            NoConta = sc.nextLine();
//            sc.nextLine();
            System.out.print("Senha: ");
            Senha = sc.nextLine();
            cliente = dataBase.SelectCliente(NoConta);
            
            if (cliente != null) {
                if (cliente.getSenha().equals(Senha)) {
                    op = 0;
                    showMenu();
                } else {
                    System.out.println("Senha inválida");
                    op++;
                }
            } else {
                System.out.println("Usuário inválido");
                op++;
            }
            System.out.println("Tentativas restantes: " + (3 - op));
        } while (op < 3);
        System.out.println("Tentativas excedidas, retornando ao menu...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ClienteMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        preMenu();
    }

    private void showMenu() {
        int op;
        do {
            System.out.println("|----- Bem vindo -----|");
            System.out.println("Usuário: " + cliente.getNome());
            System.out.println("Conta: " + cliente.getNoConta());
            System.out.println("1. Depositar");
            System.out.println("2. Sacar");
            System.out.println("3. Verificar saldo");
            System.out.println("4. Pagar emprestimo");
            System.out.println("0. Sair");
            op = sc.nextInt();

            switch (op) {
                case 0: {
                    preMenu();
                    break;
                }

                case 1: {
                    System.out.println("Valor deposito: ");
                    double valor = sc.nextDouble();
                    cliente.Depositar(valor);
                    break;
                }

                case 2: {
                    System.out.println("Valor saque: ");
                    double valor = sc.nextDouble();
                    cliente.Sacar(valor);
                    break;
                }

                case 3: {
                    cliente.VerificaSaldo();
                    break;
                }

                case 4: {
                    System.out.println("Valor em débito: " + cliente.getValorEmprestimo());
                    System.out.println("Valor a ser pago:");
                    double valorEmprestimo = sc.nextDouble();
                    if (valorEmprestimo > cliente.getValorEmprestimo()) {
                        System.out.println("Valor maior que o devedor");
                    } else {
                        cliente.setValorEmprestimo((cliente.getValorEmprestimo() - valorEmprestimo));
                    }
                    break;
                }

                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        } while (op != 0);
    }
    
    private void CadastraCliente() {
        sc.nextLine();
        System.out.print("Nome completo: ");
        String nomeCliente = sc.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        System.out.print("Numero da Conta: ");
        NoConta = sc.nextLine();

        System.out.print("Digite um senha: ");
        String senha = sc.nextLine();
        cliente = new Cliente(nomeCliente, endereco, telefone, NoConta, senha, dataBase);
        
        dataBase.getClientes().add(cliente);
               
        
        if (dataBase.verificaCliente(NoConta)) {
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println("Informações de cadastro:");
            System.out.println("" + cliente.toString());
        } else {
            System.out.println("Erro ao cadastrar!");
        }
    }
}
