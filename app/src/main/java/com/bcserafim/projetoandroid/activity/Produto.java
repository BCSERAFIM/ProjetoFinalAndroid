package com.bcserafim.projetoandroid.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterProduto;
import com.bcserafim.projetoandroid.helper.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Produto extends AppCompatActivity {

    private RecyclerView recyclerViewProduto;
    private List<Produto> listaProdutos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        recyclerViewProduto = findViewById(R.id.recyclerViewProduto);


        FloatingActionButton fab = findViewById(R.id.fabProduto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               Intent intent = new Intent(getApplicationContext(), CadastroProduto.class );
               startActivity(intent);

            }
        });

        // Listagem de filmes
        this.obterProdutos();


        //Configurar Adapter
        AdapterProduto adapterProduto = new AdapterProduto();


        // Configuração Recycleview

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewProduto.setLayoutManager(layoutManager);
        recyclerViewProduto.setHasFixedSize(true); // O Recycle view terá um tamanho fixo
        recyclerViewProduto.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));//vai criar uma linha vertical entre os conjuntos os  itens
        recyclerViewProduto.setAdapter( adapterProduto ); // Adapter vai receber os dados formatar o layout e utilizar no RecycleView

        // Evento de Click

        recyclerViewProduto.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewProduto,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Log.i("Clique","onItemClick");
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Item pressionado: ",
                                        Toast.LENGTH_SHORT
                                ).show();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {
                                Log.i("Clique","onLongItemClick");

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

        public void obterProdutos(){



        }

}