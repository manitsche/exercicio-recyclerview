package com.manitsche.exercciorecyclerview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.manitsche.exercciorecyclerview.R;
import com.manitsche.exercciorecyclerview.model.Compromisso;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Compromisso> listaCompromissos;

    public Adapter(List<Compromisso> lista) {
        this.listaCompromissos = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_lista, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Compromisso compromisso = listaCompromissos.get(position);
        holder.titulo.setText(compromisso.getTitulo());
        holder.dataHorario.setText(compromisso.getData() + " às " + compromisso.getHorario());
    }

    @Override
    public int getItemCount() {
        return listaCompromissos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titulo;
        TextView dataHorario;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitulo);
            dataHorario = itemView.findViewById(R.id.textDataHorario);
        }
    }
}