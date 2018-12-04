package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

public class Usuario {

  private Long id;

  private String nome;

  private String email;

  private String senha;

  // TODO: Mapear diretamente a entidade unidade
  private Long idUnidade;

  private Collection<UsuarioPapel> papeis;

  public Usuario() {
  }

  public Usuario(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      nome = resultSet.getString("nome");
      email = resultSet.getString("email");
      senha = resultSet.getString("senha");
      idUnidade = resultSet.getLong("unidade_id");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Collection<UsuarioPapel> getPapeis() {
    return papeis;
  }

  public void setPapeis(Collection<UsuarioPapel> papeis) {
    this.papeis = papeis;
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

  public Long getIdUnidade() {
    return idUnidade;
  }

  public void setIdUnidade(Long idUnidade) {
    this.idUnidade = idUnidade;
  }

}
