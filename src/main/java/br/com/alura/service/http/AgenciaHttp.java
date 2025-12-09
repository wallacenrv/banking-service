package br.com.alura.service.http;

import br.com.alura.service.http.SituacaoCadastral;

public class AgenciaHttp {

    public AgenciaHttp(String nome, String razaoSocial, String cnpj, String situacaoCadastral) {
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.situacaoCadastral = SituacaoCadastral.valueOf(situacaoCadastral);
    }

    private final String nome;
    private final String razaoSocial;
    private final String cnpj;
    private final SituacaoCadastral situacaoCadastral;

    public String getNome() {
        return nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public SituacaoCadastral getSituacaoCadastral() {
        return situacaoCadastral;
    }
}