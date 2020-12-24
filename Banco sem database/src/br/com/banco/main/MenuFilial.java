package br.com.banco.main;

import br.com.banco.data.DataBase;
import br.com.banco.utils.Caixa;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuFilial {

    private Scanner sc = new Scanner(System.in);
    private ArrayList<Caixa> caixas = new ArrayList();
    private DataBase dataBase = null;
    private String nomeFilial;

    public MenuFilial(String nomeFilial, DataBase dataBase) {
        this.nomeFilial = nomeFilial;
        this.dataBase = dataBase;
        showMenu();
    }

    public MenuFilial() {
        showMenu();
    }

    private void CadastraCaixa() {
        sc.nextLine();
        System.out.println("Encontre com o nome do(a) caixa: ");
        String nome = sc.nextLine();
        Caixa caixa = new Caixa(nome);
        caixas.add(caixa);
    }

    private void showMenu() {
        int op;
        caixas = dataBase.getCaixas();
        do {
            System.out.println("|----- Filial: " + nomeFilial + " -----|");
            System.out.println("1. Cadastrar caixa");
            System.out.println("2. Acessar como caixa");
            System.out.println("0. Voltar");
            op = sc.nextInt();

            switch (op) {
                case 0: {
                    Main main = new Main();
                    break;
                }

                case 1: {
                    CadastraCaixa();
                    break;
                }

                case 2: {
                    sc.nextLine();
                    System.out.println("Nome Caixa: ");
                    String nome = sc.nextLine();
                    for (int i = 0; i < caixas.size(); i++) {
                        if (nome.equals(caixas.get(i).getNome())) {
                            CaixaMenu cm = new CaixaMenu(caixas.get(i).getId(), dataBase, nomeFilial);
                        }
                    }
                    System.out.println("Caixa não encontrado");
                    break;
                }

                default:
                    System.out.println("opção invalida");
                    break;
            }
        } while (op != 0);
    }
}
