package br.com.alura.domain;

import br.com.alura.service.http.SituacaoCadastral;
import jakarta.persistence.*;

@Entity
public class Agencia {



    public Agencia() {}

    public Agencia(Long id, String nome, String razaoSocial, String cnpj, SituacaoCadastral situacaoCadastral, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.situacaoCadastral = situacaoCadastral;
        this.endereco = endereco;
    }

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "razao_social")
    private String razaoSocial;
    private String cnpj;
    private SituacaoCadastral situacaoCadastral;

    //toda vez que uma agencia for criada, um endereco também será criado
    @OneToOne(cascade =  CascadeType.ALL)
    @JoinColumn(name = "endereco_id")

    private Endereco endereco;

    public SituacaoCadastral getSituacaoCadastral() {
        return situacaoCadastral;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setSituacaoCadastral(SituacaoCadastral situacaoCadastral) {
        this.situacaoCadastral = situacaoCadastral;
    }

    public void setCnpj(String cnpj) {
            this.cnpj = cnpj;
    }

    public void setNome(String nome) {
            this.nome = nome;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }


}
