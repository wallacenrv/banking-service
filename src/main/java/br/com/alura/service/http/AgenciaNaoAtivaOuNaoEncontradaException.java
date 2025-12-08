package br.com.alura.service.http;

public class AgenciaNaoAtivaOuNaoEncontradaException extends RuntimeException {
    public AgenciaNaoAtivaOuNaoEncontradaException(String message) {
        super(message);
    }
}
