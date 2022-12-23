package com.example.consumowebserviceparametroheader.adactadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.consumowebserviceparametroheader.R;
import com.example.consumowebserviceparametroheader.models.Clientes;

import java.util.List;

public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ClienteViewHolder> {
    private Context Ctx;
    private List<Clientes> lstClientes;

    public ClienteAdapter(Context mCtx, List<Clientes> clientes) {
        this.lstClientes = clientes;
        Ctx=mCtx;
    }

    @Override
    public ClienteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(Ctx);
        View view = inflater.inflate(R.layout.item_usuario, null);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClienteViewHolder holder, int position) {

        Clientes cliente = lstClientes.get(position);
        holder.txtViewCedula.setText(cliente.getCedula());
        holder.textViewName.setText(cliente.getNombres());
        holder.textViewCorreo.setText(cliente.getCorreo());
        holder.textViewTelefono.setText(cliente.getTelefono());
        holder.txtViewFnacimiento.setText(cliente.getFecnaci());
        Glide.with(Ctx).load(cliente.getUrlavatar()).into(holder.imageView);
    }

    @Override
    public int getItemCount()
    {
        return lstClientes.size();
    }

    class ClienteViewHolder extends RecyclerView.ViewHolder {
        TextView txtViewCedula, textViewName, textViewCorreo,textViewTelefono,txtViewFnacimiento;
        ImageView imageView;

        public ClienteViewHolder(View itemView) {
            super(itemView);
            txtViewCedula=itemView.findViewById(R.id.txtCedula);
            textViewName= itemView.findViewById(R.id.txtName);
            textViewCorreo = itemView.findViewById(R.id.txtMail);
            textViewTelefono = itemView.findViewById(R.id.txtTelefono);
            txtViewFnacimiento = itemView.findViewById(R.id.txtFechaNaci);
            imageView = itemView.findViewById(R.id.imgAvatar);
        }
    }
}
