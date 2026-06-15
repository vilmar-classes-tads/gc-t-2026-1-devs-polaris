package br.ifpe.proext.controller;

import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.ProjetoService;
import java.util.ArrayList;

public class ProjetoController {
    public static void cadastrarProjeto(Projeto projeto){
        try {
            ProjetoService.cadastrarProjeto(projeto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void atualizarProjeto(Projeto projeto){
        try {
            ProjetoService.atualizarProjeto(projeto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void registrarAceiteTermo(Projeto projeto, Servidor coordenador){
        try {
            ProjetoService.registrarAceiteTermo(projeto, coordenador);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void submeterProjeto(Projeto projeto){
        try {
            ProjetoService.submeterProjeto(projeto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<Projeto> listarProjetos(){
        try {
            return ProjetoService.listarProjetos();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
