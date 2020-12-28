package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter@Setter
public class Fornecedor {

    /*chave primaria*/
    @PartitionKey
    UUID id_fornecedor;
    String nome;
    String telefone;
    String email;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
