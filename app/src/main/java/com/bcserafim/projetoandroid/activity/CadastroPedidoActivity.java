package com.bcserafim.projetoandroid.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.adapter.AdapterCadastroPedido;
import com.bcserafim.projetoandroid.entity.ItemPedido;
import com.bcserafim.projetoandroid.entity.Pedido;
import com.bcserafim.projetoandroid.entity.Produto;
import com.bcserafim.projetoandroid.helper.callback.PedidoCallback;
import com.bcserafim.projetoandroid.helper.callback.ProdutoCallback;
import com.bcserafim.projetoandroid.helper.facade.PedidoFacade;
import com.bcserafim.projetoandroid.helper.facade.ProdutoFacade;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.ArrayList;
import java.util.List;

public class CadastroPedidoActivity extends AppCompatActivity implements StepperLayout.StepperListener {
    private StepperLayout mStepperLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_pedido);

        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        mStepperLayout.setAdapter(new AdapterCadastroPedido(getSupportFragmentManager(), this));
        mStepperLayout.setListener(this);
    }

    @Override
    public void onCompleted(View completeButton) {
        cadastrar();
    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int newStepPosition) {
    }

    @Override
    public void onReturn() {

    }

    private Pedido getPedido() {
        Pedido pedido = new Pedido();

        pedido.setCliente(AdapterCadastroPedido.clienteSelecionado);
        pedido.setItemPedido(getItemsPedido());

        return pedido;
    }

    private List<ItemPedido> getItemsPedido() {
        List<ItemPedido> lista = new ArrayList<>();

        for (Produto produto : AdapterCadastroPedido.produtosSelecionados) {
            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(produto.getQtd());
            lista.add(itemPedido);
        }

        return lista;
    }

    private void cadastrar() {
        PedidoFacade.cadastrar(getPedido(), new PedidoCallback() {
            @Override
            public void onSuccess(Pedido pedido) {
                Toast.makeText(CadastroPedidoActivity.this,
                        "Sucesso ao cadastrar Pedido",
                        Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Throwable t) {
                Toast.makeText(CadastroPedidoActivity.this,
                        "Erro ao cadastrar Pedido :" +
                                t.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}