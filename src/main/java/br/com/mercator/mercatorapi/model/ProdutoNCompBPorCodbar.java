package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class ProdutoNCompBPorCodbar {

    UUID id_empresa;
    UUID id_produto;
    String discriminacao;
    /*chave primaria*/
    @PartitionKey /*duvida sobre anotação*/
    String codigoDeBarras;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
