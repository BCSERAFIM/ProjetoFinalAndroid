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



    @GET("produto")
    Call<List<Produto>> carregarProdutos();
    @POST("usuario")
    Call<Usuario> cadastrarUsuario(@Body Usuario usuario);

}
