package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
public class Despesa {

    /*chave primaria*/
    /*id composto: id_empresa + / + id_passivo*/
    @PartitionKey(0)
    UUID id_empresa;
    @PartitionKey(1)
    UUID id__passivo;
    String discriminacaoDespesa;
    Date dataDespesa;
    Date dataPagamento;
    Float quantidade;
    Float valor;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
