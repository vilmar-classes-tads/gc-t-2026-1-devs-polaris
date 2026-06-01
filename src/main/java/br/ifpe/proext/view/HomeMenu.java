package br.ifpe.proext.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class HomeMenu {
    public static void exibir() {

        Scanner scanner = new Scanner(System.in);

        int opcao = -1;

        do {

            System.out.println("\n=== PROEXT ===");
            System.out.println("1 - Cadastrar Servidor");
            System.out.println("0 - Sair");

            try {

                opcao = scanner.nextInt();
                scanner.nextLine();

            } catch (InputMismatchException e) {

                System.out.println("Digite apenas números.");
                scanner.nextLine();

                continue;
            }

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
