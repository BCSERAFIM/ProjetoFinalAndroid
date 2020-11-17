package com.bcserafim.projetoandroid.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterCliente;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.helper.callback.ClienteCallback;
import com.bcserafim.projetoandroid.helper.facade.ClienteFacade;
import com.bcserafim.projetoandroid.helper.recycler.RecyclerItemClickListener;
import com.bcserafim.projetoandroid.service.ClienteService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bcserafim.projetoandroid.activity.MainActivity.BASE_URL;

public class ClienteActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCliente;
    private List<Cliente> listaClientes = new ArrayList<>();
    private Cliente clienteSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);
        recyclerViewCliente = findViewById(R.id.recyclerViewCliente);


        FloatingActionButton fab = findViewById(R.id.fabCliente);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CadastroClienteActivity.class);
                startActivity(intent);

            }
        });

        // Evento de Click


        recyclerViewCliente.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewCliente,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Recuperar cliente area edicao
                                clienteSelecionado = listaClientes.get(position);

                                // Enviar cliente para tela Cadastro Cliente
                                Intent intent = new Intent(ClienteActivity.this, CadastroClienteActivity.class);
                                intent.putExtra("clienteSelecionado", clienteSelecionado);
                                startActivity(intent);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //Recuperar cliente para deletar
                               clienteSelecionado = listaClientes.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(ClienteActivity.this);


                                // Configurar titulo e mensagem
                                dialog.setTitle("Confirmar Exclusão");
                                dialog.setMessage("Deseja excluir o cliente "+clienteSelecionado.getNome()+" ?");

                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ClienteFacade.remover(clienteSelecionado.getId(), new ClienteCallback() {
                                            @Override
                                            public void onSuccess(Cliente cliente) {
                                                obterClientes();
                                                Toast.makeText(getApplicationContext(),
                                                        "Sucesso ao excluir cliente!",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onFailure(Throwable t) {
                                                Toast.makeText(getApplicationContext(),
                                                        "Erro ao excluir cliente: " +
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

    public void obterClientes(){

        //Configurar Adapter

        final AdapterCliente adapterCliente = new AdapterCliente();

        // Configuração Recycleview

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewCliente.setLayoutManager(layoutManager);
        recyclerViewCliente.setHasFixedSize(true); // O Recycle view terá um tamanho fixo
        recyclerViewCliente.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));//vai criar uma linha vertical entre os conjuntos os  itens
        recyclerViewCliente.setAdapter(adapterCliente); // Adapter vai receber os dados formatar o layout e utilizar no RecycleView

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ClienteService service = retrofit.create(ClienteService.class);
        Call<List<Cliente>> call = service.carregarTodosClientes();
        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()) {
                    listaClientes = response.body();
                    adapterCliente.update(listaClientes);
                    adapterCliente.notifyDataSetChanged();

                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                adapterCliente.update(null);
                adapterCliente.notifyDataSetChanged();
                t.printStackTrace();

            }
        });


    }

    @Override
    protected void onStart() {
        obterClientes();
        super.onStart();
    }
}