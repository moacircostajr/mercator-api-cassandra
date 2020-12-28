package br.com.mercator.mercatorapi.repository;

import br.com.mercator.mercatorapi.cassandradb.GeradorDB;
import org.apache.commons.codec.Charsets;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Util {

//    GeradorDB geradorDB = new GeradorDB();

    /*com o objeto e o contexto informados, será gerado o sql para ação no BD*/
    /*o contexto pode ser inserção ou alteração*/
    public static String prepareSqlDeInsercaoOuAlteracao(Object objeto) throws IllegalAccessException {
        Class classe = objeto.getClass();
        String nomeFamilyColumnCassandra = GeradorDB.busqueNomeDaClasseNoCassandra(classe);
        Field listaAtributos[] = classe.getDeclaredFields();
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO ");
        sql.append(nomeFamilyColumnCassandra);
        sql.append(" (");
        for (int a = 0; a < listaAtributos.length; a++) {
            if (a != listaAtributos.length-1) {
                sql.append(ajusteCamelCaseJavaCassandra(listaAtributos[a].getName()) + ", ");
            } else {
                sql.append(ajusteCamelCaseJavaCassandra(listaAtributos[a].getName()) + ")");

            }
        }
        sql.append(" VALUES (");
        for (int b = 0; b < listaAtributos.length; b++) {
            Object biscoito = null;
            if (b != listaAtributos.length-1) {
                Field listaAtributo = listaAtributos[b];
                listaAtributo.setAccessible(true);
                biscoito = listaAtributo.get(objeto);
                listaAtributo.setAccessible(false);
                if((biscoito instanceof String)||(biscoito instanceof Timestamp)){
                    sql.append("'"+biscoito+"'" + ", ");
                }else {
                    sql.append(biscoito + ", ");
                }
            } else {
                sql.append(biscoito + ");");

            }
        }
        return sql.toString();
    }

    public static String ajusteCamelCaseJavaCassandra(String nome) {
        byte[] nomeCodificado = nome.getBytes(Charsets.US_ASCII);
        StringBuffer nomeAjustado = new StringBuffer();
        for (int i = 0; i < nomeCodificado.length; i++) {
            if ((nomeCodificado[i] >= 65) && (nomeCodificado[i] <= 90)) {
                nomeAjustado.append("_");
                nomeAjustado.append((char)nomeCodificado[i]);
            } else {
                nomeAjustado.append((char)nomeCodificado[i]);
            }
        }
        return nomeAjustado.toString().toLowerCase();
    }
}
