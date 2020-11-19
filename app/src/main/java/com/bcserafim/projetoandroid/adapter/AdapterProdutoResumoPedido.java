package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Produto;

import java.util.List;

public class AdapterProdutoResumoPedido extends RecyclerView.Adapter<AdapterProdutoResumoPedido.MyViewHolder> {

    private List<Produto> listaProdutos;

    public AdapterProdutoResumoPedido() {
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_produto_resumo_pedido, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Produto produto = listaProdutos.get(position);
        holder.nome.setText(produto.getDescricao());
    }

    @Override
    public int getItemCount() {
        if (listaProdutos == null)
            return 0;
        return listaProdutos.size();
    }

    public void setData(List<Produto> lista) {
        this.listaProdutos = lista;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txt_nome_produto_pedido_resumo);
        }
    }

}
