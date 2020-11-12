package com.bcserafim.projetoandroid.service;

import android.content.Context;

import com.bcserafim.projetoandroid.entity.Produto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {



    @GET("/WebServiceAndroid/webresources/produto")
    Call<List<Produto>> carregarProdutos();

}
