package br.ufg.inf.es.integracao.inventario.config.database;

import br.ufg.inf.es.integracao.inventario.config.HookInicioAplicacao;
import org.apache.commons.io.IOUtils;

import javax.inject.Inject;
import javax.inject.Provider;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.function.Supplier;

public class HookExecutarDdl implements HookInicioAplicacao {

  private static final Charset DDL_FILE_CHARSET = Charset.forName("UTF-8");
  private static final String DDL_FILE_RESOURCE_PATH = "/ddl.sql";

  private final Supplier<Connection> connectionProvider;

  @Inject
  public HookExecutarDdl(final Supplier<Connection> connectionProvider) {
    this.connectionProvider = connectionProvider;
  }

  @Override
  public void executar() {
    try {
      final String ddl = resolverArquivoDdl();
      final Connection connection = connectionProvider.get();

      final PreparedStatement statement = connection.prepareStatement(ddl);
      statement.execute();
    } catch (final IOException | SQLException e) {
      throw new IllegalStateException("Houve um erro ao carregar a DDL da aplicação", e);
    }
  }

  private String resolverArquivoDdl() throws IOException {
    final InputStream stream =
      getClass().getResourceAsStream(DDL_FILE_RESOURCE_PATH);

    if (stream == null) {
      throw new RuntimeException("Arquivo DDL não encontrado");
    }

    return IOUtils.toString(stream, DDL_FILE_CHARSET);
  }

}
