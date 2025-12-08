package br.com.alura.service;

import br.com.alura.domain.Endereco;

public class EnderecoFixture {

    /**
     * Endereço padrão para qualquer teste.
     */
    public static Endereco criarEnderecoPadrao() {
        return new Endereco(
                "Rua ABC",
                "Centro",
                "Apto 123",
                123
        );
    }

    /**
     * Endereço específico para simular retorno do client HTTP.
     */
    public static Endereco criarEnderecoHttp() {
        return new Endereco(
                "Rua Teste",
                "Bairro Teste",
                "Bloco A",
                100
        );
    }
}
