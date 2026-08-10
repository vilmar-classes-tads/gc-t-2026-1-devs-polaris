package br.ifpe.proext.service;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.enums.TipoParticipacao;
import br.ifpe.proext.exception.LimitePlanosTrabalhoExcedidoException;
import br.ifpe.proext.exception.MembroJaCadastradoException;
import br.ifpe.proext.exception.MembroNaoEncontradoException;
import br.ifpe.proext.exception.ProjetoBloqueadoParaEdicaoException;
import br.ifpe.proext.exception.UsuarioSemPermissaoException;
import br.ifpe.proext.model.Membro;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EquipeServiceTest {

    private Servidor coordenador;
    private Projeto projeto;

    @BeforeEach
    void setUp() {
        coordenador = new Servidor();
        coordenador.setNome("Ana Coordenadora");
        Set<Perfil> perfis = new HashSet<>();
        perfis.add(Perfil.COORDENADOR);
        coordenador.setPerfis(perfis);

        projeto = new Projeto();
        projeto.setTitulo("Projeto Robotica na Escola");
    }

    private Membro criarMembro(String cpf, TipoParticipacao tipo) {
        Membro membro = new Membro();
        membro.setNome("Membro " + cpf);
        membro.setCpf(cpf);
        membro.setFuncao("Desenvolvedor");
        membro.setCargaHoraria(10);
        membro.setTipoParticipacao(tipo);
        return membro;
    }

    @Test
    void deveAdicionarMembroComDadosValidos() {
        Membro membro = criarMembro("111.111.111-11", null);

        EquipeService.adicionarMembro(coordenador, projeto, membro);

        assertEquals(1, projeto.getEquipe().size());
        assertTrue(projeto.getEquipe().contains(membro));
    }

    @Test
    void naoDeveAdicionarMembroSemPermissaoDeCoordenador() {
        Servidor avaliador = new Servidor();
        Set<Perfil> perfis = new HashSet<>();
        perfis.add(Perfil.AVALIADOR);
        avaliador.setPerfis(perfis);

        Membro membro = criarMembro("111.111.111-11", null);

        assertThrows(UsuarioSemPermissaoException.class,
                () -> EquipeService.adicionarMembro(avaliador, projeto, membro));
    }

    @Test
    void naoDeveAdicionarMembroComCpfDuplicadoNoProjeto() {
        Membro membro1 = criarMembro("222.222.222-22", null);
        Membro membro2 = criarMembro("222.222.222-22", null);

        EquipeService.adicionarMembro(coordenador, projeto, membro1);

        assertThrows(MembroJaCadastradoException.class,
                () -> EquipeService.adicionarMembro(coordenador, projeto, membro2));
    }

    @Test
    void naoDeveUltrapassarLimiteDeQuatroPlanosDeTrabalho() {
        EquipeService.adicionarMembro(coordenador, projeto, criarMembro("1", TipoParticipacao.BOLSISTA));
        EquipeService.adicionarMembro(coordenador, projeto, criarMembro("2", TipoParticipacao.VOLUNTARIO));
        EquipeService.adicionarMembro(coordenador, projeto, criarMembro("3", TipoParticipacao.BOLSISTA));
        EquipeService.adicionarMembro(coordenador, projeto, criarMembro("4", TipoParticipacao.VOLUNTARIO));

        Membro quinto = criarMembro("5", TipoParticipacao.BOLSISTA);

        assertThrows(LimitePlanosTrabalhoExcedidoException.class,
                () -> EquipeService.adicionarMembro(coordenador, projeto, quinto));
    }

    @Test
    void membroSemTipoParticipacaoNaoContaParaOLimiteDePlanos() {
        EquipeService.adicionarMembro(coordenador, projeto, criarMembro("1", TipoParticipacao.BOLSISTA));
        EquipeService.adicionarMembro(coordenador, projeto, criarMembro("2", TipoParticipacao.VOLUNTARIO));
        EquipeService.adicionarMembro(coordenador, projeto, criarMembro("3", TipoParticipacao.BOLSISTA));
        EquipeService.adicionarMembro(coordenador, projeto, criarMembro("4", TipoParticipacao.VOLUNTARIO));

        Membro quinto = criarMembro("5", null);

        EquipeService.adicionarMembro(coordenador, projeto, quinto);

        assertEquals(5, projeto.getEquipe().size());
    }

    @Test
    void deveRemoverMembroExistente() {
        Membro membro = criarMembro("333.333.333-33", null);
        EquipeService.adicionarMembro(coordenador, projeto, membro);

        EquipeService.removerMembro(coordenador, projeto, membro.getCpf());

        assertTrue(projeto.getEquipe().isEmpty());
    }

    @Test
    void naoDeveRemoverMembroInexistente() {
        assertThrows(MembroNaoEncontradoException.class,
                () -> EquipeService.removerMembro(coordenador, projeto, "cpf-que-nao-existe"));
    }

    @Test
    void naoDeveGerenciarEquipeDeProjetoJaSubmetido() {
        projeto.setStatus(br.ifpe.proext.enums.StatusProjeto.SUBMETIDO);
        Membro membro = criarMembro("444.444.444-44", null);

        assertThrows(ProjetoBloqueadoParaEdicaoException.class,
                () -> EquipeService.adicionarMembro(coordenador, projeto, membro));
    }
}
