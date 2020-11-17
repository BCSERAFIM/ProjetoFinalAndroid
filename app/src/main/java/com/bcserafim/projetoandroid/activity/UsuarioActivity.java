package com.bcserafim.projetoandroid.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterUsuario;
import com.bcserafim.projetoandroid.entity.Usuario;
import com.bcserafim.projetoandroid.helper.recycler.RecyclerItemClickListener;
import com.bcserafim.projetoandroid.helper.callback.UsuarioCallback;
import com.bcserafim.projetoandroid.helper.facade.UsuarioFacade;
import com.bcserafim.projetoandroid.service.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bcserafim.projetoandroid.activity.MainActivity.BASE_URL;

public class UsuarioActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsuario;
    private List<Usuario> listaUsuarios = new ArrayList<>();
    private Usuario usuarioSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        recyclerViewUsuario = findViewById(R.id.recyclerViewUsuario);


        FloatingActionButton fab = findViewById(R.id.fabUsuario);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CadastroUsuarioActivity.class);
                startActivity(intent);

            }
        });

        // Listagem de Usuarios
         this.obterUsuarios();


        // Evento de Click

        recyclerViewUsuario.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewUsuario,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Recuperar usuario area edicao
                                usuarioSelecionado = listaUsuarios.get(position);

                                // Enviar usuario para tela Cadastro Usuario
                                Intent intent = new Intent(UsuarioActivity.this, CadastroUsuarioActivity.class);
                                intent.putExtra("usuarioSelecionado",usuarioSelecionado);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //Recuperar tarefa para deletar
                                usuarioSelecionado = listaUsuarios.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(UsuarioActivity.this);

                                // Configurar titulo e mensagem
                                dialog.setTitle("Confirmar Exclusão");
                                dialog.setMessage("Deseja excluir o usuario: "+usuarioSelecionado.getLogin()+" ?");

                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        UsuarioFacade.remover(usuarioSelecionado.getLogin(), new UsuarioCallback() {
                                            @Override
                                            public void onSuccess(Usuario usuario) {
                                                obterUsuarios();
                                                Toast.makeText(getApplicationContext(),
                                                        "Sucesso ao excluir usuario  !",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onFailure(Throwable t) {
                                                Toast.makeText(getApplicationContext(),
                                                        "Erro ao excluir usuario: " +
                                                                t.getMessage(),
                                                        Toast.LENGTH_LONG).show();

                                            }
                                        });
                                    }
                                });

                                dialog.setNegativeButton("Não",null);

                                //Exibir dialog
                                dialog.create();
                                dialog.show();

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
                .baseUrl(BASE_URL)
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