package br.ifpe.proext.controller;

import br.ifpe.proext.model.Edital;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.EditalService;
import br.ifpe.proext.service.ServidorService;

import java.util.List;

public class EditalController {

    private EditalController(){}

    public static void cadastrarEdital(Edital edital, Servidor usuarioLogado){

        try {
            EditalService.cadastrarEdital(edital, usuarioLogado);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            throw e; // Repassa para o teste capturar
        }
    }

    public static void editarEdital(Edital edital, Servidor usuarioLogado) {

        EditalService.editarEdital(edital, usuarioLogado);

    }
    public static List<Edital> listarEditais(Servidor usuarioLogado) {

        return EditalService.listarEditais(usuarioLogado);

    }
}
