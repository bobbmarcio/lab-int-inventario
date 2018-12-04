package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Unidade {

  private Long id;
  private String nome;
  private String endereco;
  private String cidade;
  private String uf;
  private String cep;

  public Unidade() {
  }

  public Unidade(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      nome = resultSet.getString("nome");
      endereco = resultSet.getString("endereco");
      cidade = resultSet.getString("cidade");
      uf = resultSet.getString("uf");
      cep = resultSet.getString("cep");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getEndereco() {
    return endereco;
  }

  public void setEndereco(String endereco) {
    this.endereco = endereco;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
