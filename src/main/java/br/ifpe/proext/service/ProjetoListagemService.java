package br.ifpe.proext.service;

import br.ifpe.proext.enums.Perfil;
import br.ifpe.proext.enums.StatusProjeto;
import br.ifpe.proext.exception.SemPermissaoException;
import br.ifpe.proext.model.Edital;
import br.ifpe.proext.model.FiltroProjeto;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.repository.ProjetoRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjetoListagemService {

    private ProjetoListagemService() {}

    public static List<Projeto> listarProjetos(Servidor usuario, FiltroProjeto filtro) {
        FiltroProjeto filtroEfetivo = (filtro == null) ? new FiltroProjeto() : filtro;

        if (temPerfil(usuario, Perfil.ADMINISTRADOR)) {
            return aplicarFiltro(ProjetoRepository.listarProjetos(), filtroEfetivo);
        }

        if (temPerfil(usuario, Perfil.GESTOR)) {
            FiltroProjeto filtroDoGestor = new FiltroProjeto();
            filtroDoGestor.setCampus(usuario.getCampus());
            filtroDoGestor.setAreaTematica(filtroEfetivo.getAreaTematica());
            filtroDoGestor.setStatus(filtroEfetivo.getStatus());
            filtroDoGestor.setEdital(filtroEfetivo.getEdital());

            List<Projeto> resultado = aplicarFiltro(ProjetoRepository.listarProjetos(), filtroDoGestor);
            resultado.removeIf(projeto -> projeto.getStatus() == StatusProjeto.RASCUNHO);
            return resultado;
        }

        throw new SemPermissaoException();
    }

    public static List<String> baixarAnexos(Servidor usuario, Projeto projeto) {
        validarPermissaoDeAcessoAosArquivos(usuario, projeto);
        return new ArrayList<>(projeto.getAnexos());
    }

    private static void validarPermissaoDeAcessoAosArquivos(Servidor usuario, Projeto projeto) {
        if (temPerfil(usuario, Perfil.ADMINISTRADOR)) {
            return;
        }

        if (temPerfil(usuario, Perfil.GESTOR) && mesmoCampus(usuario, projeto)) {
            return;
        }

        if (ehCoordenadorDono(usuario, projeto)) {
            return;
        }

        throw new SemPermissaoException();
    }

    private static boolean ehCoordenadorDono(Servidor usuario, Projeto projeto) {
        Servidor dono = projeto.getCoordenadorAceite();
        return usuario != null && dono != null
                && usuario.getCpf() != null
                && usuario.getCpf().equalsIgnoreCase(dono.getCpf());
    }

    private static boolean mesmoCampus(Servidor usuario, Projeto projeto) {
        return usuario.getCampus() != null && usuario.getCampus().equals(projeto.getCampus());
    }

    private static boolean temPerfil(Servidor usuario, Perfil perfil) {
        return usuario != null && usuario.getPerfis() != null && usuario.getPerfis().contains(perfil);
    }

    private static List<Projeto> aplicarFiltro(List<Projeto> projetos, FiltroProjeto filtro) {
        List<Projeto> resultado = new ArrayList<>();

        for (Projeto projeto : projetos) {
            if (filtroCombinaComProjeto(projeto, filtro)) {
                resultado.add(projeto);
            }
        }

        return resultado;
    }

    private static boolean filtroCombinaComProjeto(Projeto projeto, FiltroProjeto filtro) {
        if (filtro.getCampus() != null && !filtro.getCampus().equals(projeto.getCampus())) {
            return false;
        }

        if (filtro.getAreaTematica() != null && !filtro.getAreaTematica().equals(projeto.getAreaTematica())) {
            return false;
        }

        if (filtro.getStatus() != null && filtro.getStatus() != projeto.getStatus()) {
            return false;
        }

        if (filtro.getEdital() != null && !mesmoEdital(filtro.getEdital(), projeto.getEdital())) {
            return false;
        }

        return true;
    }

    private static boolean mesmoEdital(Edital editalFiltro, Edital editalProjeto) {
        return editalProjeto != null
                && editalFiltro.getNumero() == editalProjeto.getNumero()
                && editalFiltro.getAno() == editalProjeto.getAno();
    }
}
