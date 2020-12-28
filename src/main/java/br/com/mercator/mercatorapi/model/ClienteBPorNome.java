package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Getter@Setter
public class ClienteBPorNome {

    /*id composto: id empresa + / + id cliente*/
    UUID id_empresa;
    UUID id_cliente;
    /*INDEXADOR*/
    @PartitionKey /*duvida sobre anotação*/
    String nome;
    String endereco;
    List<String> telefone;
    String email;
    String dataNascimento;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
