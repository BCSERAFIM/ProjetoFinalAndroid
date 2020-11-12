package com.bcserafim.projetoandroid.service;

import com.bcserafim.projetoandroid.entity.Produto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProdutoService {



    @GET("produto")
    Call<List<Produto>> carregarProdutos();

}
