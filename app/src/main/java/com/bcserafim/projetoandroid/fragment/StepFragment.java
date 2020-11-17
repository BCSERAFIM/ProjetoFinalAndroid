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

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterClientePedido;
import com.bcserafim.projetoandroid.adapter.AdapterProdutoPedido;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Produto;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;


public class StepFragment extends Fragment implements Step {

    private RecyclerView recyclerViewClientePedido, recyclerViewProdutoPedido;
    private List<Cliente> listaClientes;
    private List<Produto> listaProduto;

    public LinearLayout viewCliente, viewItemPedido, viewResumoPedido;
    int position = 0;

    public StepFragment() {
        // Required empty public constructor
    }

    public static StepFragment newInstance(int position) {
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.position = position;
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
        View view = inflater.inflate(R.layout.fragment_step_sample, container, false);

        recyclerViewClientePedido = view.findViewById(R.id.rv_cliente_pedido);
        recyclerViewProdutoPedido = view.findViewById(R.id.rv_produto_pedido);

        viewCliente = view.findViewById(R.id.view_clientes);
        viewItemPedido = view.findViewById(R.id.view_item_pedido);
        viewResumoPedido = view.findViewById(R.id.view_resumo_pedido);

        setView(position);
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
        carregarDadosClientes();
        carregarDadosProdutos();

        carregarTelaClientes();
        carregarTelaProdutos();

        super.onResume();
    }

    public void setView(int position) {
        switch (position) {
            case 0:
                viewItemPedido.setVisibility(View.GONE);
                viewResumoPedido.setVisibility(View.GONE);
                viewCliente.setVisibility(View.VISIBLE);
                break;
            case 1:
                viewCliente.setVisibility(View.GONE);
                viewResumoPedido.setVisibility(View.GONE);
                viewItemPedido.setVisibility(View.VISIBLE);
                break;
            default:
                viewCliente.setVisibility(View.GONE);
                viewItemPedido.setVisibility(View.GONE);
                viewResumoPedido.setVisibility(View.VISIBLE);
                break;

        }
    }

    private void carregarTelaClientes() {
        AdapterClientePedido adapterProduto = new AdapterClientePedido(listaClientes);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewClientePedido.setLayoutManager(layoutManager);
        recyclerViewClientePedido.setHasFixedSize(true);
        recyclerViewClientePedido.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerViewClientePedido.setAdapter(adapterProduto);
    }

    private void carregarTelaProdutos() {
        AdapterProdutoPedido adapterProduto = new AdapterProdutoPedido(listaProduto);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewProdutoPedido.setLayoutManager(layoutManager);
        recyclerViewProdutoPedido.setHasFixedSize(true);
        recyclerViewProdutoPedido.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        recyclerViewProdutoPedido.setAdapter(adapterProduto);
    }

    private void carregarDadosClientes() {
        //TODO: Buscar dados da API
        listaClientes = new ArrayList<>();
        Cliente cliente1 = new Cliente();
        cliente1.setNome("Maria");
        listaClientes.add(cliente1);
        Cliente cliente2 = new Cliente();
        cliente2.setNome("jose");
        listaClientes.add(cliente2);
        Cliente cliente3 = new Cliente();
        cliente3.setNome("pedro");
        listaClientes.add(cliente3);
    }

    private void carregarDadosProdutos() {
        //TODO: Buscar dados da API
        listaProduto = new ArrayList<>();
        Produto produto1 = new Produto();
        produto1.setDescricao("Teclado");
        listaProduto.add(produto1);
        Produto produto2 = new Produto();
        produto2.setDescricao("Mouse");
        listaProduto.add(produto2);
        Produto produto3 = new Produto();
        produto3.setDescricao("Monitor");
        listaProduto.add(produto3);
    }
}