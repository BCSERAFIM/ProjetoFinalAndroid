package com.bcserafim.projetoandroid.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bcserafim.projetoandroid.BuildConfig;
import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterClientePedido;
import com.bcserafim.projetoandroid.adapter.AdapterProdutoPedido;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.service.ClienteService;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StepClienteFragment extends Fragment implements Step {

    private RecyclerView recyclerViewClientePedido;
    private List<Cliente> listaClientes;
    private AdapterClientePedido.SelectedClienteListener listener;

    private AdapterClientePedido adapterClientes;

    public StepClienteFragment() {

    }

    public static StepClienteFragment newInstance(AdapterClientePedido.SelectedClienteListener listener) {
        StepClienteFragment fragment = new StepClienteFragment();
        Bundle args = new Bundle();
        fragment.listener = listener;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_step_cliente, container, false);

        recyclerViewClientePedido = view.findViewById(R.id.rv_cliente_pedido);

        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onResume() {
        carregarTelaClientes();
        carregarDadosClientes();
        super.onResume();
    }


    private void carregarTelaClientes() {
        adapterClientes = new AdapterClientePedido(listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewClientePedido.setLayoutManager(layoutManager);
        recyclerViewClientePedido.setHasFixedSize(true);
        recyclerViewClientePedido.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerViewClientePedido.setAdapter(adapterClientes);
    }


    private void carregarDadosClientes() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ClienteService service = retrofit.create(ClienteService.class);
        Call<List<Cliente>> call = service.carregarTodosClientes();

        call.enqueue(new Callback<List<Cliente>>() {
            @Override
            public void onResponse(Call<List<Cliente>> call, Response<List<Cliente>> response) {
                if (response.isSuccessful()) {
                    listaClientes = response.body();
                    adapterClientes.setData(listaClientes);
                    adapterClientes.notifyDataSetChanged();
                } else {
                    System.out.println(response.errorBody());
                    adapterClientes.setData(null);
                    adapterClientes.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Cliente>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}