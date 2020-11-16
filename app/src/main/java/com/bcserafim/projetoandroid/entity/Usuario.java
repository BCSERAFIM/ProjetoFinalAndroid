package com.bcserafim.projetoandroid.entity;

import java.io.Serializable;

public class Usuario implements Serializable {

    private Integer login;
    private String senha;

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
