package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Usuario;

import java.util.List;

public class AdapterUsuario extends RecyclerView.Adapter<AdapterUsuario.MyViewHolder> {

    private List<Usuario> listaUsuarios;

    public AdapterUsuario() {

    }

    public void update(List<Usuario> lista) {
        this.listaUsuarios = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_usuario, parent, false);



        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);

        holder.login.setText(String.format("%d",usuario.getLogin()));
        holder.senha.setText(usuario.getSenha());

    }

    @Override
    public int getItemCount() {
        if (this.listaUsuarios == null)
            return 0;
        return this.listaUsuarios.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView senha, login;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            login = itemView.findViewById(R.id.textViewLoginUsuario);
            senha = itemView.findViewById(R.id.textViewSenhaUsuario);
        }
    }

}
