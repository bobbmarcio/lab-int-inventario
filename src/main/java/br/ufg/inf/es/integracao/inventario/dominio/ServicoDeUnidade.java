package br.ufg.inf.es.integracao.inventario.dominio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Unidade;
import br.ufg.inf.es.integracao.inventario.repositorio.UnidadeRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ServicoDeUnidade {

  @Inject
  private UnidadeRepository unidadeRepository;

  public void save(Unidade unidade) throws IOException, SQLException {
    unidadeRepository.save(unidade);
  }

  public List<Unidade> fetchAll() throws IOException, SQLException {
    return unidadeRepository.fetchAll();
  }

  public void delete(Long id) throws IOException, SQLException {
    unidadeRepository.delete(id);
  }

}
