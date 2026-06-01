package br.ifpe.proext.view;

import br.ifpe.proext.controller.ServidorController;
import br.ifpe.proext.model.Servidor;

import java.util.Scanner;

public class CadastroServidorMenu {
    public static void exibir() {

        Scanner scanner = new Scanner(System.in);

        Servidor servidor = new Servidor();

        System.out.println("\n=== Cadastro de Servidor ===");

        System.out.print("Nome: ");
        servidor.setNome(scanner.nextLine());

        System.out.print("CPF: ");
        servidor.setCpf(scanner.nextLine());

        System.out.print("Email: ");
        servidor.setEmail(scanner.nextLine());

        System.out.print("Senha: ");
        servidor.setSenhaHash(scanner.nextLine());

        System.out.print("Campus: ");
        servidor.setCampus(scanner.nextLine());

        System.out.print("Area de Formacao: ");
        servidor.setAreaFormacao(scanner.nextLine());

        ServidorController.cadastrarServidor(servidor);

        System.out.println("\nServidor cadastrado com sucesso!");
    }
}
