package br.ifpe.proext.service;

import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.repository.ProjetoRepository;
import br.ifpe.proext.repository.ServidorRepository;

public class ProjetoService {
    private ProjetoService(){}
    
    public static void cadastrarProjeto(Projeto projeto){
        ProjetoRepository.criarProjeto(projeto);
    }
}
