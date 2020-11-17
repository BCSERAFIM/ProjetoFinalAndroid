package com.bcserafim.projetoandroid.helper.callback;

import com.bcserafim.projetoandroid.entity.Cliente;

public interface ClienteCallback {

    public void onSuccess(Cliente cliente);

    public void onFailure(Throwable t);
}
