package br.ufg.inf.es.integracao.inventario.dominio;


import br.ufg.inf.es.integracao.inventario.dominio.entidade.Departamento;
import br.ufg.inf.es.integracao.inventario.repositorio.DepartamentoRepository;

import javax.inject.Inject;
import java.util.List;

public class GerenciadorDepartamentos {

  @Inject
  private DepartamentoRepository departamentoRepository;

  public Departamento cadastrarNovoDepartamento(
    final String nome,
    final Long chefe
  ) {
    final Departamento departamento = new Departamento();
    departamento.setNome(nome);
    departamento.setChefe(chefe);

    departamentoRepository.inserirDepartamento(departamento);
    return departamento;
  }

  public void atualizaDepartamento(
    final long idDoDepartamento,
    final String novoNome,
    final Long novoChefe
  ) {
    final Departamento departamento = new Departamento();
    departamento.setNome(novoNome);
    departamento.setChefe(novoChefe);

    departamentoRepository.atualizeDepartamento(idDoDepartamento, departamento);
  }

  public void apagarDepartamentoPorId(final long id) {
    departamentoRepository.apagaDepartamento(id);
  }

  public List<Departamento> listarTodos() {
    return departamentoRepository.encontreTodos();
  }

}
