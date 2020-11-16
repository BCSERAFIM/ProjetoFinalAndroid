package com.bcserafim.projetoandroid.helper;

import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.service.ProdutoService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bcserafim.projetoandroid.activity.MainActivity.BASE_URL;

public class ProdutoFacade {

    public static void cadastrar (Produto produto, final ProdutoCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ProdutoService service = retrofit.create(ProdutoService.class);
        Call<Produto> call = service.cadastrarProduto(produto);
        call.enqueue(new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {

                callback.onFailure(t);
            }
        });

    }

    public static void alterar (Produto produto, final ProdutoCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ProdutoService service = retrofit.create(ProdutoService.class);
        Call<Produto> call = service.alterar(produto);
        call.enqueue(new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

    public static void remover (Integer id, final ProdutoCallback callback){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build();

        ProdutoService service = retrofit.create(ProdutoService.class);
        Call<Produto> call = service.remover(id);
        call.enqueue(new Callback<Produto>() {
            @Override
            public void onResponse(Call<Produto> call, Response<Produto> response) {
                if(response.isSuccessful()){
                    callback.onSuccess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Produto> call, Throwable t) {
                callback.onFailure(t);
            }
        });

    }

}
