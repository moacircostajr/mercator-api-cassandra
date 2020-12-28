package br.com.mercator.mercatorapi.repository;

import br.com.mercator.mercatorapi.model.Empresa;
import com.datastax.driver.core.ResultSet;

import java.util.List;
import java.util.UUID;

public interface EmpresaRepository {

    public ResultSet registrar(Empresa empresa);
    public Empresa buscarPorId(UUID uuid);
    public Empresa buscarPorCnpj(String cnpj);
    public Empresa buscarPorLogin(String login);
    public List<Empresa> listarEmpresas();
}
