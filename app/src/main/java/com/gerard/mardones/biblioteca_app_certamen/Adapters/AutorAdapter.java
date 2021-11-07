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

import com.gerard.mardones.biblioteca_app_certamen.Editar_AutorActivity;
import com.gerard.mardones.biblioteca_app_certamen.Editar_EditorialActivity;
import com.gerard.mardones.biblioteca_app_certamen.Models.Autor;
import com.gerard.mardones.biblioteca_app_certamen.R;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbAutores;

import java.util.ArrayList;

public class AutorAdapter extends RecyclerView.Adapter<AutorAdapter.ViewHolder> {
    ArrayList<Autor>autores;

    public AutorAdapter(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    public void alertaEliminacion(Autor a, Context context){
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle("¡ADVERTENCIA!");
        alerta.setMessage("¿Estas seguro de querer borrar a "+a.getNombres()+" "+a.getApellidos()+"?");
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DbAutores autorBD = new DbAutores(context);
                autorBD.eliminarAutor(a.getId());

                autores.remove(a);

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
                R.layout.item_autores,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cargarInformacion(autores.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Autor autor = autores.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), Editar_AutorActivity.class);
                intent.putExtra("autorEditar",autor);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Autor autor = autores.get(position);
                alertaEliminacion(autor,holder.itemView.getContext());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return autores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_nombreAutor_View, txt_apellidoAutor_View, txt_nacionAutor_View;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nombreAutor_View = itemView.findViewById(R.id.txt_nombreAutor_View);
            txt_apellidoAutor_View = itemView.findViewById(R.id.txt_apellidoAutor_View);
            txt_nacionAutor_View = itemView.findViewById(R.id.txt_nacioAutor_View);
        }

        public void cargarInformacion(Autor a){
            String nombres = a.getNombres();
            String apellidos = a.getApellidos();
            String nacionalidad = a.getNacionalidad();

            txt_nombreAutor_View.setText("Nombres: "+nombres);
            txt_apellidoAutor_View.setText("Apellidos: "+apellidos);
            txt_nacionAutor_View.setText("Nacionalidad: "+nacionalidad);
        }
    }
}
