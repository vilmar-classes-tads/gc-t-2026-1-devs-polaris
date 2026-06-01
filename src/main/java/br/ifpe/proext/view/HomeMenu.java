package br.ifpe.proext.view;

import java.util.Scanner;

public class HomeMenu {
    public static void exibir() {

        Scanner scanner = new Scanner(System.in);

        int opcao;

        do {

            System.out.println("\n=== PROEXT ===");
            System.out.println("1 - Cadastrar Servidor");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    CadastroServidorMenu.exibir();
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println("Opcao invalida.");

            }

        } while (opcao != 0);
    }
}
