package br.com.alura.service.http;

public class AgenciaNaoAtivaOuNaoEncontradaException extends RuntimeException {

    @Override
    public String getMessage() {
        return "O status da agência é " + SituacaoCadastral.INATIVO + " ou não foi encontrada";
    }
}
