package com.bcserafim.projetoandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterUsuario;
import com.bcserafim.projetoandroid.entity.Usuario;
import com.bcserafim.projetoandroid.helper.RecyclerItemClickListener;
import com.bcserafim.projetoandroid.service.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UsuarioActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsuario;
    private List<Usuario> listaUsuarios = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        recyclerViewUsuario = findViewById(R.id.recyclerViewUsuario);


        FloatingActionButton fab = findViewById(R.id.fabUsuario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CadastroUsuario.class);
                startActivity(intent);

            }
        });

        // Listagem de Usuarios
        // this.obterUsuarios();


        // Evento de Click

        recyclerViewUsuario.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewUsuario,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Log.i("Clique", "onItemClick");
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Item pressionado: ",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i("Clique", "onLongItemClick");

                                Toast.makeText(
                                        getApplicationContext(),
                                        "Click Longo",
                                        Toast.LENGTH_SHORT
                                ).show();

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )

        );

    }

    public void obterUsuarios() {

        //Configurar Adapter
        final AdapterUsuario adapterUsuario = new AdapterUsuario();

        // Configuração Recycleview

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewUsuario.setLayoutManager(layoutManager);
        recyclerViewUsuario.setHasFixedSize(true); // O Recycle view terá um tamanho fixo
        recyclerViewUsuario.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));//vai criar uma linha vertical entre os conjuntos os  itens
        recyclerViewUsuario.setAdapter(adapterUsuario); // Adapter vai receber os dados formatar o layout e utilizar no RecycleView


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.10.0.38:8080/WebServiceAndroid/webresources/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        UsuarioService service = retrofit.create(UsuarioService.class);
        Call<List<Usuario>> call = service.carregarUsuarios();

        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if (response.isSuccessful()) {

                    listaUsuarios = response.body();
                    adapterUsuario.update(listaUsuarios);
                    adapterUsuario.notifyDataSetChanged();


                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                adapterUsuario.update(null);
                adapterUsuario.notifyDataSetChanged();
                t.printStackTrace();
            }
        });


    }

    @Override
    protected void onStart() {
        obterUsuarios();
        super.onStart();
    }
}