package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class EstoqueBPorNomeProd {

    UUID id_empresa;
    UUID id_estoque;
    UUID id_produto;
    /*chave primaria*/
    @PartitionKey /*duvida sobre anotação*/
    String discriminacaoProduto;
    Float precoVenda;
    Float quantidade;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
