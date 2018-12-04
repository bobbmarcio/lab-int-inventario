package br.ufg.inf.es.integracao.inventario.dominio.seguranca;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.Usuario;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.UsuarioPapel;
import br.ufg.inf.es.integracao.inventario.repositorio.RepositorioUsuario;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.Optional;

@Singleton
public class ContextoSeguranca {

  private Usuario usuarioLogado = null;

  private final RepositorioUsuario repositorioUsuario;

  @Inject
  public ContextoSeguranca(RepositorioUsuario repositorioUsuario) {
    this.repositorioUsuario = repositorioUsuario;
  }

  public Optional<Usuario> getUsuarioLogado() {
    return Optional.ofNullable(usuarioLogado);
  }

  public boolean estaLogado() {
    return usuarioLogado != null;
  }

  public void logout() {
    usuarioLogado = null;
  }

  public void login(
    final String nome,
    final String senha
  ) throws JaLogadoException, LoginIncorretoException {
    if (usuarioLogado != null) {
      throw new JaLogadoException("Já há um usuário logado");
    }

    final Optional<Usuario> talvezUsuario =
      repositorioUsuario.encontrePorNome(nome);

    if (!talvezUsuario.isPresent()) {
      throw new LoginIncorretoException("Usuário não existe");
    }

    final Usuario usuario = talvezUsuario.get();

    if (!senhaBate(usuario, senha)) {
      throw new LoginIncorretoException("Senha incorreta");
    }

    usuarioLogado = usuario;

    final List<UsuarioPapel> papeis = repositorioUsuario.buscarPapeis(usuario);
    usuario.setPapeis(papeis);
  }

  private boolean senhaBate(final Usuario usuario, final String senha) {
    return senha != null && senha.equals(usuario.getSenha());
  }

}
