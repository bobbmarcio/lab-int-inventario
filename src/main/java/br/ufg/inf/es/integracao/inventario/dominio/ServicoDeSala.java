package br.ufg.inf.es.integracao.inventario.dominio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Sala;
import br.ufg.inf.es.integracao.inventario.repositorio.SalaRepository;

import javax.inject.Inject;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ServicoDeSala {

  @Inject
  private SalaRepository salaRepository;

  public void save(Sala sala) throws IOException, SQLException {
    salaRepository.save(sala);
  }

  public List<Sala> fetchAll() throws IOException, SQLException {
    return salaRepository.fetchAll();
  }

  public void delete(Long id) throws IOException, SQLException {
    salaRepository.delete(id);
  }

}
