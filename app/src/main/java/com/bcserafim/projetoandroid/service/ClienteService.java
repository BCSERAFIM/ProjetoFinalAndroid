package com.bcserafim.projetoandroid.service;

import com.bcserafim.projetoandroid.entity.Cliente;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClienteService {

    @GET("cliente/{id}")
    Call<Cliente> carregarCliente();

    @GET("cliente")
    Call<List<Cliente>>carregarTodosClientes();

    @POST("cliente")
    Call<Cliente>cadastrarCliente(@Body Cliente cliente);

    @PUT("cliente")
    Call<Cliente> alterar (@Body Cliente cliente);

    @DELETE("cliente/{id}")
    Call<Cliente> remover (@Path("id") Integer id);

}
