package com.bcserafim.projetoandroid.helper;

import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.service.ClienteService;
import com.bcserafim.projetoandroid.service.ProdutoService;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bcserafim.projetoandroid.activity.MainActivity.BASE_URL;

public class ClienteFacade {

    public static void cadastrar (Cliente cliente, final ClienteCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ClienteService service = retrofit.create(ClienteService.class);
        Call<Cliente> call = service.cadastrarCliente(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    public static void alterar (Cliente cliente, final ClienteCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ClienteService service = retrofit.create(ClienteService.class);
        Call<Cliente> call = service.alterar(cliente);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    public static void remover (Integer id, final ClienteCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ClienteService service = retrofit.create(ClienteService.class);
        Call<Cliente> call = service.remover(id);
        call.enqueue(new Callback<Cliente>() {
            @Override
            public void onResponse(Call<Cliente> call, Response<Cliente> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Cliente> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }



}
