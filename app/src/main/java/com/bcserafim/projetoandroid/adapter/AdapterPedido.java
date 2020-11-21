package com.bcserafim.projetoandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bcserafim.projetoandroid.R;
import com.bcserafim.projetoandroid.activity.PedidoActivity;
import com.bcserafim.projetoandroid.entity.Cliente;
import com.bcserafim.projetoandroid.entity.Pedido;

import java.util.ArrayList;
import java.util.List;

public class AdapterPedido extends BaseExpandableListAdapter {

    private List<Pedido> listaPedidos;
    private List<Cliente> listaClientes;
    private Context context;
    private LayoutInflater infalInflater;
    public SelectedPedidoListener listener;

    public interface SelectedPedidoListener {
        void onSelectPedido(Pedido pedido);
    }

    public AdapterPedido(Context context, SelectedPedidoListener listener) {
        this.context = context;
        this.listener = listener;
    }


    @Override
    public int getGroupCount() {
        if (listaClientes == null)
            return 0;
        return listaClientes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return quantidadePedidoPorCliente(listaClientes.get(groupPosition).getId());
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listaClientes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        final Cliente cliente = (Cliente) getGroup(groupPosition);
        return listaPedidosDoCliente(cliente.getId()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = infalInflater.inflate(R.layout.template_cliente_pedido, null);
        }
        final Cliente cliente = (Cliente) getGroup(groupPosition);

        ((TextView) view.findViewById(R.id.txt_nome_cliente_pedido)).setText(cliente.getNome());
        ((ImageView) view.findViewById(R.id.seta_lista)).setActivated(isExpanded);

        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (infalInflater == null) {
            infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        convertView = infalInflater.inflate(R.layout.template_pedido_cliente, null);

        final Pedido pedido = (Pedido) getChild(groupPosition, childPosition);

        ((TextView) convertView.findViewById(R.id.txt_desc_pedido)).setText(pedido.getId() + " - " + pedido.getData());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSelectPedido(pedido);
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    public void setDataPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public void setDataClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    private int quantidadePedidoPorCliente(int idCliente) {
        int qtd = 0;
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCliente().getId() == idCliente)
                qtd++;
        }
        return qtd;
    }

    private List<Pedido> listaPedidosDoCliente(int idCliente) {
        List<Pedido> pedidos = new ArrayList<>();
        for (Pedido pedido : listaPedidos) {
            if (pedido.getCliente().getId() == idCliente)
                pedidos.add(pedido);
        }
        return pedidos;
    }
}
