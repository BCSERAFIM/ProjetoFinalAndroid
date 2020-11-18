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

import com.bcserafim.projetoandroid.BuildConfig;
import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterProduto;
import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.helper.callback.ProdutoCallback;
import com.bcserafim.projetoandroid.helper.facade.ProdutoFacade;
import com.bcserafim.projetoandroid.helper.recycler.RecyclerItemClickListener;
import com.bcserafim.projetoandroid.service.ProdutoService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bcserafim.projetoandroid.BuildConfig.BASE_URL;

public class ProdutoActivity extends AppCompatActivity {

    private RecyclerView recyclerViewProduto;
    private List<Produto> listaProdutos = new ArrayList<>();
    private Produto produtoSelecionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        recyclerViewProduto = findViewById(R.id.recyclerViewProduto);


        FloatingActionButton fab = findViewById(R.id.fabProduto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CadastroProdutoActivity.class);
                startActivity(intent);

            }
        });



        // Evento de Click

        recyclerViewProduto.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewProduto,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                //Recuperar produto area edicao
                                produtoSelecionado = listaProdutos.get(position);

                                // Enviar produto para tela Cadastro Produto
                                Intent intent = new Intent(ProdutoActivity.this, CadastroProdutoActivity.class);
                                intent.putExtra("produtoSelecionado",produtoSelecionado);
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                //Recuperar tarefa para deletar
                                produtoSelecionado = listaProdutos.get(position);

                                AlertDialog.Builder dialog = new AlertDialog.Builder(ProdutoActivity.this);

                                // Configurar titulo e mensagem
                                dialog.setTitle("Confirmar Exclusão");
                                dialog.setMessage("Deseja excluir o produto: "+produtoSelecionado.getDescricao()+" ?");

                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        ProdutoFacade.remover(produtoSelecionado.getId(), new ProdutoCallback() {
                                            @Override
                                            public void onSuccess(Produto produto) {
                                                obterProdutos();
                                                Toast.makeText(getApplicationContext(),
                                                        "Sucesso ao excluir produto!",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                            @Override
                                            public void onFailure(Throwable t) {
                                                Toast.makeText(getApplicationContext(),
                                                        "Erro ao excluir produto: " +
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

    public void obterProdutos() {

        //Configurar Adapter
        final AdapterProduto adapterProduto = new AdapterProduto();

        // Configuração Recycleview

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProduto.setLayoutManager(layoutManager);
        recyclerViewProduto.setHasFixedSize(true); // O Recycle view terá um tamanho fixo
        recyclerViewProduto.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));//vai criar uma linha vertical entre os conjuntos os  itens
        recyclerViewProduto.setAdapter(adapterProduto); // Adapter vai receber os dados formatar o layout e utilizar no RecycleView


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        ProdutoService service = retrofit.create(ProdutoService.class);
        Call<List<Produto>> call = service.carregarTodosProdutos();

        call.enqueue(new Callback<List<Produto>>() {
            @Override
            public void onResponse(Call<List<Produto>> call, Response<List<Produto>> response) {
                if (response.isSuccessful()) {

                    listaProdutos = response.body();
                    adapterProduto.update(listaProdutos);
                    adapterProduto.notifyDataSetChanged();


                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                adapterProduto.update(null);
                adapterProduto.notifyDataSetChanged();
                t.printStackTrace();
            }
        });


    }

    @Override
    protected void onStart() {
        obterProdutos();
        super.onStart();
    }
}
