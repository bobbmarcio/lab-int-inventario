package br.ufg.inf.es.integracao.inventario.infra;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by aluno on 05/12/18.
 */
public class Util {

  public static Date formataData(String data) throws Exception {
    if (data == null || data.equals(""))
      return null;
    Date date = null;
    try {
      DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
      date = (java.util.Date)formatter.parse(data);
    } catch (ParseException e) {
      throw e;
    }
    return date;
  }

}
