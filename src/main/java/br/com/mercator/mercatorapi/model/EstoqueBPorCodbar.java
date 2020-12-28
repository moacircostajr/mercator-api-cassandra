package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class EstoqueBPorCodbar {

    UUID id_empresa;
    UUID id_estoque;
    /*chave primaria*/
    @PartitionKey /*duvida sobre anotação*/
    String codBarProduto;
    UUID id_produto;
    String discriminacaoProduto;
    Float precoVenda;
    Float quantidade;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
