package br.ifpe.proext.controller;

import br.ifpe.proext.model.Edital;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.EditalService;

import java.util.List;

public class EditalController {

    private EditalController(){}

    public static Edital buscarEdital(Servidor servidor, int numero, int ano) {

        try {
            return EditalService.buscarEdital(servidor, numero, ano);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean cadastrarEdital(Servidor servidor, Edital edital) {

        try {
            EditalService.cadastrarEdital(servidor, edital);
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static boolean editarEdital(Servidor servidor, Edital edital) {

        try {
            EditalService.editarEdital(servidor, edital);
            return true;
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static List<Edital> listarEditais(Servidor servidor) {

        try {
            return EditalService.listarEditais(servidor);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}