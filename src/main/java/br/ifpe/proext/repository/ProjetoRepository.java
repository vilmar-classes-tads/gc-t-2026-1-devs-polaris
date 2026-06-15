package br.ifpe.proext.repository;

import br.ifpe.proext.model.Projeto;

import java.util.ArrayList;

public class ProjetoRepository {
    public static final ArrayList<Projeto> projetos = new ArrayList<>();

    private ProjetoRepository(){}

    public static void criarProjeto(Projeto projeto){
        projetos.add(projeto);
    }

    public static Projeto buscarPorTitulo(String titulo) {
        for (Projeto projeto : projetos) {
            if (projeto.getTitulo().trim().equalsIgnoreCase(titulo.trim())) {
                return projeto;
            }
        }
        return null;
    }

    public static void atualizarProjeto(Projeto projeto) {
        for (Projeto projetoAux : projetos) {
            if (projetoAux.getTitulo().trim().equalsIgnoreCase(projeto.getTitulo().trim())) {
                projetoAux.setResumo(projeto.getResumo());
                projetoAux.setPalavrasChave(projeto.getPalavrasChave());
                projetoAux.setPublicoAlvo(projeto.getPublicoAlvo());
                projetoAux.setAreaTematica(projeto.getAreaTematica());
                projetoAux.setCampus(projeto.getCampus());
                projetoAux.setOds(projeto.getOds());
                projetoAux.setStatus(projeto.getStatus());
                projetoAux.setTermoAceito(projeto.isTermoAceito());
                projetoAux.setCoordenadorAceite(projeto.getCoordenadorAceite());
                break;
            }
        }
    }

        public static ArrayList<Projeto> listarProjetos() {
            return projetos;
        }
}
