package br.com.alura.service.http;
// mapeando o que vem da api externa
public class AgenciaHttp {

    public AgenciaHttp(){}



    public AgenciaHttp(String nome, String razaoSocial, String cnpj, String situacaoCadastral) {
        this.nome = nome;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.situacaoCadastral = SituacaoCadastral.valueOf(situacaoCadastral);
    }

     private String nome;
     private String razaoSocial;
     private String cnpj;
     private SituacaoCadastral situacaoCadastral;

     public String  getNome() {
         return nome;
     }

     public String getRazaoSocial() {
            return razaoSocial;
     }

     public String getCnpj() {
         return cnpj;
     }

     public String getSituacaoCadastral() {
         return situacaoCadastral.toString();
     }
}
