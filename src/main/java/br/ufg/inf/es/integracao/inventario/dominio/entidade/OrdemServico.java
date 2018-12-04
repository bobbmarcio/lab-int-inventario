package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrdemServico {
  Long id;
  Date dataCriacao;
  String situacao;
  Date inicioServico;
  Date fimServico;
  Long responsavelId;
  Long bemPatrimonialId;

  public OrdemServico() {
  }

  public OrdemServico(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      dataCriacao = resultSet.getDate("data_criacao");
      situacao = resultSet.getString("situacao");
      inicioServico = resultSet.getDate("inicio_servico");
      fimServico = resultSet.getDate("fim_servico");
      responsavelId = resultSet.getLong("responsavel_id");
      bemPatrimonialId = resultSet.getLong("bem_patrimonial_id");
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

  public Date getDataCriacao() {
    return dataCriacao;
  }

  public void setDataCriacao(Date dataCriacao) {
    this.dataCriacao = dataCriacao;
  }

  public String getSituacao() {
    return situacao;
  }

  public void setSituacao(String situacao) {
    this.situacao = situacao;
  }

  public Date getInicioServico() {
    return inicioServico;
  }

  public void setInicioServico(Date inicioServico) {
    this.inicioServico = inicioServico;
  }

  public Date getFimServico() {
    return fimServico;
  }

  public void setFimServico(Date fimServico) {
    this.fimServico = fimServico;
  }

  public Long getResponsavelId() {
    return responsavelId;
  }

  public void setResponsavelId(Long responsavelId) {
    this.responsavelId = responsavelId;
  }

  public Long getBemPatrimonialId() {
    return bemPatrimonialId;
  }

  public void setBemPatrimonialId(Long bemPatrimonialId) {
    this.bemPatrimonialId = bemPatrimonialId;
  }
}
