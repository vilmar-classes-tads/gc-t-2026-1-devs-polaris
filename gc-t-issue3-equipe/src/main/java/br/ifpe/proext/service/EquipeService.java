package br.ifpe.proext.service;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.enums.StatusProjeto;
import br.ifpe.proext.exception.LimitePlanosTrabalhoExcedidoException;
import br.ifpe.proext.exception.MembroJaCadastradoException;
import br.ifpe.proext.exception.MembroNaoEncontradoException;
import br.ifpe.proext.exception.ProjetoBloqueadoParaEdicaoException;
import br.ifpe.proext.exception.UsuarioSemPermissaoException;
import br.ifpe.proext.model.Membro;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;

public class EquipeService {

    private static final int LIMITE_PLANOS_TRABALHO = 4;

    private EquipeService() {}

    public static void adicionarMembro(Servidor usuario, Projeto projeto, Membro membro) {
        validarPermissaoCoordenador(usuario);
        validarProjetoEditavel(projeto);
        validarDadosObrigatorios(membro);
        validarCpfNaoDuplicado(projeto, membro);

        if (membro.isPlanoDeTrabalho()) {
            validarLimiteDePlanos(projeto);
        }

        projeto.getEquipe().add(membro);
    }

    public static void removerMembro(Servidor usuario, Projeto projeto, String cpf) {
        validarPermissaoCoordenador(usuario);
        validarProjetoEditavel(projeto);

        Membro membro = buscarMembroPorCpf(projeto, cpf);
        projeto.getEquipe().remove(membro);
    }

    private static Membro buscarMembroPorCpf(Projeto projeto, String cpf) {
        for (Membro membro : projeto.getEquipe()) {
            if (membro.getCpf().equalsIgnoreCase(cpf)) {
                return membro;
            }
        }
        throw new MembroNaoEncontradoException();
    }

    private static void validarCpfNaoDuplicado(Projeto projeto, Membro membro) {
        for (Membro membroExistente : projeto.getEquipe()) {
            if (membroExistente.getCpf().equalsIgnoreCase(membro.getCpf())) {
                throw new MembroJaCadastradoException();
            }
        }
    }

    private static void validarLimiteDePlanos(Projeto projeto) {
        long planosAtuais = projeto.getEquipe().stream()
                .filter(Membro::isPlanoDeTrabalho)
                .count();

        if (planosAtuais >= LIMITE_PLANOS_TRABALHO) {
            throw new LimitePlanosTrabalhoExcedidoException();
        }
    }

    private static void validarDadosObrigatorios(Membro membro) {
        if (membro == null || estaEmBranco(membro.getNome())) {
            throw new IllegalStateException("O nome do membro é obrigatório.");
        }
        if (estaEmBranco(membro.getCpf())) {
            throw new IllegalStateException("O CPF do membro é obrigatório.");
        }
        if (estaEmBranco(membro.getFuncao())) {
            throw new IllegalStateException("A função do membro é obrigatória.");
        }
        if (membro.getCargaHoraria() <= 0) {
            throw new IllegalStateException("A carga horária do membro deve ser maior que zero.");
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

    private static void validarProjetoEditavel(Projeto projeto) {
        if (projeto.getStatus() != StatusProjeto.RASCUNHO && projeto.getStatus() != StatusProjeto.CORRECAO) {
            throw new ProjetoBloqueadoParaEdicaoException();
        }
    }
}
