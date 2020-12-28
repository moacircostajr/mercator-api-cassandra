package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
public class DespesaPassivNComp {

    /*chave primaria*/
    @PartitionKey
    UUID id_passivo;
    String discriminacaoDespesa;
    Date dataDespesa;
    Date dataPagamento;
    Float quantidade;
    Float valor;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
