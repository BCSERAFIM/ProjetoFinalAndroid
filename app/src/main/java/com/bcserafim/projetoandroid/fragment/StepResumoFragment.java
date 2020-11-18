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


public class StepResumoFragment extends Fragment implements Step {


    private TextView nomeClienteResumo;

    public StepResumoFragment() {
        // Required empty public constructor
    }

    public static StepResumoFragment newInstance(Cliente cliente) {
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
        carregarTelaResumo();
        carregarDadosResumo();
        super.onResume();
    }

    private void carregarTelaResumo() {

    }

    private void carregarDadosResumo() {
        nomeClienteResumo.setText("Nome do Cliente");
    }
}