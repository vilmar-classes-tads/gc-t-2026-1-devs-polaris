package br.ifpe.proext.controller;

import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.ProjetoService;
import java.util.ArrayList;

public class ProjetoController {
    public static void cadastrarProjeto(Servidor usuario, Projeto projeto){
        try {
            ProjetoService.cadastrarProjeto(usuario, projeto);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void atualizarProjeto(Servidor usuario, Projeto projeto){
        try {
            ProjetoService.atualizarProjeto(usuario, projeto);
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

    public static void submeterProjeto(Servidor usuario, Projeto projeto){
        try {
            ProjetoService.submeterProjeto(usuario, projeto);
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

    public static Projeto visualizarProjeto(String titulo){
        try {
            return ProjetoService.buscarProjetoParaVisualizacao(titulo);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static Projeto buscarProjetoParaEdicao(String titulo){
        try {
            return ProjetoService.buscarProjetoParaEdicao(titulo);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
