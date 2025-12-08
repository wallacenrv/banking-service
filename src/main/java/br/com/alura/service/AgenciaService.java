package br.com.alura.service;

import br.com.alura.domain.Agencia;
import br.com.alura.exception.AgenciaNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.service.http.SituacaoCadastral;
import br.com.alura.service.http.SituacaoCadastralHttpService;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@ApplicationScoped // quarkus gerenciq para mim para que eu use
public class AgenciaService {

    @Inject
    @RestClient // Injeta o cliente REST configurado para acessar a API externa de situação cadastral
    private SituacaoCadastralHttpService situacaoCadastralHttpService;

    private final AgenciaRepository agenciaRepository;

    @Inject
    public AgenciaService(AgenciaRepository agenciaRepository) {
        this.agenciaRepository = agenciaRepository;
    }


    public void cadastrar(Agencia agencia) {
        Agencia agenciaBuscada = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());


        if (agenciaBuscada != null && SituacaoCadastral.ATIVO.equals(agenciaBuscada.getSituacaoCadastral())) {
            Log.info("Agencia com CNPJ " + agencia.getCnpj() + " foi cadastrada com sucesso.");
            agenciaRepository.persist(agencia);
        }
        else if (agenciaBuscada != null && SituacaoCadastral.INATIVO.equals(agenciaBuscada.getSituacaoCadastral()))  {
            throw  new AgenciaNaoAtivaOuNaoEncontradaException("Agencia inativa não pode ser cadastrada.");
        }
        else {
            Log.info("Agencia com CNPF " + agencia.getCnpj() + " não foi cadastrada.");
            throw new AgenciaNaoEncontradaException("Agência com CNPJ " + agencia.getCnpj() + " não está ativa.");
        }


    }


    public Agencia buscarPorId(Long id) {
        Agencia agenciaBuscada = agenciaRepository.findById(id);
        if (agenciaBuscada == null) {
            throw new AgenciaNaoEncontradaException("Agência com ID " + id + " não encontrada.");
        }
        return agenciaBuscada;
    }

    public void deletar(Long id) {
        Agencia agenciaExistente = buscarPorId(id);
        if (agenciaExistente != null) {
            Log.info("Agencia com ID " + id + " foi deletada com sucesso.");
            agenciaRepository.delete(agenciaExistente);
        } else {
            throw new AgenciaNaoEncontradaException("Agência com ID : " + id + " não encontrada.");
        }
    }

    public void alterar(Agencia agencia) {
        // Busca a entidade pelo ID
        Agencia entidadeExistente = agenciaRepository.findById(agencia.getId());

        if (entidadeExistente != null) {
            // Atualiza os atributos desejados
            entidadeExistente.setNome(agencia.getNome());
            entidadeExistente.setRazaoSocial(agencia.getRazaoSocial());
            entidadeExistente.setCnpj(agencia.getCnpj());
            Log.info("Agencia com ID " + agencia.getId() + " foi alterada com sucesso.");
        } else {
            Log.info("Agencia não foi alterada");
            throw new IllegalStateException("Agência com ID " + agencia.getId() + " não encontrada");
        }
    }
}
