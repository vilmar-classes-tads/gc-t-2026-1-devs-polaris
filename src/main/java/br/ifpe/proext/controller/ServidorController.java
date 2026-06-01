package br.ifpe.proext.controller;

import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.ServidorService;

public class ServidorController {

    private ServidorController(){}

    public static void cadastrarServidor(Servidor servidor){

        try {
            ServidorService.cadastrarServidor(servidor);
            System.out.println("Servidor foi cadastrado com sucesso!");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
