package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter@Setter
public class EmpresaBPorLogin {

    UUID id_empresa;
    /*chave primaria*/
    @PartitionKey
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
