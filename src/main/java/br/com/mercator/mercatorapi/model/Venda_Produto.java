package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
public class Venda_Produto {

    /*chave primaria*/
    /*id composto: id_empresa + / + id_venda*/
    @PartitionKey(0)
    UUID id_empresa;
    @PartitionKey(1)
    UUID id_venda;
    UUID id_produto;
    String discriminacaoProduto;
    Float quantidade;
}
