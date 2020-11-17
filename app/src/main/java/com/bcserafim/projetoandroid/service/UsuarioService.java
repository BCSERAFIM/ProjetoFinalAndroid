package com.bcserafim.projetoandroid.service;

import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.entity.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {


    @GET("usuario")
    Call<List<Usuario>> carregarUsuarios();

    @POST("usuario")
    Call<Usuario> cadastrar(@Body Usuario usuario);

    @PUT("login")
    Call<Usuario> alterar(@Body Usuario usuario);

    @DELETE("usuario/{login}")
    Call<Usuario> remover(@Path("login") Integer login);

}
