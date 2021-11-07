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

public class OpcionListar_Activity extends AppCompatActivity {
    Button btn_verAutores, btn_verEstanterias,btn_verEditoriales, btn_verLibros;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opcion_listar);

        btn_verAutores = findViewById(R.id.btn_VerAutores);
        btn_verEstanterias = findViewById(R.id.btn_VerEstanterias);
        btn_verEditoriales = findViewById(R.id.btn_VerEditoriales);
        btn_verLibros = findViewById(R.id.btn_VerLibros);

        btn_verAutores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Ver_Autores_Activity.class);
                startActivity(intent);
            }
        });

        btn_verEstanterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Ver_Estanterias_Activity.class);
                startActivity(intent);
            }
        });

        btn_verEditoriales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Ver_Editoriales_Activity.class);
                startActivity(intent);
            }
        });

        btn_verLibros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Ver_Libros_Activity.class);
                startActivity(intent);
            }
        });
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