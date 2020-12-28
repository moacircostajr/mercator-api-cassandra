package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class Venda_Produto_ProdNComp {


    /*chave primaria*/
    /*id composto: id_empresa + / + id_venda*/
    @PartitionKey(0)
    UUID id_empresa;
    @PartitionKey(1)
    UUID id_venda;
    UUID id_empresa_produto;
    String discriminacaoProduto;
    Float quantidade;
}
