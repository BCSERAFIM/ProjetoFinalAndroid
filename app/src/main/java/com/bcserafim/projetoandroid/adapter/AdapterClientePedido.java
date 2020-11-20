package com.bcserafim.projetoandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Pedido;

import java.util.List;

public class AdapterClientePedido extends RecyclerView.Adapter<AdapterClientePedido.MyViewHolder> {

    private List<Cliente> listaCleintes;
    private RadioButton mSelectedRB;

    public SelectedClienteListener listener;

    public AdapterClientePedido(AdapterClientePedido.SelectedClienteListener listener) {
        this.listener = listener;
    }

    public interface SelectedClienteListener {
        void onSelectCliente(Cliente cliente);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_cliente_pedido, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Cliente cliente = listaCleintes.get(position);
        holder.nome.setText(cliente.getNome());

        holder.radioBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mSelectedRB != null && mSelectedRB.isChecked()) {
                    mSelectedRB.setChecked(false);
                }
                mSelectedRB = (RadioButton) v;
                mSelectedRB.setChecked(true);
                listener.onSelectCliente(cliente);
            }
        });

        if (heClienteSelecionado(cliente)) {
            holder.radioBtn.setChecked(true);
            if (mSelectedRB != null && holder.radioBtn != mSelectedRB) {
                mSelectedRB = holder.radioBtn;
            }
        } else {
            holder.radioBtn.setChecked(false);
        }
    }

    public boolean heClienteSelecionado(Cliente cliente) {
        return (AdapterCadastroPedido.clienteSelecionado != null && AdapterCadastroPedido.clienteSelecionado.getId().equals(cliente.getId()));
    }

    @Override
    public int getItemCount() {
        if (listaCleintes == null)
            return 0;
        return listaCleintes.size();
    }

    public void setData(List<Cliente> lista) {
        this.listaCleintes = lista;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        RadioButton radioBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.txt_nome_cliente_pedido);
            radioBtn = itemView.findViewById(R.id.radio_button_cliente);
        }


    }

}
