package br.ifpe.proext.controller;

import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.service.ProjetoService;

public class ProjetoController {
    public static void cadastrarProjeto(Projeto projeto){
        try {
            ProjetoService.cadastrarProjeto(projeto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
