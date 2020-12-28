package br.com.mercator.mercatorapi.repository.Impl;

import br.com.mercator.mercatorapi.cassandradb.AcessoDB;
import br.com.mercator.mercatorapi.cassandradb.Conexao;
import br.com.mercator.mercatorapi.model.Empresa;
import br.com.mercator.mercatorapi.repository.Util;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.utils.UUIDs;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;

import static org.junit.Assert.*;

public class EmpresaRepositoryImplTest extends AcessoDB {

    /*Conexao conexao = new Conexao();
    Session sessao;*/
    EmpresaRepositoryImpl empresaRepository;

    private final String CNPJ = "28081755000187";
    private final String EMAIL = "testetestecombr";
    private final String ENDERECO = "Rua dos Tabajaras 1234 Centro Amontada CE";
    private final String LOGIN = "testeteste";
    private final String SENHA = "12345";
    private final String NOMEFANTASIA = "Caverna do Dragao";
    private final String TELEFONE = "9898981234";

    @Before
    public void setUp() throws Exception {
        /*conexao.conecte();
        sessao = conexao.obterSessao();*/
        empresaRepository = new EmpresaRepositoryImpl(super.acesseDB());
    }

    @Test
    public void testeEmpresaRepositorySalvar() throws IllegalAccessException {
        Empresa empresa = new Empresa();
        empresa.setId_empresa(UUIDs.timeBased());
        empresa.setCnpj(CNPJ);
        empresa.setEmail(EMAIL);
        empresa.setEndereco(ENDERECO);
        empresa.setLogin(LOGIN);
        empresa.setSenha(SENHA);
        empresa.setNomeFantasia(NOMEFANTASIA);
        empresa.setTelefone(TELEFONE);
        empresa.setMomentoCriacao(Timestamp.from(Instant.now()));
        empresa.setMomentoAtualizacao(Timestamp.from(Instant.now()));

        /*String classeEmpresa = empresa.getClass().getSimpleName();
        System.out.println(classeEmpresa);*/

        super.useSessao().execute("USE mercator_teste;");

        ResultSet resultado = empresaRepository.salvar(empresa);

        assertEquals(resultado.wasApplied(), true);

//        TODO: realizar busca da empresa no banco de dados
//        TODO: testar se os valores obtidos conferem com os valores registrados
//        assertEquals(empresa.getId_empresa(), empresaBuscada.getId_empresa());
        super.useSessao().execute("DELETE FROM empresas WHERE id_empresa=" + empresa.getId_empresa() + ";");
    }


    @After
    public void tearDown() throws Exception {
        super.fecheDB();
    }

}