package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
public class Compra {

    /*CREATE TABLE sales(countryCode text, areaCode text, sales int, PRIMARY KEY((countryCode, areaCode)));
   exemplo de chave composta no CQL
   */
    /*chave primaria*/
    /*id composto: id_empresa + / + id_compra*/
    @PartitionKey(0)
    UUID id_empresa;
    @PartitionKey(1)
    UUID id_compra;
    UUID id_produto;
    String discriminacaoProduto;
    Date dataCompra;
    Date dataEntrada;
    Date dataPagamento;
    Float quantidade;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
