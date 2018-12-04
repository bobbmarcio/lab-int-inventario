package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.util.Date;

/**
 * Created by Alunoinf_2 on 03/12/2018.
 */
public class BaixaBem {
  private long id;
  private Date data;
  private String comentario;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getData() {
    return data;
  }

  public void setData(Date data) {
    this.data = data;
  }

  public String getComentario() {
    return comentario;
  }

  public void setComentario(String comentario) {
    this.comentario = comentario;
  }
}
