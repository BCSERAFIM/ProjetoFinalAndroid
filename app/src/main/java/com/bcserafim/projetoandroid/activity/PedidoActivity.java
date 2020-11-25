package com.bcserafim.projetoandroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bcserafim.projetoandroid.BuildConfig;
import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterPedido;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Pedido;
import com.bcserafim.projetoandroid.fragment.ModalItensPedidoFragment;
import com.bcserafim.projetoandroid.service.PedidoService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PedidoActivity extends AppCompatActivity implements View.OnClickListener, AdapterPedido.SelectedPedidoListener {

    private FloatingActionButton fabPedido;

    private List<Pedido> listaPedidos;

    private AdapterPedido adapterPedido;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);


        expandableListView = findViewById(R.id.expandableListPedido);
        fabPedido = findViewById(R.id.fab_pedido);

        fabPedido.setOnClickListener(this);

        if (adapterPedido == null) {
            adapterPedido = new AdapterPedido(this, this);
        }
        expandableListView.setAdapter(adapterPedido);

    }

    @Override
    protected void onResume() {
        carregarDados();
        super.onResume();
    }

    private void carregarDados() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PedidoService service = retrofit.create(PedidoService.class);
        Call<List<Pedido>> call = service.carregarTodosPedidos();
        call.enqueue(new Callback<List<Pedido>>() {
            @Override
            public void onResponse(Call<List<Pedido>> call, Response<List<Pedido>> response) {
                if (response.isSuccessful()) {
                    listaPedidos = response.body();
                    List<Cliente> clientes = new ArrayList<>();
                    for (Pedido pedido : listaPedidos) {
                        boolean clienteAdicioando = false;
                        for (Cliente cliente : clientes) {
                            if (cliente.getId() == pedido.getCliente().getId()) {
                                clienteAdicioando = true;
                                break;
                            }
                        }
                        if (!clienteAdicioando)
                            clientes.add(pedido.getCliente());
                    }
                    adapterPedido.setDataClientes(clientes);
                    adapterPedido.setDataPedidos(listaPedidos);
                    adapterPedido.notifyDataSetChanged();
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Pedido>> call, Throwable t) {
                adapterPedido.setDataClientes(null);
                adapterPedido.setDataPedidos(null);
                adapterPedido.notifyDataSetChanged();
                t.printStackTrace();

            }
        });

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

    @Override
    public void onSelectPedido(Pedido pedido) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PedidoService service = retrofit.create(PedidoService.class);
        Call<Pedido> call = service.carregarTodosItensPedido(pedido.getId());
        call.enqueue(new Callback<Pedido>() {
            @Override
            public void onResponse(Call<Pedido> call, Response<Pedido> response) {
                if (response.isSuccessful()) {
                    Pedido pedido = response.body();
                    FragmentManager fm = getSupportFragmentManager();
                    ModalItensPedidoFragment editNameDialogFragment = ModalItensPedidoFragment.newInstance(pedido, PedidoActivity.this);
                    editNameDialogFragment.show(fm, "fragment_modal_item_pedido");
                } else {
                    System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Pedido> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}