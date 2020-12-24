package br.com.banco.main;

import br.com.banco.data.DataBase;
import br.com.banco.utils.Banco;
import java.io.IOException;

import java.util.Scanner;
import static java.lang.System.in;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(in);
        DataBase dataBase = new DataBase();
        int op;

        do {
            try {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } catch (InterruptedException | IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("|----- Sistema Banco -----|\n");
            System.out.println("1. Cadastrar filial");
            System.out.println("2. Acessar filial");
            System.out.println("3. Acessar como cliente");
            System.out.println("0. Sair");
            op = sc.nextInt();

            switch (op) {

                case 1: {
                    sc.nextLine();
                    System.out.print("Nome Filial: ");
                    String nomeFilial = sc.nextLine();
                    //sc.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = sc.nextLine();
                    Banco b = new Banco(nomeFilial, endereco);
                    dataBase.getBancos().add(b);
                    System.out.println("banco cadastrado:");
                    System.out.println("" + b.toString());
                    break;
                }

                case 2: {
                    if (!dataBase.getBancos().isEmpty()) {
                        sc.nextLine();
                        System.out.println("Nome da filial: ");
                        String nome = sc.nextLine();

                        for (int i = 0; i < dataBase.getBancos().size(); i++) {
                            if (nome.equals(dataBase.getBancos().get(i).getNome())) {
                                MenuFilial filial = new MenuFilial(dataBase.getBancos().get(i).getNome(), dataBase);
                            }
                        }
                        System.out.println("Filial não encontrada!");
                    } else {
                        System.out.println("Nenhuma filial cadastrada até o momento");
                    }
                    break;
                }

                case 3: {
                    ClienteMenu cm = new ClienteMenu(dataBase);
                    break;
                }

                case 0: {
                    System.out.println("Até mais!");
                    System.exit(0);
                    break;
                }

                default: {
                    System.out.println("Opção invalida");
                    break;
                }
            }
        } while (op != 0);
    }
}
