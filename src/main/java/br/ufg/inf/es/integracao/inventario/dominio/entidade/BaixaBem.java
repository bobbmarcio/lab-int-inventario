package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Alunoinf_2 on 03/12/2018.
 */
public class BaixaBem {

  public BaixaBem(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      data = resultSet.getDate("data");
      comentario = resultSet.getString("comentario");
    //  bemPatrimonialId = resulSet.getLong(,"bemPatrimonialId")
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public BaixaBem(){

  }

  private long id;
  private Date data;
  private String comentario;

  private int bemPatrimonialId;

  public long getId() {
    return id;
  }

  public void setId(long id) {
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

  public int getBemPatrimonialId() {
    return bemPatrimonialId;
  }

  public void setBemPatrimonialId(int bemPatrimonialId) {
    this.bemPatrimonialId = bemPatrimonialId;
  }

}
