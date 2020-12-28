package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
public class Venda {

    /*chave primaria*/
    /*id composto: id_empresa + / + id_venda*/
    @PartitionKey(0)
    UUID id_empresa;
    @PartitionKey(1)
    UUID id_venda;
    Date dataVenda;
    Date dataPagamento;
    Float valor;
    Boolean quitado;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
