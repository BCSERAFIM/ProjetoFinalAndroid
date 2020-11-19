package com.bcserafim.projetoandroid.service;

import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.ItemPedido;
import com.bcserafim.projetoandroid.entity.Pedido;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PedidoService {

    @GET("pedido/{id}")
    Call<Pedido> carregarPedido();

    @GET("pedido")
    Call<List<Pedido>>carregarTodosPedidos();

    @POST("pedido")
    Call<Pedido>cadastrarPedido(@Body Pedido cliente);
}
