package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
public class Fornecedor_Produto_Preco {

    /*chave primaria*/
    @PartitionKey
    UUID id_fornecedor;
    UUID id_produto;
    UUID id_produto_empresa;
    Date data;
    Float preco;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
