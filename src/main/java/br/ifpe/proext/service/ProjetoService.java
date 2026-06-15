package br.ifpe.proext.service;

import br.ifpe.proext.enums.StatusProjeto;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.repository.ProjetoRepository;
import br.ifpe.proext.repository.ServidorRepository;

public class ProjetoService {
    private ProjetoService(){}
    
    public static void cadastrarProjeto(Projeto projeto){
        ProjetoRepository.criarProjeto(projeto);
    }

    public static void atualizarProjeto(Projeto projeto) {
        Projeto projetoExistente = ProjetoRepository.buscarPorTitulo(projeto.getTitulo());
        
        if (projetoExistente == null) {
            throw new IllegalStateException("Projeto não encontrado.");
        }
        
        if (projetoExistente.getStatus() != StatusProjeto.RASCUNHO) {
            throw new IllegalStateException("O projeto deve estar em status RASCUNHO para ser atualizado.");
        }

        ProjetoRepository.atualizarProjeto(projeto);
    }
}
