package com.bcserafim.projetoandroid.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterCadastroPedido;
import com.stepstone.stepper.StepperLayout;

public class CadastroPedidoActivity extends AppCompatActivity {
    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new AdapterCadastroPedido(getSupportFragmentManager(), this));
    }
}