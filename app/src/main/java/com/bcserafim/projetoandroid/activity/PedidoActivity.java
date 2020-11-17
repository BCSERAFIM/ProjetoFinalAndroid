package com.bcserafim.projetoandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterCadastroPedido;
import com.bcserafim.projetoandroid.adapter.AdapterPedido;
import com.bcserafim.projetoandroid.entity.Pedido;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stepstone.stepper.StepperLayout;

import java.util.ArrayList;
import java.util.List;

public class PedidoActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerViewPedido;
    private FloatingActionButton fabPedido;

    private List<Pedido> listaPedidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);

        recyclerViewPedido = findViewById(R.id.rv_pedido);
        fabPedido = findViewById(R.id.fab_pedido);

        fabPedido.setOnClickListener(this);

        carregarDados();
        carregarTela();
    }

    private void carregarTela() {
        AdapterPedido adapterProduto = new AdapterPedido(listaPedidos);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPedido.setLayoutManager(layoutManager);
        recyclerViewPedido.setHasFixedSize(true);
        recyclerViewPedido.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewPedido.setAdapter(adapterProduto);
    }

    private void carregarDados() {
        //TODO: Buscar dados da API
        listaPedidos = new ArrayList<>();
        Pedido pedido1 = new Pedido();
        pedido1.setId(1);
        listaPedidos.add(pedido1);
        Pedido pedido2 = new Pedido();
        pedido2.setId(2);
        listaPedidos.add(pedido2);
        Pedido pedido3 = new Pedido();
        pedido3.setId(3);
        listaPedidos.add(pedido3);
        Pedido pedido4 = new Pedido();
        pedido3.setId(4);
        listaPedidos.add(pedido4);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_pedido:
                Intent intent = new Intent(getApplicationContext(), CadastroPedidoActivity.class);
                startActivity(intent);
                break;
        }
    }
}