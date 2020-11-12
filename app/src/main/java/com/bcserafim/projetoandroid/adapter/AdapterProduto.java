package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Produto;

import java.util.List;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private List<Produto> listaProdutos;

    public AdapterProduto(List<Produto> lista){

        this.listaProdutos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       View itemLista = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.adapter_produto,parent,false);



        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Produto produto = listaProdutos.get(position);
        holder.id.setText(produto.getId());
        holder.descricao.setText(produto.getDescricao());

    }

    @Override
    public int getItemCount() {

        return this.listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

            TextView id, descricao;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.textViewIdProduto);
            descricao = itemView.findViewById(R.id.textViewDescricaoProduto);
        }
    }

}
