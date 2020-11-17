package com.bcserafim.projetoandroid.entity;

import java.io.Serializable;

public class Produto implements Serializable {

    private Integer id;
    private String descricao;
    private Integer Qtd;

    public Integer getQtd() {
        return Qtd;
    }

    public void setQtd(Integer qtd) {
        Qtd = qtd;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
