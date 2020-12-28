package br.com.mercator.mercatorapi.cassandradb;

import br.com.mercator.mercatorapi.model.*;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GeradorDBTest {


    private GeradorDB geradorDB;
    private Conexao conexao = new Conexao();
    private Session session;
    final String KEYSPACE_NAME = "mercator_teste";

    @Before
    public void connect() {
        conexao.conecte();
        this.session = conexao.obterSessao();
        geradorDB = new GeradorDB(session);

//        geradorDB.crieKeyspace(KEYSPACE_NAME);
//        geradorDB.crieColumnFamily(KEYSPACE_NAME, Empresa.class, geradorDB.getEMPRESA());

    }

    @Test
    public void whenCreatingAKeyspace_thenCreated() {
//        geradorDB.crieKeyspace(KEYSPACE_NAME);

        ResultSet result =
                session.execute("SELECT * FROM system_schema.keyspaces;");

        List<String> matchedKeyspaces = result.all()
                .stream()
                .filter(r -> r.getString(0).equals(KEYSPACE_NAME.toLowerCase()))
                .map(r -> r.getString(0))
                .collect(Collectors.toList());

        assertEquals(matchedKeyspaces.size(), 1);
        assertTrue(matchedKeyspaces.get(0).equals(KEYSPACE_NAME.toLowerCase()));

    }


    @Test
    public void whenCreatingATable_thenCreatedCorrectly() {
        Class classe = Compra.class;

        geradorDB.crieColumnFamily(KEYSPACE_NAME, classe, geradorDB.busqueNomeDaClasseNoCassandra(classe));

        ResultSet result = session.execute(
                "SELECT * FROM " + KEYSPACE_NAME + "." + geradorDB.busqueNomeDaClasseNoCassandra(classe) + ";");

        List<String> columnNames = result.getColumnDefinitions().asList().stream()
                        .map(cl -> cl.getName())
                        .collect(Collectors.toList());

        assertEquals(columnNames.size(), 10);
        assertTrue(columnNames.contains("id_compra"));
        assertTrue(columnNames.contains("datacompra"));
        assertTrue(columnNames.contains("dataentrada"));

        session.execute("DROP TABLE " + KEYSPACE_NAME + "." + geradorDB.busqueNomeDaClasseNoCassandra(classe) + ";");
    }

    @After
    public void tearDown() throws Exception {
//        session.execute("DROP KEYSPACE " + KEYSPACE_NAME + ";");
        conexao.fecheConexao();
    }
}
