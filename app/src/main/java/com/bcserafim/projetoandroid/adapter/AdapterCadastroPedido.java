package com.bcserafim.projetoandroid.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.fragment.StepClienteFragment;
import com.bcserafim.projetoandroid.fragment.StepProdutoFragment;
import com.bcserafim.projetoandroid.fragment.StepResumoFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

import java.util.List;

public class AdapterCadastroPedido extends AbstractFragmentStepAdapter implements AdapterClientePedido.SelectedClienteListener {

    private static final String CURRENT_STEP_POSITION_KEY = "";
    private StepClienteFragment stepClienteFragment;
    private StepProdutoFragment stepProdutoFragment;
    private StepResumoFragment stepResumoFragment;
    public static Cliente clienteSelecionado;
    public static List<Produto> produtosSelecionados;

    public AdapterCadastroPedido(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        Bundle b = new Bundle();
        b.putInt(CURRENT_STEP_POSITION_KEY, position);

        switch (position) {
            case 0:
                stepClienteFragment = StepClienteFragment.newInstance(this);
                stepClienteFragment.setArguments(b);
                return stepClienteFragment;
            case 1:
                stepProdutoFragment = StepProdutoFragment.newInstance();
                stepProdutoFragment.setArguments(b);
                return stepProdutoFragment;
            case 2:
                stepResumoFragment = StepResumoFragment.newInstance();
                stepResumoFragment.setArguments(b);
                return stepResumoFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @NonNull
    @Override
    public StepViewModel getViewModel(@IntRange(from = 0) int position) {
//        if (step != null)
//            step.setView(position);

        switch (position) {
            case 0:
                return new StepViewModel.Builder(context)
                        .setTitle("Cliente") //can be a CharSequence instead
                        .create();
            case 1:
                return new StepViewModel.Builder(context)
                        .setTitle("Produtos") //can be a CharSequence instead
                        .create();
            default:
                return new StepViewModel.Builder(context)
                        .setTitle("Finalizar Pedido") //can be a CharSequence instead
                        .create();
        }
    }

    @Override
    public void onSelectCliente(Cliente cliente) {
        clienteSelecionado = cliente;
    }
}