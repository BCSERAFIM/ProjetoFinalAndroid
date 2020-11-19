package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Cliente;

import java.util.List;

public class AdapterCliente extends RecyclerView.Adapter<AdapterCliente.MyViewHolder> {

    private List<Cliente> listaClientes;
    private int quantidadePedidos;


    public AdapterCliente() {

    }

    public void update(List<Cliente> lista) {
        this.listaClientes = lista;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_cliente, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cliente cliente = listaClientes.get(position);
        holder.id.setText(String.valueOf(cliente.getId()));
        holder.cpf.setText(cliente.getCpf());
        holder.nome.setText(cliente.getNome());
        holder.sobrenome.setText(cliente.getSobreNome());



    }

    @Override
    public int getItemCount() {
        if (this.listaClientes == null)
            return 0;
        return this.listaClientes.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView id, cpf, nome, sobrenome;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.textViewIdCliente);
            cpf = itemView.findViewById(R.id.textViewCpfCliente);
            nome = itemView.findViewById(R.id.textViewNomeCliente);
            sobrenome = itemView.findViewById(R.id.textViewSobrenomeCliente);


        }
    }

}
