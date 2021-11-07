package com.gerard.mardones.biblioteca_app_certamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.gerard.mardones.biblioteca_app_certamen.Models.Autor;
import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;
import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;
import com.gerard.mardones.biblioteca_app_certamen.Models.Libro;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbAutores;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEditoriales;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEstanterias;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbLibros;

import java.util.ArrayList;

public class FormLibroActivity extends AppCompatActivity {
    EditText txt_tituloLibro, txt_descLibro, txt_fechaPuLibro, txt_copiasLibro, txt_cantPagLibro;
    Spinner spAutorLibro, spEditLibro, spEstantLibro;
    Button btn_registrarLibro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_libro);

        txt_tituloLibro = findViewById(R.id.txt_tituloLibro);
        txt_descLibro = findViewById(R.id.txt_descLibro);
        txt_fechaPuLibro = findViewById(R.id.txt_fechaPuLibro);
        txt_copiasLibro = findViewById(R.id.txt_copiasLibro);
        txt_cantPagLibro = findViewById(R.id.txt_cantPagLibro);
        spAutorLibro = findViewById(R.id.spAutorLibro);
        spEditLibro = findViewById(R.id.spEditorialLibro);
        spEstantLibro = findViewById(R.id.spEstantLibro);
        btn_registrarLibro = findViewById(R.id.btn_registrarLibro);

        cargarEspinners();

        btn_registrarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = txt_tituloLibro.getText().toString();
                String descripcion = txt_descLibro.getText().toString();
                String fecha_publicacion = txt_fechaPuLibro.getText().toString();
                int copias = Integer.parseInt(txt_copiasLibro.getText().toString());
                int cantPagLibro = Integer.parseInt(txt_cantPagLibro.getText().toString());
                Autor autor = (Autor) spAutorLibro.getSelectedItem();
                Editorial editorial = (Editorial) spEditLibro.getSelectedItem();
                Estanteria estanteria = (Estanteria) spEstantLibro.getSelectedItem();

                DbLibros dbLibros = new DbLibros(getApplicationContext());
                Libro libro = new Libro(titulo,descripcion,fecha_publicacion,copias,cantPagLibro,autor,editorial,estanteria);

                long res = dbLibros.insertarLibro(libro);
                if(res > 0){
                    Toast.makeText(getApplicationContext(), "¡Registro exitoso!", Toast.LENGTH_LONG).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(getApplicationContext(), "¡Registro fallido!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }




    public void cargarEspinners(){
        DbAutores dbAutores = new DbAutores(this);
        DbEditoriales dbEditoriales = new DbEditoriales(this);
        DbEstanterias dbEstanterias = new DbEstanterias(this);

        ArrayList<Autor>autores = dbAutores.obtenerAutores();
        ArrayList<Editorial>editoriales = dbEditoriales.obtenerEditoriales();
        ArrayList<Estanteria>estanterias = dbEstanterias.obtenerTodas();

        if(autores != null){
            ArrayAdapter<Autor> adapterAutor = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,autores);

            spAutorLibro.setAdapter(adapterAutor);
        }

        if(editoriales != null){
            ArrayAdapter<Editorial> editorialAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,editoriales);

            spEditLibro.setAdapter(editorialAdapter);
        }

        if(estanterias != null){
            ArrayAdapter<Estanteria>estanteriaAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,estanterias);

            spEstantLibro.setAdapter(estanteriaAdapter);
        }
    }

    public void limpiarCampos(){
        txt_tituloLibro.setText("");
        txt_descLibro.setText("");
        txt_fechaPuLibro.setText("");
        txt_copiasLibro.setText("");
        txt_cantPagLibro.setText("");
        spAutorLibro.setSelection(0);
        spEditLibro.setSelection(0);
        spEstantLibro.setSelection(0);
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