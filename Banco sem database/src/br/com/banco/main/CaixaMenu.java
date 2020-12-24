package br.com.banco.main;

import br.com.banco.data.DataBase;
import br.com.banco.utils.Cliente;
import br.com.banco.utils.Conta;
import br.com.banco.utils.Emprestimo;
import java.util.ArrayList;
import java.util.Scanner;

public class CaixaMenu {

    private final int idCaixa;
    private final Scanner sc = new Scanner(System.in);
    private DataBase dataBase = null;
    private Cliente cliente = null;
    private Conta conta;
    private Emprestimo emprestimo;
    private String nomeFilial;
    
    public ArrayList<Cliente> clientes = new ArrayList();
    public ArrayList<Conta> contas = new ArrayList();
    public ArrayList<Emprestimo> emprestimos = new ArrayList();
  
    public CaixaMenu(int idCaixa, DataBase dataBase, String nomeFilial) {
        this.idCaixa = idCaixa;
        this.dataBase = dataBase;
        this.nomeFilial = nomeFilial;
        showMenu();
    }

    private void showMenu() {
        int op;
        clientes = dataBase.getClientes();
        do {
            System.out.println("\n|----- Caixa " + dataBase.getNomeCaixa(idCaixa) + " -----|");
            System.out.println("1. Resgatar Dinheiro");
            System.out.println("2. Abrir Conta");
            System.out.println("3. Encerrar Conta");
            System.out.println("4. Pedir Emprestimo");
            System.out.println("0. Voltar");
            op = sc.nextInt();

            switch (op) {
                case 0: {
                    MenuFilial filial = new MenuFilial(nomeFilial, dataBase);
                    break;
                }

                case 1: {
                    sc.nextLine();
                    System.out.println("Numero da conta: ");
                    String NoConta = sc.nextLine();
                    cliente = VerificaCliente(NoConta);
                    if (cliente != null) {
                        ResgatarDinheiro(cliente);
                    } else {
                        System.out.println("Cliente inexistente");
                        System.out.println("Deseja cadastrar?");
                        System.out.println("1. Sim | 2. Não");
                        int cad = sc.nextInt();
                        if (cad == 1) {
                            CadastraCliente();
                        }
                    }
                    cliente = null;
                    break;
                }

                case 2: {
                    CadastraCliente();
                    cliente = null;
                    break;
                }

                case 3: {
                    sc.nextLine();
                    System.out.println("Numero da conta: ");
                    String NoConta = sc.nextLine();
                    cliente = VerificaCliente(NoConta);
                    if (cliente != null) {
                        cliente.EncerrarConta();
                    } else {
                        System.out.println("Usuário inválido");
                    }
                    cliente = null;
                    break;
                }

                case 4: {
                    sc.nextLine();
                    System.out.println("Numero da conta: ");
                    String NoConta = sc.nextLine();
                    cliente = VerificaCliente(NoConta);
                    if (cliente != null) {
                        if (solicitaEmprestimo(cliente)) {
                            System.out.println("Pedido realizado com sucesso");
                        } else {
                            System.out.println("");
                        }
                    } else {
                        System.out.println("Usuário inválido");
                    }
                    cliente = null;
                    break;
                }

                default: {
                    System.out.println("Opção inválida");
                    break;
                }
            }
        } while (op != 0);
    }

    private void ResgatarDinheiro(Cliente cliente) {
        System.out.println("Valor do saque: ");
        double valor = sc.nextDouble();
        if(!cliente.Sacar(valor)){
            System.out.println("Valor em para saque superior ao em conta.");
        }
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
        String NoConta = sc.nextLine();

        System.out.print("Digite um senha: ");
        String senha = sc.nextLine();
        cliente = new Cliente(nomeCliente, endereco, telefone, NoConta, senha, dataBase);
        
        clientes.add(cliente);
        
        if (dataBase.verificaCliente(NoConta)) {
            System.out.println("Cliente cadastrado com sucesso!");
            System.out.println("Informações de cadastro:");
            System.out.println("" + cliente.toString());
        } else {
            System.out.println("Erro ao cadastrar!");
        }
    }
    
    private void abrirConta(Cliente cliente){
        conta = dataBase.getContas().get(dataBase.getContas().size());
        contas = dataBase.getContas();
        conta = new Conta(cliente.getId(), true);
        contas.add(conta);
    }
    
    private Cliente VerificaCliente(String NoConta) {
        if (!clientes.isEmpty()) {
            for (int i = 0; i < clientes.size(); i++) {
                if (clientes.get(i).getNoConta().equals(NoConta)) {
                    return clientes.get(i);
                }
            }
        }
        return null;
    }
    
    public boolean solicitaEmprestimo(Cliente cliente) {
        Scanner scan = new Scanner(System.in);
        int op;
        double valorEmprestimo;
        do {
            System.out.println("Selecione o tipo de empréstimo: ");
            System.out.println("1. Empréstimo pessoal");
            System.out.println("2. Empréstimo pessoal com garantia");
            System.out.println("3. Empréstimo consignado");
            System.out.println("4. Empréstimo rotativo");
            System.out.println("5. Cheque especial");
            System.out.println("6. Refinanciamento de imóvel");
            System.out.println("7. Antecipação da restituição do Imposto de Renda");
            System.out.println("8. Antecipação do 13º salário");
            System.out.println("0. Voltar");
            System.out.print("Opção: ");
            op = scan.nextInt();

            switch (op) {
                case 1: {
                    System.out.println("Valor do emprestimo: ");
                    valorEmprestimo = scan.nextDouble();
                    if (valorEmprestimo != cliente.getLimite() * 3) {
                        Emprestimo emp = new Emprestimo(conta.getId(), cliente.getId(), "Empréstimo pessoal", valorEmprestimo);
                        emprestimos.add(emp);
                        return true;
                    } else {
                        System.out.println("Valor incompativel com o permitido");
                    }
                    break;
                }

                case 2: {
                    System.out.println("Valor do emprestimo: ");
                    valorEmprestimo = scan.nextDouble();
                    if (valorEmprestimo != cliente.getLimite() * 3) {
                        Emprestimo emp = new Emprestimo(conta.getId(), cliente.getId(), "Empréstimo pessoal com garantia", valorEmprestimo);
                        emprestimos.add(emp);
                        return true;
                    } else {
                        System.out.println("Valor incompativel com o permitido");
                    }
                    break;
                }

                case 3: {
                    System.out.println("Valor do emprestimo: ");
                    valorEmprestimo = scan.nextDouble();
                    if (valorEmprestimo != cliente.getLimite() * 3) {
                        Emprestimo emp = new Emprestimo(conta.getId(), cliente.getId(), "Empréstimo consignado", valorEmprestimo);
                        emprestimos.add(emp);
                        return true;
                    } else {
                        System.out.println("Valor incompativel com o permitido");
                    }
                    break;
                }

                case 4: {
                    System.out.println("Valor do emprestimo: ");
                    valorEmprestimo = scan.nextDouble();
                    if (valorEmprestimo != cliente.getLimite() * 3) {
                        Emprestimo emp = new Emprestimo(conta.getId(), cliente.getId(), "Empréstimo rotativo", valorEmprestimo);
                        emprestimos.add(emp);
                        return true;
                    } else {
                        System.out.println("Valor incompativel com o permitido");
                    }
                    break;
                }

                case 5: {
                    System.out.println("Valor do emprestimo: ");
                    valorEmprestimo = scan.nextDouble();
                    if (valorEmprestimo != cliente.getLimite() * 3) {
                        Emprestimo emp = new Emprestimo(conta.getId(), cliente.getId(), "Cheque especial", valorEmprestimo);
                        emprestimos.add(emp);
                        return true;
                    } else {
                        System.out.println("Valor incompativel com o permitido");
                    }
                    break;
                }

                case 6: {
                    System.out.println("Valor do emprestimo: ");
                    valorEmprestimo = scan.nextDouble();
                    if (valorEmprestimo != cliente.getLimite() * 3) {
                        Emprestimo emp = new Emprestimo(conta.getId(), cliente.getId(), "Refinanciamento de imóvel", valorEmprestimo);
                        emprestimos.add(emp);
                        return true;
                    } else {
                        System.out.println("Valor incompativel com o permitido");
                    }
                    break;
                }

                case 7: {
                    System.out.println("Valor do emprestimo: ");
                    valorEmprestimo = scan.nextDouble();
                    if (valorEmprestimo != cliente.getLimite() * 3) {
                        Emprestimo emp = new Emprestimo(conta.getId(), cliente.getId(), "Antecipação da restituição do Imposto de Renda", valorEmprestimo);
                        emprestimos.add(emp);
                        return true;
                    } else {
                        System.out.println("Valor incompativel com o permitido");
                    }
                    break;
                }

                case 8: {
                    System.out.println("Valor do emprestimo: ");
                    valorEmprestimo = scan.nextDouble();
                    if (valorEmprestimo != cliente.getLimite() * 3) {
                        Emprestimo emp = new Emprestimo(conta.getId(), cliente.getId(), "Antecipação do 13º salário", valorEmprestimo);
                        emprestimos.add(emp);
                        return true;
                    } else {
                        System.out.println("Valor incompativel com o permitido");
                    }
                    break;
                }

                default: {
                    System.out.println("opção invalida");
                    break;
                }
            }
        } while (op != 0);
        return false;
    }
}
