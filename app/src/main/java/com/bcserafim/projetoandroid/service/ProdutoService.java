package com.bcserafim.projetoandroid.service;

import android.content.Context;

import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProdutoService {


    @GET("produto/{id}")
    Call<Produto> carregarProduto();

    @GET("produto")
    Call<List<Produto>> carregarTodosProdutos();

    @POST("produto")
    Call<Produto> cadastrarProduto(@Body Produto produto);

    @PUT("produto")
    Call<Produto> alterar (@Body Produto produto);

    @DELETE("produto/{id}")
    Call<Produto> remover (@Path("id") Integer id);

}
