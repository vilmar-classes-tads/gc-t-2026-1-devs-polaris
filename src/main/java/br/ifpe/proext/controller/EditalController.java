package br.ifpe.proext.controller;

import br.ifpe.proext.model.Edital;
import br.ifpe.proext.service.EditalService;


import java.util.List;

public class EditalController {

    private EditalController(){}

    public static void cadastrarEdital(Edital edital){

        try {
            EditalService.cadastrarEdital(edital);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void editarEdital(Edital edital) {

        EditalService.editarEdital(edital);

    }
    public static List<Edital> listarEditais() {

        return EditalService.listarEditais();

    }
}
