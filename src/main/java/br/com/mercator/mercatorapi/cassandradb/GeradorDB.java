package br.com.mercator.mercatorapi.cassandradb;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.ArrayList;

@Getter
@Setter
public class GeradorDB {

    private Session session;
    //public final String CLIENTE = "clientes";
    //public final String CLIENTE_TELEFONE = "clientes_telefones";
    //public final String CLIENTEBPORANIVERSARIO = "clientes_BP_aniversario";
    //public final String CLIENTEBPORNOME = "clientes_BP_nome";
    //public final String COMPRA = "compras";
    //public final String COMPRAPRODNCOMP = "compras_prod_n_comp";
    //public final String DESPESA = "despesas";
    //public final String DESPESAPASSIVNCOMP = "despesas_passiv_n_comp";
    //public final String EMPRESA = "empresas";
    //public final String EMPRESABPORCNPJ = "empresas_BP_cnpj";
    //public final String EMPRESABPORLOGIN = "empresas_BP_login";
    //public final String ESTOQUE = "estoques";
    //public final String ESTOQUEPRODNCOMP = "estoques_prod_n_comp";
    //public final String ESTOQUEBPORCODBAR = "estoques_BP_codbar";
    //public final String ESTOQUEPRODNCOMPBPORCODBAR = "estoques_prod_n_comp_BP_codbar";
    //public final String ESTOQUEBPORNOMEPROD = "estoques_BP_nomeprod";
    //public final String ESTOQUEPRODNCOMPBPORNOMEPROD = "estoques_prod_n_comp_BP_nomeprod";
    //public final String FORNECEDOR = "fornecedores";
    //public final String FORNECEDOR_PRODUTO_PRECO = "fornecedores_produtos_precos";
    //public final String FORNECEDOR_PRODUTO_PRECO_PRODNCOMP = "fornecedores_produtos_n_comp_precos";
    //public final String PAGAMENTO = "pagamentos";
    //public final String PASSIVO = "passivos";
    //public final String PRODUTO = "produtos";
    //public final String PRODUTONCOMP = "produtos_n_comp";
    //public final String PRODUTOBPORCODBAR = "produtos_BP_codbar";
    //public final String PRODUTONCOMPBPORCODBAR = "produtos_n_comp_BP_codbar";
    //public final String PRODUTOBPORNOME = "produtos_BP_nome";
    //public final String PRODUTONCOMPBPORNOME = "produtos_n_comp_BP_nome";
    //public final String PRODUTO_FORNECEDOR = "produtos_fornecedores";
    //public final String PRODUTO_FORNECEDOR_PRODNCOMP = "produtos_n_comp_fornecedores";
    //public final String VENDA = "vendas";
    //public final String VENDA_PRODUTO = "vendas_produtos";
    //public final String VENDA_PRODUTO_PRODNCOMP = "vendas_produtos_n_comp";
    //public final String VENDABPORPAGAMENTO = "vendas_BP_pagamentos";

    public GeradorDB(Session session) {
        this.session = session;
    }

    public GeradorDB() {
    }

    public void crieKeyspace(String keyspaceName) {
        StringBuilder sb =
                new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                        .append(keyspaceName).append(" WITH replication = {")
                        .append("'class':'SimpleStrategy', 'replication_factor':1};");

        String query = sb.toString();
        session.execute(query);
    }

    /*método que cria, no banco de dados cassandra, as column family (tabelas), a partir da leitura das classes modelo*/
    public void crieColumnFamily(String keyspacename, Class cls, String nome) {
//        array que armazena os nomes das chaves primárias
        ArrayList<String> chaves = new ArrayList<String>();
//        variável que armazena nome de atributo de classe
        String nomeAtributo = null;
//        variavel que armazena tipo de atributo de classe
        String tipoAtributo = null;
//        variavel que armazena a posicao do caractere /, que separa tipo de atributo de nome de atributo
        Integer posicao = null;
        try {
//            determina que seja usado o keyspace indicado nos argumentos do metodo
            session.execute("USE " + keyspacename + ";");
//            povoa um vetor do tipo field com os atributos declarados na classe indicada nos argumentos
            Field fieldlist[] = cls.getDeclaredFields();
//            faz a varredura do vetor fieldlist[] buscando os atributos que estão anotados com @PartitionKey
//            quando um atributo é localizado, ele é copiado para o ArrayList chaves
            for (Field field : fieldlist
            ) {
                if (field.isAnnotationPresent(PartitionKey.class)) {
                    chaves.add(field.getName());
                }
            }
//            cria um vetor para o armazenamento de atributos no formato String, do mesmo tamanho do vetor fieldlist
            String[] arrayAtributos = new String[fieldlist.length];
//            cria um vetor para o armazenamento de atributos e tipos prontos para serem iseridos no comando SQL,
//            no formato String, do mesmo tamanho do vetor arrayAtributos
            String[] resultado = new String[arrayAtributos.length];
//            faz a varredura do vetor fieldlist, obtem os nomes e os tipos dos atributos e povoa o vetor arrayAtributos
            for (int i
                 = 0; i < fieldlist.length; i++) {
                Field fld = fieldlist[i];
                /*System.out.println("name = " + fld.getName());*/
                nomeAtributo = fld.getName();
                /*System.out.println("decl class = " +
                        fld.getDeclaringClass());*/
                /*System.out.println("type = " + fld.getType());*/
                tipoAtributo = fld.getType().toString();
                int mod = fld.getModifiers();
                /*System.out.println("modifiers = " +
                        Modifier.toString(mod));
                System.out.println("-----");*/
//                converte o tipo do formato java para o formato cassandra e insere o par atributo/tipo no arrayAtributos
//                arrayAtributos[i] = nomeAtributo + "/" + convertaTipoDadosJavaCassandra(tipoAtributo);


//                converte o tipo do formato java para o formato cassandra e insere o par "atributo tipo" no vetor resultado
                resultado[i] = nomeAtributo + " " + convertaTipoDadosJavaCassandra(tipoAtributo);
            }
//            faz a varredura do vetor arrayAtributos, buscando o caractere /
//            o que estiver anterior à / é salvo no array resultado seguido de espaço e do tipo de dados no cassandra
//            o vetor resultado atuará na formação do SQL
            /*for (int i = 0; i < arrayAtributos.length; i++) {
                posicao = arrayAtributos[i].indexOf('/');
                if (posicao >= 0) {
                    resultado[i] = arrayAtributos[i].substring(0, posicao) + " " + arrayAtributos[i].substring(posicao + 1, arrayAtributos[i].length());
                }
            }*/

//          o objeto sb contem o sql a ser enviado para execução no cassandra
            StringBuilder sb = new StringBuilder("CREATE TABLE IF NOT EXISTS ")
//                    é adicionado ao string de formação do sql o nome da family column indicado nos argumentos
                    .append(nome).append(" (");
//            o vetor resultado é varrido e seus valores são inseridos no string de formação do sql
            for (int j = 0; j < resultado.length; j++) {
//                se for o último valor do vetor, não será inserido vírgula após a insercao de seu conteudo
                if (j == (resultado.length - 1)) {
                    sb.append(resultado[j]);
//                    caso não seja o ultimo valor, será inserido virgula para a inserção de mais valores
                } else {
                    sb.append(resultado[j] + ", ");
                }
            }
//            é incluído no string do SQL a definição das chaves primárias, seguindo o mesmo princípio de
//            inclusão de vírgula caso hajam mais campos a serem inseridos e o fechamento do comando caso não hajam
//            mais valores a inserir
            sb.append(", PRIMARY KEY (");
            for (int k = 0; k < chaves.size(); k++) {
                if (k != chaves.size() - 1) {
                    sb.append(chaves.get(k) + ", ");
                } else {
                    sb.append(chaves.get(k) + ")");
                }
            }
            sb.append(");");
            String query = sb.toString();
//            mostra no console o comando SQL gerado
            System.out.println(query);
//            executa o comando SQL
            session.execute(query);

        } catch (
                Throwable e) {
            System.err.println(e);
        }

    }

    private String convertaTipoDadosJavaCassandra(String tipoAtributo) {
//        System.out.println(tipoAtributo);
        String tipo = null;
        switch (tipoAtributo) {
            case "class java.util.UUID":
                tipo = "uuid";
                break;
            case "class java.lang.String":
                tipo = "text";
                break;
            case "class java.sql.Timestamp":
                tipo = "timestamp";
                break;
            case "class java.util.Date":
                tipo = "date";
                break;
            case "class java.lang.Float":
                tipo = "float";
                break;
        }
        return tipo;
    }

    public static String busqueNomeDaClasseNoCassandra(Class classe) {
        String classeBuscada = classe.getSimpleName().toUpperCase();
        String retorno = null;
        switch (classeBuscada) {
            case "CLIENTE":
                retorno = "clientes";
                break;
            case "CLIENTE_TELEFONE":
                retorno = "clientes_telefones";
                break;
            case "CLIENTEBPORANIVERSARIO":
                retorno = "clientes_BP_aniversario";
                break;
            case "CLIENTEBPORNOME":
                retorno = "clientes_BP_nome";
                break;
            case "COMPRA":
                retorno = "compras";
                break;
            case "COMPRAPRODNCOMP":
                retorno = "compras_prod_n_comp";
                break;
            case "DESPESA":
                retorno = "despesas";
                break;
            case "DESPESAPASSIVNCOMP":
                retorno = "despesas_passiv_n_comp";
                break;
            case "EMPRESA":
                retorno = "empresas";
                break;
            case "EMPRESABPORCNPJ":
                retorno = "empresas_BP_cnpj";
                break;
            case "EMPRESABPORLOGIN":
                retorno = "empresas_BP_login";
                break;
            case "ESTOQUE":
                retorno = "estoques";
                break;
            case "ESTOQUEPRODNCOMP":
                retorno = "estoques_prod_n_comp";
                break;
            case "ESTOQUEBPORCODBAR":
                retorno = "estoques_BP_codbar";
                break;
            case "ESTOQUEPRODNCOMPBPORCODBAR":
                retorno = "estoques_prod_n_comp_BP_codbar";
                break;
            case "ESTOQUEBPORNOMEPROD":
                retorno = "estoques_BP_nomeprod";
                break;
            case "ESTOQUEPRODNCOMPBPORNOMEPROD":
                retorno = "estoques_prod_n_comp_BP_nomeprod";
                break;
            case "FORNECEDOR":
                retorno = "fornecedores";
                break;
            case "FORNECEDOR_PRODUTO_PRECO":
                retorno = "fornecedores_produtos_precos";
                break;
            case "FORNECEDOR_PRODUTO_PRECO_PRODNCOMP":
                retorno = "fornecedores_produtos_n_comp_precos";
                break;
            case "PAGAMENTO":
                retorno = "pagamentos";
                break;
            case "PASSIVO":
                retorno = "passivos";
                break;
            case "PRODUTO":
                retorno = "produtos";
                break;
            case "PRODUTONCOMP":
                retorno = "produtos_n_comp";
                break;
            case "PRODUTOBPORCODBAR":
                retorno = "produtos_BP_codbar";
                break;
            case "PRODUTONCOMPBPORCODBAR":
                retorno = "produtos_n_comp_BP_codbar";
                break;
            case "PRODUTOBPORNOME":
                retorno = "produtos_BP_nome";
                break;
            case "PRODUTONCOMPBPORNOME":
                retorno = "produtos_n_comp_BP_nome";
                break;
            case "PRODUTO_FORNECEDOR":
                retorno = "produtos_fornecedores";
                break;
            case "PRODUTO_FORNECEDOR_PRODNCOMP":
                retorno = "produtos_n_comp_fornecedores";
                break;
            case "VENDA":
                retorno = "vendas";
                break;
            case "VENDA_PRODUTO":
                retorno = "vendas_produtos";
                break;
            case "VENDA_PRODUTO_PRODNCOMP":
                retorno = "vendas_produtos_n_comp";
                break;
            case "VENDABPORPAGAMENTO":
                retorno = "vendas_BP_pagamentos";
                break;
        }
        return retorno;
    }
}
