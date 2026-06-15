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
            if (projeto.getTitulo().trim().toLowerCase().equals(titulo.trim().toLowerCase())) {
                return projeto;
            }
        }
        return null;
    }

    public static void atualizarProjeto(Projeto projeto) {
        for (Projeto projetoAux : projetos) {
            if (projetoAux.getTitulo().trim().toLowerCase().equals(projeto.getTitulo().trim().toLowerCase())) {
                projetoAux.setResumo(projeto.getResumo());
                projetoAux.setPalavrasChave(projeto.getPalavrasChave());
                projetoAux.setPublicoAlvo(projeto.getPublicoAlvo());
                projetoAux.setAreaTematica(projeto.getAreaTematica());
                projetoAux.setCampus(projeto.getCampus());
                projetoAux.setOds(projeto.getOds());
                break;
            }
        }
    }

        public static ArrayList<Projeto> listarProjetos() {
            return projetos;
        }
}
