package br.com.mercator.mercatorapi.model;

import com.datastax.driver.mapping.annotations.PartitionKey;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Getter@Setter
public class CompraProdNComp {

    /*chave primaria*/
    /*id composto: id_empresa + / + id_compra*/
    @PartitionKey(0)
    UUID id_empresa;
    @PartitionKey(1)
    UUID id_compra;
    UUID id_empresa_produtoNaoComp;
    String discriminacaoProduto;
    Date dataCompra;
    Date dataEntrada;
    Date dataPagamento;
    Float quantidade;
    Timestamp momentoCriacao;
    Timestamp momentoAtualizacao;

}
