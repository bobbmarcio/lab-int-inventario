package br.ufg.inf.es.integracao.inventario.dominio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Usuario;
import br.ufg.inf.es.integracao.inventario.repositorio.RepositorioUsuario;

import javax.inject.Inject;
import java.util.List;

public class GerenciadorUsuarios {

  @Inject
  private RepositorioUsuario repositorioUsuario;

  public Usuario cadastrarNovoUsuario(
    final String nome,
    final String email,
    final String senha
  ) {
    final Usuario usuario = new Usuario();
    usuario.setNome(nome);
    usuario.setEmail(email);
    usuario.setSenha(senha);

    repositorioUsuario.inserirUsuario(usuario);
    return usuario;
  }

  public void atualizaUsuario(
    final long idDoUsuario,
    final String novoNome,
    final String novaSenha,
    final String novoEmail
  ) {
    final Usuario usuario = new Usuario();
    usuario.setNome(novoNome);
    usuario.setEmail(novoEmail);
    usuario.setNome(novaSenha);

    repositorioUsuario.atualizeUsuario(idDoUsuario, usuario);
  }

  public void apagarUsuarioPorId(final long id) {
    repositorioUsuario.apagaUsuario(id);
  }

  public List<Usuario> listarTodos() {
    return repositorioUsuario.encontreTodos();
  }

}
