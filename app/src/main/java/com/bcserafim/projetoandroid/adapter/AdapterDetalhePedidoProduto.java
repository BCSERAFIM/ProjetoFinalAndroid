package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.ItemPedido;
import com.bcserafim.projetoandroid.entity.Pedido;
import com.bcserafim.projetoandroid.entity.Produto;

import java.util.List;

public class AdapterDetalhePedidoProduto extends RecyclerView.Adapter<AdapterDetalhePedidoProduto.MyViewHolder> {

    private Pedido pedido;

    public void setData(Pedido pedido) {
        this.pedido = pedido;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_detalhes_pedido_produto, parent, false);


        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ItemPedido itemPedido = pedido.getItemPedido().get(position);
        holder.txtDescricaoProduto.setText(itemPedido.getProduto().getDescricao());
        holder.txtQuantidade.setText(String.valueOf(itemPedido.getQuantidade()));
    }

    @Override
    public int getItemCount() {
        if (this.pedido.getItemPedido() == null)
            return 0;
        return this.pedido.getItemPedido().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtDescricaoProduto, txtQuantidade;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDescricaoProduto = itemView.findViewById(R.id.txt_descricao_produto_detalhes);
            txtQuantidade = itemView.findViewById(R.id.text_qtd_produto_detalhes);
        }
    }

}
