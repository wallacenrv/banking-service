package br.com.alura.service.http;

import br.com.alura.domain.Agencia;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

//faz a comunicação com a api externa
@Path("/situacao-cadastral") // Define o caminho base para os endpoints deste serviço da api externa
@RegisterRestClient(configKey = "situacao-cadastral-api") // Define que esta interface é um cliente REST
public interface SituacaoCadastralHttpService {

    @GET
    @Path("/{cnpj}") // Define o caminho para buscar uma agência pelo CNPJ
    Agencia buscarPorCnpj(String cnpj); // Método para buscar uma agência pelo CNPJ, retornando um objeto AgenciaHttp


}


//microprofile