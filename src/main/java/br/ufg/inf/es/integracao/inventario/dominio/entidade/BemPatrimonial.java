package br.ufg.inf.es.integracao.inventario.dominio.entidade;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Created by Alunoinf_2 on 03/12/2018.
 */
public class BemPatrimonial {

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  private long id;
  private Date dataAquisicao;
  private String numeroTombamento;
  private String numeroNotaFiscal;
  private long vidaUtil;
  private String especificacao;;
  private Date dataGarantia;
  private String marca;
  private String grupo;
  private BigDecimal valorCompra;
  private boolean incorporado;

  public BemPatrimonial(){

  }

  public BemPatrimonial(final ResultSet resultSet) {
    try {
      id = resultSet.getLong("id");
      dataAquisicao = resultSet.getDate("dataAquisicao");
      numeroTombamento = resultSet.getString("numeroTombamento");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public Date getDataAquisicao() {
    return dataAquisicao;
  }

  public void setDataAquisicao(Date dataAquisicao) {
    this.dataAquisicao = dataAquisicao;
  }

  public String getNumeroTombamento() {
    return numeroTombamento;
  }

  public void setNumeroTombamento(String numeroTombamento) {
    this.numeroTombamento = numeroTombamento;
  }

  public String getNumeroNotaFiscal() {
    return numeroNotaFiscal;
  }

  public void setNumeroNotaFiscal(String numeroNotaFiscal) {
    this.numeroNotaFiscal = numeroNotaFiscal;
  }

  public long getVidaUtil() {
    return vidaUtil;
  }

  public void setVidaUtil(long vidaUtil) {
    this.vidaUtil = vidaUtil;
  }

  public String getEspecificacao() {
    return especificacao;
  }

  public void setEspecificacao(String especificacao) {
    this.especificacao = especificacao;
  }

  public Date getDataGarantia() {
    return dataGarantia;
  }

  public void setDataGarantia(Date dataGarantia) {
    this.dataGarantia = dataGarantia;
  }

  public String getMarca() {
    return marca;
  }

  public void setMarca(String marca) {
    this.marca = marca;
  }

  public String getGrupo() {
    return grupo;
  }

  public void setGrupo(String grupo) {
    this.grupo = grupo;
  }

  public BigDecimal getValorCompra() {
    return valorCompra;
  }

  public void setValorCompra(BigDecimal valorCompra) {
    this.valorCompra = valorCompra;
  }

  public boolean isIncorporado() {
    return incorporado;
  }

  public void setIncorporado(boolean incorporado) {
    this.incorporado = incorporado;
  }
}

