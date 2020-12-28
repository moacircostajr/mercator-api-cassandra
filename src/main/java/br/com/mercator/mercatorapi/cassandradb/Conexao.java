package br.com.mercator.mercatorapi.cassandradb;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Conexao {

    private Cluster cluster;
    private Session session;
    private final String node = "127.0.0.1";
    private final Integer port = 9042;
    public final String KEYSPACE = "mercator_teste";

    public void conecte() {
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if (port != null) {
            b.withPort(port);
        }
        cluster = b.build();
        session = cluster.connect();
    }

    public Session obterSessao() {
        return this.session;
    }

    public void fecheConexao() {
        session.close();
        cluster.close();
    }

}
