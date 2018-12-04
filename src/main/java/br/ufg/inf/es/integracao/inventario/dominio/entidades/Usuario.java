package br.ufg.inf.es.integracao.inventario.dominio.entidades;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Usuario {

  private Long id;

  private String nome;

  private String email;

  private String senha;

  // TODO: Mapear diretamente a entidade sala
  private Long idSala;

  public Usuario() {
  }

  public Usuario(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      nome = resultSet.getString("nome");
      email = resultSet.getString("email");
      senha = resultSet.getString("senha");
      idSala = resultSet.getLong("sala_id");
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public Long getIdSala() {
    return idSala;
  }

  public void setIdSala(Long idSala) {
    this.idSala = idSala;
  }

}
