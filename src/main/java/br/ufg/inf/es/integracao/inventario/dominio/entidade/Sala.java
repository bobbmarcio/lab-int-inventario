package br.ufg.inf.es.integracao.inventario.dominio.entidade;

public class Sala {
  private Long id;
  private String nome;
  private String codigo;
  private Long predioId;
  private Long departamentoId;

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

  public Long getDepartamentoId() {
    return departamentoId;
  }

  public void setDepartamentoId(Long departamentoId) {
    this.departamentoId = departamentoId;
  }

  public Long getPredioId() {
    return predioId;
  }

  public void setPredioId(Long predioId) {
    this.predioId = predioId;
  }
}
