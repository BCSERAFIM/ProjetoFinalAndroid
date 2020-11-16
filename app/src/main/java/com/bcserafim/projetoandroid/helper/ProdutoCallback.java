package com.bcserafim.projetoandroid.helper;

import com.bcserafim.projetoandroid.entity.Produto;

public interface ProdutoCallback {
    public void onSuccess (Produto produto);
    public void onFailure (Throwable t);
}
