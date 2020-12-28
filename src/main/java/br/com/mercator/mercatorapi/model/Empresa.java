package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class Empresa {

    /*chave primaria*/
    @PartitionKey
    UUID id_empresa;
    String login;
    String senha;
    String email;
    String nomeFantasia;
    String cnpj;
    String telefone;
    String endereco;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
