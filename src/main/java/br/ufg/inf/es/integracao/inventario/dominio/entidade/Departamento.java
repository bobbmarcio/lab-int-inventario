package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class Departamento {

  private Long id;

  private String nome;

  // TODO: Mapear diretamente a entidade usuário
  private Long chefe;

  public Departamento() {
  }

  public Departamento(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      nome = resultSet.getString("nome");
      chefe = resultSet.getLong("chefe");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Long getChefe() {
    return chefe;
  }

  public void setChefe(Long chefe) {
    this.chefe = chefe;
  }
}
