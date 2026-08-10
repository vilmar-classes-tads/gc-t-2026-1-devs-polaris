package br.ifpe.proext.controller;

import br.ifpe.proext.model.Membro;
import br.ifpe.proext.model.Projeto;
import br.ifpe.proext.model.Servidor;
import br.ifpe.proext.service.EquipeService;

public class EquipeController {
    public static void adicionarMembro(Servidor usuario, Projeto projeto, Membro membro) {
        try {
            EquipeService.adicionarMembro(usuario, projeto, membro);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public static void removerMembro(Servidor usuario, Projeto projeto, String cpf) {
        try {
            EquipeService.removerMembro(usuario, projeto, cpf);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
