package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class Produto_Fornecedor {

    /*chave primaria*/
    @PartitionKey
    UUID id_produto;
    UUID id_fornecedor;
}
