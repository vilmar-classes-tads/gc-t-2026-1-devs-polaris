package br.ifpe.proext.repository;

import br.ifpe.proext.model.Projeto;

import java.util.ArrayList;

public class ProjetoRepository {
    public static final ArrayList<Projeto> projetos = new ArrayList<>();

    private ProjetoRepository(){}

    public static void criarProjeto(Projeto projeto){
        projetos.add(projeto);
    }    
}
