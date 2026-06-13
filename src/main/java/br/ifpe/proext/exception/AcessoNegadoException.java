package br.ifpe.proext.exception;

public class AcessoNegadoException extends RuntimeException {
    public AcessoNegadoException() {
        super("Acesso negado: Apenas usuários com perfil administrativo podem gerenciar editais.");
    }
}
