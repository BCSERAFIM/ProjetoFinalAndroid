package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Produto;

import java.util.List;

public class AdapterProdutoPedido extends RecyclerView.Adapter<AdapterProdutoPedido.MyViewHolder> {

    private List<Produto> listaProdutos;

    public AdapterProdutoPedido(List<Produto> lista) {
        this.listaProdutos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_produto_pedido, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Produto produto = listaProdutos.get(position);
        holder.nome.setText(produto.getDescricao());

        holder.btnMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produto.setQtd(produto.getQtd() + 1);
                holder.qtd.setText(String.valueOf(produto.getQtd()));
            }
        });

        holder.btnMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                produto.setQtd(produto.getQtd() - 1);
                holder.qtd.setText(String.valueOf(produto.getQtd()));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (listaProdutos == null)
            return 0;
        return listaProdutos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome, qtd;
        Button btnMais, btnMenos;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txt_nome_produto_pedido);
            qtd = itemView.findViewById(R.id.text_qtd_produto);
            btnMais = itemView.findViewById(R.id.btn_Mais);
            btnMenos = itemView.findViewById(R.id.btn_menos);
        }
    }

}
