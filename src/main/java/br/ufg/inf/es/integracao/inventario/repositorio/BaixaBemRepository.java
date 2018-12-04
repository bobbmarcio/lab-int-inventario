package br.ufg.inf.es.integracao.inventario.repositorio;

import br.ufg.inf.es.integracao.inventario.dominio.entidade.BaixaBem;
import br.ufg.inf.es.integracao.inventario.dominio.entidade.Predio;
import org.apache.commons.io.IOUtils;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public class BaixaBem {
  private final Supplier<Connection> connectionProvider;

  private static final Charset DDL_FILE_CHARSET = Charset.forName("UTF-8");
  private static final String INSERT_SQL = "/baixabem/insert_baixabem.sql";
  private static final String SELECT_SQL = "/baixabem/select_baixabem.sql";
  private static final String DELETE_SQL = "/baixabem/delete_baixabem.sql";

  @Inject
  public BaixaBemRepository(final Supplier<Connection> connectionProvider) {
    this.connectionProvider = connectionProvider;
  }
}
