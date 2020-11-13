package com.bcserafim.projetoandroid.service;

import android.content.Context;

import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.entity.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ProdutoService {


    @GET("produto/{id}")
    Call<Produto> carregarProduto();
    @GET("produto")
    Call<List<Produto>> carregarTodosProdutos();
    @POST("produto")
    Call<Produto> cadastrarProduto(@Body Produto produto);
    @POST("usuario")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);


}
