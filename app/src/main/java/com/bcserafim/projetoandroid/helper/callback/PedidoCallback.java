package com.bcserafim.projetoandroid.helper.callback;

import com.bcserafim.projetoandroid.entity.ItemPedido;
import com.bcserafim.projetoandroid.entity.Pedido;

import java.util.List;

public interface PedidoCallback {

    public void onSuccess(Pedido pedido);

    public void onFailure(Throwable t);
}
