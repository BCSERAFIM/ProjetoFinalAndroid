package com.bcserafim.projetoandroid.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.BuildConfig;
import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterClientePedido;
import com.bcserafim.projetoandroid.adapter.AdapterProdutoPedido;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.service.ClienteService;
import com.bcserafim.projetoandroid.service.ProdutoService;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class StepProdutoFragment extends Fragment implements Step {

    private RecyclerView recyclerViewProdutoPedido;
    private List<Produto> listaProdutos;

    private AdapterProdutoPedido adapterProdutos;

    public StepProdutoFragment() {
        // Required empty public constructor
    }

    public static StepProdutoFragment newInstance() {
        StepProdutoFragment fragment = new StepProdutoFragment();
        Bundle args = new Bundle();
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
        View view = inflater.inflate(R.layout.fragment_step_produto, container, false);

        recyclerViewProdutoPedido = view.findViewById(R.id.rv_produto_pedido);

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
        carregarTelaProdutos();
        carregarDadosProdutos();
        super.onResume();
    }

    private void carregarTelaProdutos() {
        adapterProdutos = new AdapterProdutoPedido();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewProdutoPedido.setLayoutManager(layoutManager);
        recyclerViewProdutoPedido.setHasFixedSize(true);
        recyclerViewProdutoPedido.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerViewProdutoPedido.setAdapter(adapterProdutos);
    }


    private void carregarDadosProdutos() {

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
                    adapterProdutos.setData(listaProdutos);
                    adapterProdutos.notifyDataSetChanged();
                } else {
                    System.out.println(response.errorBody());
                    adapterProdutos.setData(null);
                    adapterProdutos.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Produto>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}