package br.ifpe.proext.exception;

public class MembroNaoEncontradoException extends RuntimeException {

    public MembroNaoEncontradoException() {
        super("Membro não encontrado na equipe do projeto.");
    }
}
