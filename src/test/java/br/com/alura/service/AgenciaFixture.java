package br.com.alura.service;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.Endereco;
import br.com.alura.service.http.AgenciaHttp;
import br.com.alura.service.http.SituacaoCadastral;

public class AgenciaFixture {

    /**
     * Retorna uma Agência simulada como se viesse do client HTTP.
     * Situação cadastral: ATIVO
     */
    public static Agencia criarAgenciaHttp() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua Teste");
        endereco.setNumero(100);
        endereco.setComplemento("Bloco A");
        endereco.setLogradouro("Centro");

        return new Agencia(
                5L,
                "Agência Teste",
                "Razão Social Teste",
                "123", // CNPJ usado no teste
                SituacaoCadastral.ATIVO,
                endereco
        );
    }


    public static Agencia criarAgenciaHttpInativo() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua Teste");
        endereco.setNumero(100);
        endereco.setComplemento("Bloco A");
        endereco.setLogradouro("Centro");

        return new Agencia(
                5L,
                "Agência Teste",
                "Razão Social Teste",
                "15130254000100", // CNPJ usado no teste
                SituacaoCadastral.INATIVO,
                endereco
        );
    }

    /**
     * Retorna uma agência padrão para testes gerais.
     */
    public static Agencia criarAgenciaPadrao() {
        Endereco endereco = new Endereco();
        endereco.setRua("Rua ABC");
        endereco.setNumero(123);
        endereco.setComplemento("Apto 123");
        endereco.setLogradouro("Centro");

        return new Agencia(
                5L,
                "Banco Brasil",
                "Razao Social Brasileira",
                "123",
                SituacaoCadastral.ATIVO,
                endereco
        );


    }

    public static AgenciaHttp criarAgenciaHttp(String status) {
        return new AgenciaHttp("Agencia Teste", "Razao social da Agencia Teste", "123", status);
    }
}
