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

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterCadastroPedido;
import com.bcserafim.projetoandroid.adapter.AdapterProdutoPedido;
import com.bcserafim.projetoandroid.adapter.AdapterProdutoResumoPedido;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;


public class StepResumoFragment extends Fragment implements Step {

    private TextView nomeClienteResumo;
    private RecyclerView recyclerViewProdutoPedido;


    private AdapterProdutoResumoPedido adapterProdutos;

    public StepResumoFragment() {
        // Required empty public constructor
    }

    public static StepResumoFragment newInstance() {
        StepResumoFragment fragment = new StepResumoFragment();
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
        View view = inflater.inflate(R.layout.fragment_step_resumo, container, false);


        nomeClienteResumo = view.findViewById(R.id.txt_nome_cliente_resumo);
        recyclerViewProdutoPedido = view.findViewById(R.id.rv_resumo_pedido_resumo);

        carregarTelaResumo();
        return view;
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
        if (AdapterCadastroPedido.clienteSelecionado != null) {
            nomeClienteResumo.setText(AdapterCadastroPedido.clienteSelecionado.getNome());
        }
        carregarDadosProdutos();
    }

    @Override
    public void onError(@NonNull VerificationError error) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void setCliente(Cliente cliente) {
        nomeClienteResumo.setText(cliente.getNome());
    }

    private void carregarTelaResumo() {
        adapterProdutos = new AdapterProdutoResumoPedido();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewProdutoPedido.setLayoutManager(layoutManager);
        recyclerViewProdutoPedido.setHasFixedSize(true);
        recyclerViewProdutoPedido.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerViewProdutoPedido.setAdapter(adapterProdutos);
    }

    private void carregarDadosProdutos() {
        adapterProdutos.setData(AdapterCadastroPedido.produtosSelecionados);
        adapterProdutos.notifyDataSetChanged();
    }

}