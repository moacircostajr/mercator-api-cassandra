package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class Estoque {

    /*chave primaria*/
    /*id composto: id_empresa + / + id_estoque*/
    @PartitionKey(0)
    UUID id_empresa;
    @PartitionKey(1)
    UUID id_estoque;
    UUID id_produto;
    String discriminacaoProduto;
    Float precoVenda;
    Float quantidade;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
