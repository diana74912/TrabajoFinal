package com.example.trabajofinal.Adaptadores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.trabajofinal.Modelo.Trabajador;
import com.example.trabajofinal.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrabajadorAdaptadores extends RecyclerView.Adapter<TrabajadorAdaptadores.ViewHolder> {


    private int resource;
    private ArrayList<Trabajador>trabajadorArrayList;

    public TrabajadorAdaptadores(ArrayList<Trabajador>trabajadorArrayList, int resource){
        this.trabajadorArrayList=trabajadorArrayList;
        this.resource=resource;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        final Trabajador trabajador=trabajadorArrayList.get(position);
        holder.titulo.setText(trabajador.getNombre());
        holder.Oficio.setText(trabajador.getOficio());
        holder.telefono.setText(trabajador.getTelefono());
    }


    @Override
    public int getItemCount() {
        return trabajadorArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView titulo;
        public TextView Oficio;
        public TextView telefono;
        public View view;
        public CircleImageView circleImageView;

        public ViewHolder(View view){
            super(view);
            this.view=view;
            titulo=(TextView)view.findViewById(R.id.txtNombre);
            Oficio=(TextView)view.findViewById(R.id.txtOficio);
            telefono=(TextView)view.findViewById(R.id.txtStatus);
            titulo= titulo.findViewById(R.id.txtNombre);
            Oficio=Oficio.findViewById(R.id.txtOficio);
            telefono=telefono.findViewById(R.id.txtStatus);
        }
    }


}
