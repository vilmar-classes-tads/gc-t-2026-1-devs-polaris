package br.ifpe.proext.exception;

public class ProjetoBloqueadoParaEdicaoException extends RuntimeException {

    public ProjetoBloqueadoParaEdicaoException() {
        super("Projeto bloqueado para edição neste status.");
    }
}

