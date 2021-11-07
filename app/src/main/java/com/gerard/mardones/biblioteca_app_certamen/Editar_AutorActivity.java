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

import com.gerard.mardones.biblioteca_app_certamen.Models.Autor;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbAutores;

import java.util.ArrayList;

public class Editar_AutorActivity extends AppCompatActivity {
    EditText txt_nombreAutor_Edit,txt_apellidoAutor_Edit,txt_nacionAutor_Edit;
    Button btn_editarAutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_autor);

        txt_nombreAutor_Edit = findViewById(R.id.txt_nombreAutor_Edit);
        txt_apellidoAutor_Edit = findViewById(R.id.txt_apellidoAutor_edit);
        txt_nacionAutor_Edit = findViewById(R.id.txt_nacionAutor_Edit);
        btn_editarAutor = findViewById(R.id.btn_modificarAutor);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Autor autorEditar = (Autor) bundle.get("autorEditar");

        cargarDatosEnPantalla(autorEditar);

        btn_editarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = txt_nombreAutor_Edit.getText().toString();
                String apellidos = txt_apellidoAutor_Edit.getText().toString();
                String nacionalidad = txt_nacionAutor_Edit.getText().toString();

                actualizarAutor_View(autorEditar,nombres,apellidos,nacionalidad);

            }
        });

    }

    public void cargarDatosEnPantalla(Autor a){
        String nombres = a.getNombres();
        String apellidos = a.getApellidos();
        String nacionalidad = a.getNacionalidad();

        txt_nombreAutor_Edit.setText(nombres);
        txt_apellidoAutor_Edit.setText(apellidos);
        txt_nacionAutor_Edit.setText(nacionalidad);
    }

    public void actualizarAutor_View(Autor autor, String nombre, String apellido, String nacionalidad){
        DbAutores autoresDB = new DbAutores(getApplicationContext());
        ArrayList<Autor>autors = autoresDB.obtenerAutores();
        for(Autor a : autors){
            if(a.equals(autor)){
                a.setNombres(nombre);
                a.setApellidos(apellido);
                a.setNacionalidad(nacionalidad);

                autoresDB.actualizarAutor(a);

                startActivity(new Intent(Editar_AutorActivity.this,Ver_Autores_Activity.class));
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