package br.ifpe.proext.service;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.enums.StatusProjeto;
import br.ifpe.proext.exception.SemPermissaoException;
import br.ifpe.proext.model.Edital;
import br.ifpe.proext.model.FiltroProjeto;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.ProjetoRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProjetoListagemServiceTest {

    private Servidor administrador;
    private Servidor gestorRecife;
    private Servidor gestorCaruaru;
    private Servidor coordenadorDono;
    private Servidor avaliadorQualquer;

    private Edital edital2026;
    private Projeto projetoRecifeSubmetido;
    private Projeto projetoRecifeRascunho;
    private Projeto projetoCaruaruAprovado;

    @BeforeEach
    void setUp() {
        administrador = servidorComPerfil("admin", Perfil.ADMINISTRADOR, null);
        gestorRecife = servidorComPerfil("gestor-recife", Perfil.GESTOR, "Recife");
        gestorCaruaru = servidorComPerfil("gestor-caruaru", Perfil.GESTOR, "Caruaru");
        coordenadorDono = servidorComPerfil("coordenador-dono", Perfil.COORDENADOR, "Recife");
        avaliadorQualquer = servidorComPerfil("avaliador", Perfil.AVALIADOR, "Recife");

        edital2026 = new Edital();
        edital2026.setTitulo("Edital de Extensão 2026");
        edital2026.setNumero(1);
        edital2026.setAno(2026);

        projetoRecifeSubmetido = criarProjeto("Projeto Recife Submetido", "Recife", "Educação",
                StatusProjeto.SUBMETIDO, edital2026, coordenadorDono);

        projetoRecifeRascunho = criarProjeto("Projeto Recife Rascunho", "Recife", "Saúde",
                StatusProjeto.RASCUNHO, edital2026, coordenadorDono);

        projetoCaruaruAprovado = criarProjeto("Projeto Caruaru Aprovado", "Caruaru", "Educação",
                StatusProjeto.APROVADO, edital2026, coordenadorDono);

        ProjetoRepository.projetos.add(projetoRecifeSubmetido);
        ProjetoRepository.projetos.add(projetoRecifeRascunho);
        ProjetoRepository.projetos.add(projetoCaruaruAprovado);
    }

    @AfterEach
    void tearDown() {
        ProjetoRepository.projetos.clear();
    }

    private Servidor servidorComPerfil(String cpf, Perfil perfil, String campus) {
        Servidor servidor = new Servidor();
        servidor.setCpf(cpf);
        servidor.setCampus(campus);
        Set<Perfil> perfis = new HashSet<>();
        perfis.add(perfil);
        servidor.setPerfis(perfis);
        return servidor;
    }

    private Projeto criarProjeto(String titulo, String campus, String areaTematica,
                                  StatusProjeto status, Edital edital, Servidor coordenador) {
        Projeto projeto = new Projeto();
        projeto.setTitulo(titulo);
        projeto.setCampus(campus);
        projeto.setAreaTematica(areaTematica);
        projeto.setStatus(status);
        projeto.setEdital(edital);
        projeto.setCoordenadorAceite(coordenador);
        return projeto;
    }

    // ---------- Listagem administrativa ----------

    @Test
    void administradorVisualizaTodosOsProjetosSemFiltro() {
        List<Projeto> resultado = ProjetoListagemService.listarProjetos(administrador, new FiltroProjeto());

        assertEquals(3, resultado.size());
    }

    @Test
    void administradorFiltraPorCampus() {
        FiltroProjeto filtro = new FiltroProjeto();
        filtro.setCampus("Caruaru");

        List<Projeto> resultado = ProjetoListagemService.listarProjetos(administrador, filtro);

        assertEquals(1, resultado.size());
        assertEquals("Projeto Caruaru Aprovado", resultado.get(0).getTitulo());
    }

    @Test
    void administradorFiltraPorStatus() {
        FiltroProjeto filtro = new FiltroProjeto();
        filtro.setStatus(StatusProjeto.RASCUNHO);

        List<Projeto> resultado = ProjetoListagemService.listarProjetos(administrador, filtro);

        assertEquals(1, resultado.size());
        assertEquals("Projeto Recife Rascunho", resultado.get(0).getTitulo());
    }

    @Test
    void administradorFiltraPorAreaTematica() {
        FiltroProjeto filtro = new FiltroProjeto();
        filtro.setAreaTematica("Educação");

        List<Projeto> resultado = ProjetoListagemService.listarProjetos(administrador, filtro);

        assertEquals(2, resultado.size());
    }

    @Test
    void administradorFiltraPorEdital() {
        Edital outroEdital = new Edital();
        outroEdital.setNumero(2);
        outroEdital.setAno(2026);
        Projeto projetoOutroEdital = criarProjeto("Projeto Outro Edital", "Recife", "Educação",
                StatusProjeto.SUBMETIDO, outroEdital, coordenadorDono);
        ProjetoRepository.projetos.add(projetoOutroEdital);

        FiltroProjeto filtro = new FiltroProjeto();
        filtro.setEdital(edital2026);

        List<Projeto> resultado = ProjetoListagemService.listarProjetos(administrador, filtro);

        assertEquals(3, resultado.size());
    }

    @Test
    void gestorVisualizaApenasProjetosDoProprioCampus() {
        List<Projeto> resultado = ProjetoListagemService.listarProjetos(gestorCaruaru, new FiltroProjeto());

        assertEquals(1, resultado.size());
        assertEquals("Caruaru", resultado.get(0).getCampus());
    }

    @Test
    void gestorIgnoraTentativaDeFiltrarPorOutroCampus() {
        FiltroProjeto filtro = new FiltroProjeto();
        filtro.setCampus("Caruaru");

        List<Projeto> resultado = ProjetoListagemService.listarProjetos(gestorRecife, filtro);

        assertTrue(resultado.stream().allMatch(p -> p.getCampus().equals("Recife")));
    }

    @Test
    void gestorNaoVisualizaProjetosEmRascunho() {
        List<Projeto> resultado = ProjetoListagemService.listarProjetos(gestorRecife, new FiltroProjeto());

        assertEquals(1, resultado.size());
        assertEquals(StatusProjeto.SUBMETIDO, resultado.get(0).getStatus());
    }

    @Test
    void coordenadorNaoTemPermissaoParaListagemAdministrativa() {
        assertThrows(SemPermissaoException.class,
                () -> ProjetoListagemService.listarProjetos(coordenadorDono, new FiltroProjeto()));
    }

    @Test
    void avaliadorNaoTemPermissaoParaListagemAdministrativa() {
        assertThrows(SemPermissaoException.class,
                () -> ProjetoListagemService.listarProjetos(avaliadorQualquer, new FiltroProjeto()));
    }

    // ---------- Download de arquivos (anexos e planos) ----------

    @Test
    void administradorPodeBaixarArquivosMesmoNaoSendoDono() {
        projetoRecifeSubmetido.getAnexos().add("proposta.pdf");

        List<String> arquivos = ProjetoListagemService.baixarAnexos(administrador, projetoRecifeSubmetido);

        assertEquals(1, arquivos.size());
    }

    @Test
    void gestorDoMesmoCampusPodeBaixarArquivos() {
        projetoRecifeSubmetido.getAnexos().add("proposta.pdf");

        List<String> arquivos = ProjetoListagemService.baixarAnexos(gestorRecife, projetoRecifeSubmetido);

        assertEquals(1, arquivos.size());
    }

    @Test
    void gestorDeOutroCampusNaoPodeBaixarArquivos() {
        assertThrows(SemPermissaoException.class,
                () -> ProjetoListagemService.baixarAnexos(gestorCaruaru, projetoRecifeSubmetido));
    }

    @Test
    void coordenadorDonoPodeBaixarSeusPropriosArquivos() {
        projetoRecifeSubmetido.getAnexos().add("proposta.pdf");

        List<String> arquivos = ProjetoListagemService.baixarAnexos(coordenadorDono, projetoRecifeSubmetido);

        assertEquals(1, arquivos.size());
    }

    @Test
    void usuarioSemRelacaoComProjetoNaoPodeBaixarArquivos() {
        assertThrows(SemPermissaoException.class,
                () -> ProjetoListagemService.baixarAnexos(avaliadorQualquer, projetoCaruaruAprovado));
    }
}
