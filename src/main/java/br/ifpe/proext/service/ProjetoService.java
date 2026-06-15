package br.ifpe.proext.service;

import br.ifpe.proext.enums.StatusProjeto;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.repository.ProjetoRepository;
import java.util.ArrayList;

public class ProjetoService {
    private ProjetoService(){}
    
    public static void cadastrarProjeto(Projeto projeto){
        validarOdsSelecionadas(projeto);
        ProjetoRepository.criarProjeto(projeto);
    }

    public static void atualizarProjeto(Projeto projeto) {
        validarOdsSelecionadas(projeto);
        Projeto projetoExistente = ProjetoRepository.buscarPorTitulo(projeto.getTitulo());
        
        if (projetoExistente == null) {
            throw new IllegalStateException("Projeto não encontrado.");
        }
        
        if (projetoExistente.getStatus() != StatusProjeto.RASCUNHO) {
            throw new IllegalStateException("O projeto deve estar em status RASCUNHO para ser atualizado.");
        }

        ProjetoRepository.atualizarProjeto(projeto);
    }

    public static ArrayList<Projeto> listarProjetos() {
        return ProjetoRepository.listarProjetos();
    }

    private static void validarOdsSelecionadas(Projeto projeto) {
        if (projeto.getOds() == null || projeto.getOds().isEmpty()) {
            throw new IllegalStateException("O projeto deve possuir ao menos uma ODS associada.");
        }
    }
}
