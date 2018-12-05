package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class Predio {

  private Long id;

  private String nome;

  // TODO: Mapear diretamente a entidade unidade
  private Long idUnidade;

  private String nomeUnidade;

  public Predio() {
  }

  public Predio(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      nome = resultSet.getString("nome");
      nomeUnidade = resultSet.getString("unidade");
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

  public Long getIdUnidade() {
    return idUnidade;
  }

  public void setIdUnidade(Long idUnidade) {
    this.idUnidade = idUnidade;
  }

  public String getNomeUnidade() {
    return nomeUnidade;
  }

  public void setNomeUnidade(String nomeUnidade) {
    this.nomeUnidade = nomeUnidade;
  }
}
