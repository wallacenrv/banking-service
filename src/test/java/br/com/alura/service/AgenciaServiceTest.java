package br.com.alura.service;

import br.com.alura.domain.Agencia;
import br.com.alura.domain.Endereco;
import br.com.alura.exception.AgenciaNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.service.http.SituacaoCadastral;
import br.com.alura.service.http.SituacaoCadastralHttpService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

//indica que é uma classe de test
@QuarkusTest
public class AgenciaServiceTest {

    @InjectMock
    private AgenciaRepository agenciaRepository;

//É usado em testes para injetar mocks ou stubs em uma classe que você
// está testando. Ele cria uma instância da classe e injeta os mocks ou
// stubs nos campos anotados com @Mock ou @Spy.
    @InjectMock
    @RestClient
    private SituacaoCadastralHttpService situacaoCadastralHttpService;


    //É usado para injetar dependências reais em um objeto.
    // Isso significa que, durante a execução do seu código, o container
    // (como o Quarkus) irá fornecer uma instância real da classe ou interface que você está injetando.
    @Inject
    private AgenciaService agenciaService;

    @Test
    public void deveNaocadastrarQuandoClientRetornarNull(){
//arrange
        Agencia agencia = criarAgencia();
        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj("123")).thenReturn(null);
//assert
        Assertions.assertThrows(AgenciaNaoEncontradaException.class,() ->  agenciaService.cadastrar(agencia));
        Mockito.verify(agenciaRepository, Mockito.never()).persist(agencia);
    }


    @Test
    public void deveCadastrarQuandiClientRetornarSituacaoCadastral(){
        // GIVEN (contexto)
        Agencia agencia = AgenciaFixture.criarAgenciaPadrao();

        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj("123")).thenReturn(agencia);
        // WHEN (ação principal)
        agenciaService.cadastrar(agencia);
// THEN (resultado esperado)
        Mockito.verify(agenciaRepository).persist(agencia);
    }

    @Test
    public void deveBuscarAgenciaPorId(){
        Agencia agencia = AgenciaFixture.criarAgenciaPadrao();
        agenciaRepository.persist(agencia);

        Mockito.when(agenciaRepository.findById(agencia.getId())).thenReturn(agencia);
        Agencia agenciaBuscada = agenciaService.buscarPorId(agencia.getId());
        Assertions.assertEquals(agencia, agenciaBuscada);
    }

    @Test
    public void deveLancarExcecaoQuandoAgenciaNaoForEncontrada() {

        Long idInexistente = 999L;

        Mockito.when(agenciaRepository.findById(idInexistente))
                .thenReturn(null);

        Assertions.assertThrows(
                AgenciaNaoEncontradaException.class,
                () -> agenciaService.buscarPorId(idInexistente)
        );

        Mockito.verify(agenciaRepository).findById(idInexistente);
    }

    @Test
    public void deveNaoCadastrarQuandoClientRetornarAgenciaInativa() {
        Agencia agencia = AgenciaFixture.criarAgenciaHttpInativo();
        Mockito.when(situacaoCadastralHttpService.buscarPorCnpj("15130254000100")).thenReturn(AgenciaFixture.criarAgenciaHttpInativo());

        Assertions.assertThrows(AgenciaNaoAtivaOuNaoEncontradaException.class, () -> agenciaService.cadastrar(agencia));

        Mockito.verify(agenciaRepository, Mockito.never()).persist(agencia);
    }


    private Agencia criarAgencia(){
        Endereco endereco = new Endereco();
        endereco.setRua("Rua ABC");
        endereco.setNumero(123);
        endereco.setComplemento("Apto 123");
        endereco.setLogradouro("Centro");

        Agencia agencia = new Agencia(5L,"Banco brasil","Razao social brasileira", "34567353522", SituacaoCadastral.ATIVO, endereco);
    return agencia;
    }
}
