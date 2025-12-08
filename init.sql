create table if not exists endereco(
                                       id BIGINT primary key,
                                       rua text not null,
                                       logradouro text not null,
                                       complemento text not null,
                                       numero int not null
);

create table if not exists agencia(
                                      id BIGINT primary key,
                                      nome text not null,
                                      razao_social text not null,
                                      cnpj text not null,
                                      endereco_id int references endereco
);

-- Explicação do script SQL:
--
-- endereco: Cria uma tabela para armazenar informações de endereço, como rua, logradouro, complemento e numero.
-- agencia: Cria uma tabela para armazenar informações de agências, incluindo nome, razao_social e cnpj. A coluna endereco_id é uma chave estrangeira que faz referência ao id da tabela endereco.
-- Executando o banco de dados
--
-- Após salvar o docker-compose.yml e o init.sql, no terminal, navegue até a raiz do projeto e execute o seguinte comando:
--
-- O arquivo init.sql é um script SQL que contém instruções para criar as tabelas do banco de dados PostgreSQL. Ele é
-- executado automaticamente quando o container do PostgreSQL é iniciado pelo Docker Compose.
--
-- No contexto do tutorial, o init.sql cria duas tabelas:
--
-- endereco: Armazena informações de endereços, como rua, logradouro, complemento e número.
-- agencia: Armazena informações sobre as agências, incluindo nome, razão social e CNPJ. Essa tabela também possui uma
-- chave estrangeira (endereco_id) que se relaciona com a tabela endereco.
-- Em resumo, o init.sql garante que o banco de dados seja inicializado com a estrutura necessária para o curso, f
-- acilitando o desenvolvimento e evitando a necessidade de criar as tabelas manualmente.