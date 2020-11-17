package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Pedido;

import java.util.List;

public class AdapterClientePedido extends RecyclerView.Adapter<AdapterClientePedido.MyViewHolder> {

    private List<Cliente> listaCleintes;

    public AdapterClientePedido(List<Cliente> lista) {
        this.listaCleintes = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_cliente_pedido, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cliente cliente = listaCleintes.get(position);
        holder.nome.setText(cliente.getNome());
    }

    @Override
    public int getItemCount() {
        if (listaCleintes == null)
            return 0;
        return listaCleintes.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txt_nome_cliente_pedido);
        }
    }

}
