package com.gerard.mardones.biblioteca_app_certamen.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.gerard.mardones.biblioteca_app_certamen.Editar_EstanteriaActivity;
import com.gerard.mardones.biblioteca_app_certamen.Editar_LibroActivity;
import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;
import com.gerard.mardones.biblioteca_app_certamen.Models.Libro;
import com.gerard.mardones.biblioteca_app_certamen.R;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEstanterias;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbLibros;

import java.util.ArrayList;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolder> {
    ArrayList<Libro>libros;

    public LibroAdapter(ArrayList<Libro> libros) {
        this.libros = libros;
    }

    public void eliminarLibro(Libro libro, Context context){
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle("¡ADVERTENCIA!");
        alerta.setMessage("¿Estas seguro de querer borrar el libro "
                +libro.getTitulo()+"?");
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DbLibros dbLibros = new DbLibros(context.getApplicationContext());
                dbLibros.eliminarLibro(libro.getId());

                libros.remove(libro);

                notifyDataSetChanged();
            }
        });
        alerta.setNegativeButton("Cancelar",null);
        alerta.create();
        alerta.show();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_libros,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cargarInformacion(libros.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Libro libro = libros.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), Editar_LibroActivity.class);
                intent.putExtra("libroEditar",libro);
                holder.itemView.getContext().startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Libro libro = libros.get(position);
                eliminarLibro(libro,holder.itemView.getContext());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return libros.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_tituloLibro_View, txt_autorLibro_View, txt_editorial_fecha_View, txt_copiasLibro_View,
                txt_cantPag_View, txt_estantLibro_View, txt_descLibro_View ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_tituloLibro_View = itemView.findViewById(R.id.txt_tituloLibro_View);
            txt_autorLibro_View = itemView.findViewById(R.id.txt_autorLibro_View);
            txt_editorial_fecha_View = itemView.findViewById(R.id.txt_editorial_fecha_View);
            txt_copiasLibro_View = itemView.findViewById(R.id.txt_copiasLibro_View);
            txt_cantPag_View = itemView.findViewById(R.id.txt_cantPag_View);
            txt_estantLibro_View = itemView.findViewById(R.id.txt_estantLibro_View);
            txt_descLibro_View = itemView.findViewById(R.id.txt_descLibro_View);
        }

        public void cargarInformacion(Libro libro){
            String titulo = libro.getTitulo();
            String nombre_autor = libro.getAutor().getNombres();
            String apellido_autor = libro.getAutor().getApellidos();

            String editorial = libro.getEditorial().getNombre();
            String fecha_publicacion = libro.getFecha_publicacion();

            String cantidad_pag = String.valueOf(libro.getCantidad_paginas());

            String copias = String.valueOf(libro.getCopias());

            String letra_estant = libro.getEstanteria().getLetra();
            String num_estant = String.valueOf(libro.getEstanteria().getNumero());
            String col_estant = libro.getEstanteria().getColor();

            String desc = libro.getDescripcion();

            txt_tituloLibro_View.setText(titulo);
            txt_autorLibro_View.setText(nombre_autor+" "+apellido_autor);
            txt_editorial_fecha_View.setText(editorial+" ("+fecha_publicacion+")");
            txt_copiasLibro_View.setText(copias);
            txt_cantPag_View.setText("Paginas: "+cantidad_pag);
            txt_estantLibro_View.setText("Estanteria: "+letra_estant+" "+num_estant+" "+col_estant);
            txt_descLibro_View.setText(desc);


        }
    }
}
