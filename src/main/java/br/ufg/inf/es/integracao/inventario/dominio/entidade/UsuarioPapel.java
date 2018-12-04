package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioPapel {

  private Usuario usuario;

  private Long usuarioId;

  private Papel papel;

  public UsuarioPapel() {
  }

  public UsuarioPapel(ResultSet resultSet) {
    try {
      usuarioId = resultSet.getLong("usuario_id");
      papel = Papel.valueOf(resultSet.getString("papel").toUpperCase());
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public enum Papel {
    ADMIN,
    USUARIO,
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public Papel getPapel() {
    return papel;
  }

  public void setPapel(Papel papel) {
    this.papel = papel;
  }
}
