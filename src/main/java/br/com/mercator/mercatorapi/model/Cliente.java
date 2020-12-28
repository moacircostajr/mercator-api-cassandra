package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter@Setter
public class Cliente {

    /*chave primaria*/
    /*id composto: id empresa + / + id cliente*/
    @PartitionKey(0)
    private UUID id_empresa;
    @PartitionKey(1)
    private UUID id_cliente;
    private String nome;
    private String endereco;
    private String email;
    private String dataNascimento;
    private Timestamp momentoCriacao;
    private Timestamp momentoAtualizacao;

}
