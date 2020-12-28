package br.com.mercator.mercatorapi.repository.Impl;

import br.com.mercator.mercatorapi.model.Empresa;
import br.com.mercator.mercatorapi.repository.EmpresaRepository;
import br.com.mercator.mercatorapi.repository.Util;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;

import java.util.List;
import java.util.UUID;

public class EmpresaRepositoryImpl implements EmpresaRepository {

    Session sessao;

    public EmpresaRepositoryImpl(Session sessao) {
        this.sessao = sessao;
    }

    public EmpresaRepositoryImpl() {
    }

    //    TODO: incluir outros bancos de dados
    @Override
    public ResultSet registrar(Empresa empresa) {
        try {
//            System.out.println(Util.prepareSqlDeInsercaoOuAlteracao(empresa));
            return sessao.execute(Util.prepareSqlDeInsercaoOuAlteracao(empresa));
        } catch (IllegalAccessException e) {
//            exceção que não obrigatoriamente devera ser tratada
            throw new RuntimeException(e);
        }
    }

    @Override
    public Empresa buscarPorId(UUID uuid) {
        return null;
    }

    @Override
    public Empresa buscarPorCnpj(String cnpj) {
        return null;
    }

    @Override
    public Empresa buscarPorLogin(String login) {
        return null;
    }

    @Override
    public List<Empresa> listarEmpresas() {
        return null;
    }

}
