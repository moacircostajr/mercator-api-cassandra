package br.com.mercator.mercatorapi.cassandradb;

import com.datastax.driver.core.Session;

public abstract class AcessoDB {

    Conexao conexao = new Conexao();

    public Session acesseDB() {
        conexao.conecte();
        return conexao.obterSessao();

    }

    public Session useSessao() {
        return conexao.obterSessao();
    }

    public void fecheDB() {
        conexao.fecheConexao();
    }

}
