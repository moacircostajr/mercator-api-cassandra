package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class ProdutoNComp {

    /*chave primaria*/
    /*id composto: id_empresa + / + id_produto*/
    @PartitionKey(0)
    UUID id_empresa;
    @PartitionKey(1)
    UUID id_produto;
    String discriminacao;
    String codigoDeBarras;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
