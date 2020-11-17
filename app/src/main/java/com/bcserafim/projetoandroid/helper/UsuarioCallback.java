package com.bcserafim.projetoandroid.helper;

import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Usuario;

public interface UsuarioCallback {

    public void onSuccess(Usuario usuario);

    public void onFailure(Throwable t);
}
