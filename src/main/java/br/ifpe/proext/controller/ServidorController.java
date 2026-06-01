package br.ifpe.proext.controller;

import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.ServidorService;

public class ServidorController {

    private ServidorController(){}

    public static void cadastrarServidor(Servidor servidor){
        ServidorService.cadastrarServidor(servidor);
    }
}
