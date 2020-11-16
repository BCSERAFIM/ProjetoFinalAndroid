package com.bcserafim.projetoandroid.helper;

import com.bcserafim.projetoandroid.entity.Cliente;

public interface ClienteCallback {

    public void onSuccess (Cliente cliente);
    public void onFailure (Throwable t);
}
