package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Pedido;
import com.bcserafim.projetoandroid.entity.Produto;

import java.util.List;

public class AdapterPedido extends RecyclerView.Adapter<AdapterPedido.MyViewHolder> {

    private List<Pedido> listaPedidos;

    public AdapterPedido(List<Pedido> lista) {
        this.listaPedidos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pedido, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pedido produto = listaPedidos.get(position);
        holder.id.setText("Pedido #" + produto.getId());
    }

    @Override
    public int getItemCount() {
        if (listaPedidos == null)
            return 0;
        return listaPedidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txt_id_pedido);
        }
    }

}
