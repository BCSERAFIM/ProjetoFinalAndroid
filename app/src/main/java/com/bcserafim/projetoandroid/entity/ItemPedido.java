package com.bcserafim.projetoandroid.entity;

import java.io.Serializable;

public class ItemPedido implements Serializable {

    private int quantidade;
    private Produto produto;

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
