package br.ifpe.proext.service;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.enums.StatusProjeto;
import br.ifpe.proext.exception.ProjetoBloqueadoParaEdicaoException;
import br.ifpe.proext.exception.ProjetoJaSubmetidoException;
import br.ifpe.proext.exception.ProjetoNaoEncontradoException;
import br.ifpe.proext.exception.TermoNaoAceitoException;
import br.ifpe.proext.exception.UsuarioSemPermissaoException;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.ProjetoRepository;
import java.util.ArrayList;

public class ProjetoService {
    private ProjetoService(){}
    
    public static void cadastrarProjeto(Servidor usuario, Projeto projeto){
        validarPermissaoCoordenador(usuario);
        validarPreenchimentoObrigatorio(projeto);
        validarOdsSelecionadas(projeto);
        ProjetoRepository.criarProjeto(projeto);
    }

    public static void atualizarProjeto(Servidor usuario, Projeto projeto) {
        validarPermissaoCoordenador(usuario);
        validarPreenchimentoObrigatorio(projeto);
        validarOdsSelecionadas(projeto);
        Projeto projetoExistente = buscarProjetoExistentePorTitulo(projeto.getTitulo());
        validarProjetoEditavel(projetoExistente);

        ProjetoRepository.atualizarProjeto(projeto);
    }

    public static void registrarAceiteTermo(Projeto projeto, Servidor coordenador) {
        Projeto projetoExistente = buscarProjetoExistentePorTitulo(projeto.getTitulo());

        validarCoordenador(coordenador);

        if (projetoExistente.getStatus() != StatusProjeto.RASCUNHO) {
            throw new IllegalStateException("O aceite do termo só pode ser registrado em projetos RASCUNHO.");
        }

        projetoExistente.setTermoAceito(true);
        projetoExistente.setCoordenadorAceite(coordenador);
        ProjetoRepository.atualizarProjeto(projetoExistente);
    }

    public static void submeterProjeto(Servidor usuario, Projeto projeto) {
        validarPermissaoCoordenador(usuario);
        Projeto projetoExistente = buscarProjetoExistentePorTitulo(projeto.getTitulo());

        validarProjetoParaSubmissao(projetoExistente);

        projetoExistente.setStatus(StatusProjeto.SUBMETIDO);
        ProjetoRepository.atualizarProjeto(projetoExistente);
    }

    public static ArrayList<Projeto> listarProjetos() {
        return ProjetoRepository.listarProjetos();
    }

    public static Projeto buscarProjetoParaVisualizacao(String titulo) {
        return buscarProjetoExistentePorTitulo(titulo);
    }

    public static Projeto buscarProjetoParaEdicao(String titulo) {
        Projeto projetoExistente = buscarProjetoExistentePorTitulo(titulo);
        validarProjetoEditavel(projetoExistente);
        return projetoExistente;
    }

    private static void validarOdsSelecionadas(Projeto projeto) {
        if (projeto.getOds() == null || projeto.getOds().isEmpty()) {
            throw new IllegalStateException("O projeto deve possuir ao menos uma ODS associada.");
        }
    }

    private static void validarPreenchimentoObrigatorio(Projeto projeto) {
        if (projeto == null) {
            throw new IllegalStateException("Projeto inválido.");
        }

        if (estaEmBranco(projeto.getTitulo())) {
            throw new IllegalStateException("O título do projeto é obrigatório.");
        }

        if (estaEmBranco(projeto.getResumo())) {
            throw new IllegalStateException("O resumo do projeto é obrigatório.");
        }

        if (projeto.getPalavrasChave() == null || projeto.getPalavrasChave().isEmpty()) {
            throw new IllegalStateException("O projeto deve possuir ao menos uma palavra-chave.");
        }

        if (estaEmBranco(projeto.getPublicoAlvo())) {
            throw new IllegalStateException("O público-alvo do projeto é obrigatório.");
        }

        if (estaEmBranco(projeto.getAreaTematica())) {
            throw new IllegalStateException("A área temática do projeto é obrigatória.");
        }

        if (estaEmBranco(projeto.getCampus())) {
            throw new IllegalStateException("O campus do projeto é obrigatório.");
        }
    }

    private static boolean estaEmBranco(String valor) {
        return valor == null || valor.trim().isEmpty();
    }

    private static void validarPermissaoCoordenador(Servidor usuario) {
        if (usuario == null || usuario.getPerfis() == null || !usuario.getPerfis().contains(Perfil.COORDENADOR)) {
            throw new UsuarioSemPermissaoException();
        }
    }

    private static void validarCoordenador(Servidor coordenador) {
        if (coordenador == null || coordenador.getPerfis() == null || !coordenador.getPerfis().contains(Perfil.COORDENADOR)) {
            throw new UsuarioSemPermissaoException();
        }
    }

    private static void validarProjetoParaSubmissao(Projeto projeto) {
        validarPreenchimentoObrigatorio(projeto);
        validarOdsSelecionadas(projeto);

        if (!projeto.isTermoAceito()) {
            throw new TermoNaoAceitoException();
        }

        if (projeto.getStatus() == StatusProjeto.SUBMETIDO) {
            throw new ProjetoJaSubmetidoException();
        }

        if (projeto.getStatus() != StatusProjeto.RASCUNHO) {
            throw new IllegalStateException("Somente projetos em status RASCUNHO podem ser submetidos.");
        }
    }

    private static Projeto buscarProjetoExistentePorTitulo(String titulo) {
        Projeto projetoExistente = ProjetoRepository.buscarPorTitulo(titulo);
        if (projetoExistente == null) {
            throw new ProjetoNaoEncontradoException();
        }
        return projetoExistente;
    }

    private static void validarProjetoEditavel(Projeto projeto) {
        if (projeto.getStatus() != StatusProjeto.RASCUNHO && projeto.getStatus() != StatusProjeto.CORRECAO) {
            throw new ProjetoBloqueadoParaEdicaoException();
        }
    }
}
