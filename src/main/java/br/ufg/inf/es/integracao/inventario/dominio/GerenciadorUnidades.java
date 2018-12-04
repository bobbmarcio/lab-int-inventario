package br.ufg.inf.es.integracao.inventario.dominio;


import br.ufg.inf.es.integracao.inventario.dominio.entidade.Unidade;
import br.ufg.inf.es.integracao.inventario.repositorio.UnidadeRepository;

import javax.inject.Inject;
import java.util.List;

public class GerenciadorUnidades {

  @Inject
  private UnidadeRepository unidadeRepository;

  public Unidade cadastrarNovaUnidade(
    final String nome,
    final String endereco,
    final String cidade,
    final String uf,
    final String cep
  ) {
    final Unidade unidade = new Unidade();
    unidade.setNome(nome);
    unidade.setEndereco(endereco);
    unidade.setCidade(cidade);
    unidade.setUf(uf);
    unidade.setCep(cep);

    unidadeRepository.inserirUnidade(unidade);
    return unidade;
  }

  public void atualizaUnidade(
    final long idUnidade,
    final String novoNome,
    final String novoEndereco,
    final String novaCidade,
    final String novaUf,
    final String novoCep
  ) {
    final Unidade unidade = new Unidade();
    unidade.setNome(novoNome);
    unidade.setEndereco(novoEndereco);
    unidade.setCidade(novaCidade);
    unidade.setUf(novaUf);
    unidade.setCep(novoCep);

    unidadeRepository.atualizeUnidade(idUnidade, unidade);
  }

  public void apagarUnidadePorId(final long id) {
    unidadeRepository.apagaUnidade(id);
  }

  public List<Unidade> listarTodos() {
    return unidadeRepository.encontreTodos();
  }

}
