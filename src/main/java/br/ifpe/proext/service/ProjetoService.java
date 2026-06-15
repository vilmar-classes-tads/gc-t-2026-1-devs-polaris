package br.ifpe.proext.service;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.enums.StatusProjeto;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
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

    public static void registrarAceiteTermo(Projeto projeto, Servidor coordenador) {
        Projeto projetoExistente = ProjetoRepository.buscarPorTitulo(projeto.getTitulo());

        if (projetoExistente == null) {
            throw new IllegalStateException("Projeto não encontrado.");
        }

        validarCoordenador(coordenador);

        if (projetoExistente.getStatus() != StatusProjeto.RASCUNHO) {
            throw new IllegalStateException("O aceite do termo só pode ser registrado em projetos RASCUNHO.");
        }

        projetoExistente.setTermoAceito(true);
        projetoExistente.setCoordenadorAceite(coordenador);
        ProjetoRepository.atualizarProjeto(projetoExistente);
    }

    public static void submeterProjeto(Projeto projeto) {
        Projeto projetoExistente = ProjetoRepository.buscarPorTitulo(projeto.getTitulo());

        if (projetoExistente == null) {
            throw new IllegalStateException("Projeto não encontrado.");
        }

        if (!projetoExistente.isTermoAceito()) {
            throw new IllegalStateException("O projeto só pode ser submetido após o aceite do termo pelo coordenador.");
        }

        if (projetoExistente.getStatus() != StatusProjeto.RASCUNHO) {
            throw new IllegalStateException("Somente projetos em status RASCUNHO podem ser submetidos.");
        }

        projetoExistente.setStatus(StatusProjeto.SUBMETIDO);
        ProjetoRepository.atualizarProjeto(projetoExistente);
    }

    public static ArrayList<Projeto> listarProjetos() {
        return ProjetoRepository.listarProjetos();
    }

    private static void validarOdsSelecionadas(Projeto projeto) {
        if (projeto.getOds() == null || projeto.getOds().isEmpty()) {
            throw new IllegalStateException("O projeto deve possuir ao menos uma ODS associada.");
        }
    }

    private static void validarCoordenador(Servidor coordenador) {
        if (coordenador == null || coordenador.getPerfis() == null || !coordenador.getPerfis().contains(Perfil.COORDENADOR)) {
            throw new IllegalStateException("Somente um coordenador pode registrar o aceite do termo.");
        }
    }
}
