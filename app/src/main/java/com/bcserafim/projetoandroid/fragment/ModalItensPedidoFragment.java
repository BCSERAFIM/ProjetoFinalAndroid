package com.bcserafim.projetoandroid.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.BuildConfig;
import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterCadastroPedido;
import com.bcserafim.projetoandroid.adapter.AdapterClientePedido;
import com.bcserafim.projetoandroid.adapter.AdapterDetalhePedidoProduto;
import com.bcserafim.projetoandroid.adapter.AdapterProduto;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Pedido;
import com.bcserafim.projetoandroid.service.ClienteService;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ModalItensPedidoFragment extends DialogFragment {
    private static final String TAG = "modalItensPedido";
    private Pedido pedido;
    private Context context;
    private RecyclerView recyclerViewDetalhePedido;
    private AdapterDetalhePedidoProduto adapterProduto;

    public ModalItensPedidoFragment() {
    }

    public static ModalItensPedidoFragment newInstance(Pedido pedido, Context context) {
        ModalItensPedidoFragment fragment = new ModalItensPedidoFragment();
        Bundle args = new Bundle();
        fragment.pedido = pedido;
        fragment.context = context;
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
        View view = inflater.inflate(R.layout.fragment_modal_item_pedido, container, false);


        recyclerViewDetalhePedido = view.findViewById(R.id.rv_produto_pedido);
//        getDialog().getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//        getDialog().getWindow().setBackgroundDrawableResource(R.mipmap.background_clean);

        if (adapterProduto == null) {
            adapterProduto = new AdapterDetalhePedidoProduto();

            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this.context.getApplicationContext());
            recyclerViewDetalhePedido.setLayoutManager(layoutManager);
            recyclerViewDetalhePedido.setHasFixedSize(true);
            recyclerViewDetalhePedido.addItemDecoration(new DividerItemDecoration(this.context.getApplicationContext(), LinearLayout.VERTICAL));
            recyclerViewDetalhePedido.setAdapter(adapterProduto);
        }

        view.findViewById(R.id.btn_voltar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        configurarTela();
        super.onResume();
    }

    private void configurarTela() {
        adapterProduto.setData(pedido);
        adapterProduto.notifyDataSetChanged();
    }

}