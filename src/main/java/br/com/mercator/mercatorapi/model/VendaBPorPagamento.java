package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
public class VendaBPorPagamento {

    UUID id_empresa;
    UUID id_venda;
    Date dataVenda;
    Date dataPagamento;
    Float valor;
    /*chave primaria*/
    @PartitionKey /*duvida sobre anotação*/
    Boolean quitado;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
