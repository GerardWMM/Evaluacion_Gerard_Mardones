package com.gerard.mardones.biblioteca_app_certamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;
import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEstanterias;

import java.util.ArrayList;

public class Editar_EstanteriaActivity extends AppCompatActivity {
    EditText txt_letraEstant_Edit, txt_numEstant_Edit,txt_colorEstant_Edit;
    Button btn_modificarEstanteria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_estanteria);

        txt_letraEstant_Edit = findViewById(R.id.txt_letraEstant_Edit);
        txt_numEstant_Edit = findViewById(R.id.txt_numEstant_Edit);
        txt_colorEstant_Edit = findViewById(R.id.txt_colorEstant_Edit);
        btn_modificarEstanteria = findViewById(R.id.btn_modificarEstant);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Estanteria estanteria = (Estanteria) bundle.get("estanteriaEditar");

        cargarDatosEnPantalla(estanteria);

        btn_modificarEstanteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String letra = txt_letraEstant_Edit.getText().toString();
                int numero = Integer.parseInt(txt_numEstant_Edit.getText().toString());
                String color = txt_colorEstant_Edit.getText().toString();

                modificarEstanteria(estanteria,letra,numero,color);
            }
        });


    }



    public void cargarDatosEnPantalla(Estanteria es){
        String letra = es.getLetra();
        String num = String.valueOf(es.getNumero());
        String color = es.getColor();

        txt_letraEstant_Edit.setText(letra);
        txt_numEstant_Edit.setText(num);
        txt_colorEstant_Edit.setText(color);
    }

    public void modificarEstanteria(Estanteria es,String let, int num, String col){
        DbEstanterias estanteriasDB = new DbEstanterias(this);
        ArrayList<Estanteria> estanterias = estanteriasDB.obtenerTodas();

        for(Estanteria e : estanterias){
            if(es.equals(e)){
                e.setLetra(let);
                e.setNumero(num);
                e.setColor(col);

                estanteriasDB.actualizarEstanterias(e);
                startActivity(new Intent(Editar_EstanteriaActivity.this,Ver_Estanterias_Activity.class));
                break;
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_datos,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_crear:
                Intent intent1 = new Intent(getApplicationContext(),OpcionCrear_Activity.class);
                startActivity(intent1);
                return true;
            case R.id.menu_listar:
                Intent intent2 = new Intent(getApplicationContext(),OpcionListar_Activity.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}