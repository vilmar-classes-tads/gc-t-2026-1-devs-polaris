package br.ifpe.proext.controller;

import br.ifpe.proext.model.FiltroProjeto;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.ProjetoListagemService;

import java.util.List;

public class ProjetoListagemController {
    public static List<Projeto> listarProjetos(Servidor usuario, FiltroProjeto filtro) {
        try {
            return ProjetoListagemService.listarProjetos(usuario, filtro);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> baixarAnexos(Servidor usuario, Projeto projeto) {
        try {
            return ProjetoListagemService.baixarAnexos(usuario, projeto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
