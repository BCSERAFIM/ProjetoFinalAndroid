package com.bcserafim.projetoandroid.helper.facade;

import com.bcserafim.projetoandroid.BuildConfig;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.ItemPedido;
import com.bcserafim.projetoandroid.entity.Pedido;
import com.bcserafim.projetoandroid.helper.callback.ClienteCallback;
import com.bcserafim.projetoandroid.helper.callback.PedidoCallback;
import com.bcserafim.projetoandroid.service.ClienteService;
import com.bcserafim.projetoandroid.service.PedidoService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class PedidoFacade {

    public static void cadastrar (Pedido pedido, final PedidoCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        PedidoService service = retrofit.create(PedidoService.class);
        Call<Pedido> call = service.cadastrarPedido(pedido);
        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

}
