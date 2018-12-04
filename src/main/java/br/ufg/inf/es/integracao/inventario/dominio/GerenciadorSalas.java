package br.ufg.inf.es.integracao.inventario.dominio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Sala;
import br.ufg.inf.es.integracao.inventario.repositorio.SalaRepository;

import javax.inject.Inject;
import java.util.List;

public class GerenciadorSalas {

  @Inject
  private SalaRepository salaRepository;

  public Sala cadastrarNovaSala(
    final String nome,
    final String codigo,
    final Long predioId,
    final Long departamentoId
  ) {
    final Sala sala = new Sala();
    sala.setNome(nome);
    sala.setCodigo(codigo);
    sala.setPredioId(predioId);
    sala.setDepartamentoId(departamentoId);

    salaRepository.inserirSala(sala);
    return sala;
  }

  public void atualizaSala(
    final long idDaSala,
    final String novoNome,
    final String novoCodigo,
    final Long novoPredioId,
    final Long novoDepartamentoId
  ) {
    final Sala sala = new Sala();
    sala.setNome(novoNome);
    sala.setCodigo(novoCodigo);
    sala.setPredioId(novoPredioId);
    sala.setDepartamentoId(novoDepartamentoId);

    salaRepository.atualizeSala(idDaSala, sala);
  }

  public void apagarSalaPorId(final long id) {
    salaRepository.apagaSala(id);
  }

  public List<Sala> listarTodos() {
    return salaRepository.encontreTodos();
  }

}
