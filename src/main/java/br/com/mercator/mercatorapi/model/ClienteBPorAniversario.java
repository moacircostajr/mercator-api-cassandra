package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter@Setter
public class ClienteBPorAniversario {

    /*id composto: id empresa + / + id cliente*/
    UUID id_empresa;
    UUID id_cliente;
    String nome;
    String endereco;
    List<String> telefone;
    String email;
    /*INDEXADOR*/
    @PartitionKey /*duvida sobre anotação*/
    String dataNascimento;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
