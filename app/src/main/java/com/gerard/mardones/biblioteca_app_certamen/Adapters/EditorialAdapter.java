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

import com.gerard.mardones.biblioteca_app_certamen.Editar_EditorialActivity;
import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;
import com.gerard.mardones.biblioteca_app_certamen.R;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEditoriales;

import java.util.ArrayList;

public class EditorialAdapter extends RecyclerView.Adapter<EditorialAdapter.ViewHolder> {
    ArrayList<Editorial>editorials;

    public EditorialAdapter(ArrayList<Editorial>editorials){
        this.editorials = editorials;
    }

    public void eliminarEditorial(Editorial e, Context context){
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle("¡ADVERTENCIA!");
        alerta.setMessage("¿Estas seguro de borrar la editorial "+e.getNombre()+"?");
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DbEditoriales editorialesDB = new DbEditoriales(context);
                editorialesDB.eliminarEditorial(e.getId());

                editorials.remove(e);

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
                R.layout.item_editoriales,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cargarInfo(editorials.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Editorial editorial = editorials.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), Editar_EditorialActivity.class);
                intent.putExtra("editorialEditar",editorial);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Editorial editorial = editorials.get(position);
                eliminarEditorial(editorial,holder.itemView.getContext());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return editorials.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_nombreEdit_View, txt_nacionEdit_View;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_nombreEdit_View = itemView.findViewById(R.id.txt_nombreEdit_View);
            txt_nacionEdit_View = itemView.findViewById(R.id.txt_nacionEdit_View);
        }

        public void cargarInfo(Editorial ed){
            String nombre = ed.getNombre();
            String nacionalidad = ed.getNacionalidad();

            txt_nombreEdit_View.setText("Nombre de la editorial: "+nombre);
            txt_nacionEdit_View.setText("Nacionalidad de la editorial: "+nacionalidad);
        }
    }
}
