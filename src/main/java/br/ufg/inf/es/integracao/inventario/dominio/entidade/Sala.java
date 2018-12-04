package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Sala {

  private Long id;
  private String nome;
  private String codigo;

  // TODO: Mapear diretamente a entidade predio
  private Long predioId;

  // TODO: Mapear diretamente a entidade departamento
  private Long departamentoId;

  public Sala() {
  }

  public Sala(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      nome = resultSet.getString("nome");
      codigo = resultSet.getString("codigo");
      predioId = resultSet.getLong("predio_id");
      departamentoId = resultSet.getLong("departamento_id");
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

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public Long getPredioId() {
    return predioId;
  }

  public void setPredioId(Long predioId) {
    this.predioId = predioId;
  }

  public Long getDepartamentoId() {
    return departamentoId;
  }

  public void setDepartamentoId(Long departamentoId) {
    this.departamentoId = departamentoId;
  }
}
