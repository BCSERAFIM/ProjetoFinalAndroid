package com.bcserafim.projetoandroid.adapter;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.bcserafim.projetoandroid.fragment.StepFragment;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.adapter.AbstractFragmentStepAdapter;
import com.stepstone.stepper.viewmodel.StepViewModel;

public class AdapterCadastroPedido extends AbstractFragmentStepAdapter {

    private static final String CURRENT_STEP_POSITION_KEY = "";
    private StepFragment step;

    public AdapterCadastroPedido(FragmentManager fm, Context context) {
        super(fm, context);
    }

    @Override
    public Step createStep(int position) {
        step = StepFragment.newInstance(position);
        Bundle b = new Bundle();
        b.putInt(CURRENT_STEP_POSITION_KEY, position);
        step.setArguments(b);

//        switch (position) {
//            case 0:
//                step.viewItemPedido.setVisibility(View.VISIBLE);
//                step.viewItemPedido.setVisibility(View.GONE);
//                break;
//            case 1:
//                step.viewCliente.setVisibility(View.VISIBLE);
//                step.viewItemPedido.setVisibility(View.GONE);
//                break;
//            default:
//                step.viewCliente.setVisibility(View.GONE);
//                step.viewItemPedido.setVisibility(View.GONE);
//                break;
//
//        }

        return step;
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

}