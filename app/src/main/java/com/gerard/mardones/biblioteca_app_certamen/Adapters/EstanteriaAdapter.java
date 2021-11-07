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
import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;
import com.gerard.mardones.biblioteca_app_certamen.R;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEstanterias;

import java.util.ArrayList;

public class EstanteriaAdapter extends RecyclerView.Adapter<EstanteriaAdapter.ViewHolder> {
    ArrayList<Estanteria> estanterias;

    public EstanteriaAdapter(ArrayList<Estanteria>estanterias){
        this.estanterias = estanterias;
    }

    public void eliminarEstanteria(Estanteria e, Context context){
        AlertDialog.Builder alerta = new AlertDialog.Builder(context);
        alerta.setTitle("¡ADVERTENCIA!");
        alerta.setMessage("¿Estas seguro de querer borrar la estanteria "
                +e.getLetra()+" | "+e.getNumero()+" | "+e.getColor()+"?");
        alerta.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DbEstanterias estanteriasDB = new DbEstanterias(context);
                estanteriasDB.eliminarEstanterias(e.getId());

                estanterias.remove(e);

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
                R.layout.item_estanterias,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cargarInfo(estanterias.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Estanteria estanteria = estanterias.get(position);
                Intent intent = new Intent(holder.itemView.getContext(), Editar_EstanteriaActivity.class);
                intent.putExtra("estanteriaEditar",estanteria);
                holder.itemView.getContext().startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Estanteria estanteria = estanterias.get(position);
                eliminarEstanteria(estanteria,holder.itemView.getContext());
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return estanterias.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_letraEstant_View, txt_numEstant_View, txt_colorEstant_View;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_letraEstant_View = itemView.findViewById(R.id.txt_letraEst_View);
            txt_numEstant_View = itemView.findViewById(R.id.txt_numEst_View);
            txt_colorEstant_View = itemView.findViewById(R.id.txt_colorEst_View);
        }

        public void cargarInfo(Estanteria e){
            String letra = e.getLetra();
            String numero = String.valueOf(e.getNumero());
            String color = e.getColor();

            txt_letraEstant_View.setText("Letra del estante: "+letra);
            txt_numEstant_View.setText("Numero del estante: "+numero);
            txt_colorEstant_View.setText("Color del estante: "+color);
        }
    }
}
