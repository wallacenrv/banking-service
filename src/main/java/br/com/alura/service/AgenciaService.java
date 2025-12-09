package br.com.alura.service;

import br.com.alura.domain.Agencia;
import br.com.alura.exception.AgenciaNaoEncontradaException;
import br.com.alura.repository.AgenciaRepository;
import br.com.alura.service.http.AgenciaHttp;
import br.com.alura.service.http.AgenciaNaoAtivaOuNaoEncontradaException;
import br.com.alura.service.http.SituacaoCadastral;
import br.com.alura.service.http.SituacaoCadastralHttpService;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;


@ApplicationScoped // quarkus gerenciq para mim para que eu use
public class AgenciaService {


    private final AgenciaRepository agenciaRepository;

    private final MeterRegistry meterRegistry;

    @Inject
    public AgenciaService(AgenciaRepository agenciaRepository, MeterRegistry meterRegistry) {
        this.agenciaRepository = agenciaRepository;
        this.meterRegistry = meterRegistry;
    }

    @Inject
    @RestClient // Injeta o cliente REST configurado para acessar a API externa de situação cadastral
    private SituacaoCadastralHttpService situacaoCadastralHttpService;



    public void cadastrar(Agencia agencia) {
        AgenciaHttp agenciaHttp = situacaoCadastralHttpService.buscarPorCnpj(agencia.getCnpj());
        if(agenciaHttp != null && agenciaHttp.getSituacaoCadastral().equals(SituacaoCadastral.ATIVO)) {
            this.meterRegistry.counter("agencia_adicionada_count").increment();
            Log.info("Agencia com CNPJ " + agencia.getCnpj() + " foi adicionada");
            agenciaRepository.persist(agencia);
        } else {
            Log.info("Agencia com CNPJ " + agencia.getCnpj() + " não ativa ou não encontrada");
            this.meterRegistry.counter("agencia_nao_adicionada_count").increment();
            throw new AgenciaNaoAtivaOuNaoEncontradaException();
        }
    }

    public Agencia buscarPorId(Long id) {
        return agenciaRepository.findById(id);
    }

    public void deletar(Long id) {
        Log.info("A agência foi deletada");
        agenciaRepository.deleteById(id);
    }

    public void alterar(Agencia agencia) {
        Log.info("A agência com CNPJ " + agencia.getCnpj() + " foi alterada");
        agenciaRepository.update("nome = ?1, razaoSocial = ?2, cnpj = ?3 where id = ?4", agencia.getNome(), agencia.getRazaoSocial(), agencia.getCnpj(), agencia.getId());
    }
}