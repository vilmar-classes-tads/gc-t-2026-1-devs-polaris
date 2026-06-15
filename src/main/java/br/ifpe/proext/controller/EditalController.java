package br.ifpe.proext.controller;

import br.ifpe.proext.model.Edital;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.EditalService;

import java.util.List;

public class EditalController {

    private EditalController(){}

    public static void cadastrarEdital(Edital edital, Servidor servidor){

        try {
            EditalService.cadastrarEdital(edital, servidor);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static void editarEdital(Edital edital, Servidor servidor) {

        EditalService.editarEdital(edital, servidor);

    }

    public static List<Edital> listarEditais(Servidor servidor) {

        return EditalService.listarEditais(servidor);

    }
}
