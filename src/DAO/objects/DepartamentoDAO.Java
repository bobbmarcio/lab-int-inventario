package br.com.devmedia.dataaccessobject;

import java.util.List;

public interface DepartamentoDAO {

    public void addDepartamento(Departamento departamento);

    public Departamento getDepartamento(int id);

    public void removerDepartamento(int id);

    public void atualizarDepartamento(Departamento departamento);

    public List<Departamento> getTodosDepartamentos();

}