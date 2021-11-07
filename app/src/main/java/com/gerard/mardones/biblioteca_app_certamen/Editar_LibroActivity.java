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

import com.gerard.mardones.biblioteca_app_certamen.Models.Autor;
import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;
import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;
import com.gerard.mardones.biblioteca_app_certamen.Models.Libro;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbAutores;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEditoriales;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEstanterias;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbLibros;

import java.util.ArrayList;

public class Editar_LibroActivity extends AppCompatActivity {
    EditText txt_tituloLibro_Edit, txt_descLibro_Edit, txt_fechaLibro_Edit, txt_copiasLibro_Edit, txt_pagsLib_Edit;
    Spinner sp_AutorEdit, sp_EditorialEdit, sp_EstantEdit;
    Button btn_modificarLibro;
    ArrayList<Autor>autores;
    ArrayList<Estanteria>estanterias;
    ArrayList<Editorial>editoriales;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_libro);

        txt_tituloLibro_Edit = findViewById(R.id.txt_tituloLibro_Edit);
        txt_descLibro_Edit = findViewById(R.id.txt_descLibro_Edit);
        txt_fechaLibro_Edit = findViewById(R.id.txt_fechaLibro_Edit);
        txt_copiasLibro_Edit = findViewById(R.id.txt_copiasLibro_Edit);
        txt_pagsLib_Edit = findViewById(R.id.txt_pagsLib_Edit);
        sp_AutorEdit = findViewById(R.id.sp_AutorEdit);
        sp_EditorialEdit = findViewById(R.id.sp_EditorialEdit);
        sp_EstantEdit = findViewById(R.id.sp_EstantEdit);
        btn_modificarLibro = findViewById(R.id.btn_modificarLibro);

        cargarSpinners();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Libro libro = (Libro) bundle.get("libroEditar");


        cargarFormulario(libro);

        btn_modificarLibro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = txt_tituloLibro_Edit.getText().toString();
                String descripcion = txt_descLibro_Edit.getText().toString();
                String fecha_publicacion = txt_fechaLibro_Edit.getText().toString();
                int copias = Integer.parseInt(txt_copiasLibro_Edit.getText().toString());
                int pagsLib = Integer.parseInt(txt_pagsLib_Edit.getText().toString());
                Autor autor = (Autor) sp_AutorEdit.getSelectedItem();
                Editorial editorial = (Editorial) sp_EditorialEdit.getSelectedItem();
                Estanteria estanteria = (Estanteria) sp_EstantEdit.getSelectedItem();

                modificarLibro(libro,titulo,descripcion,fecha_publicacion,copias,pagsLib,autor,editorial,estanteria);
            }
        });

    }

    public void modificarLibro(Libro libro, String titulo, String descripcion,String fecha, int copias, int pagsLibro, Autor autor, Editorial editorial, Estanteria estanteria){
        DbLibros dbLibros = new DbLibros(getApplicationContext());
        ArrayList<Libro>librosModificar = dbLibros.obtenerlibros();
        for(Libro l : librosModificar){
            if(l.equals(libro)){
                l.setTitulo(titulo);
                l.setDescripcion(descripcion);
                l.setFecha_publicacion(fecha);
                l.setCopias(copias);
                l.setCantidad_paginas(pagsLibro);
                l.setAutor(autor);
                l.setEditorial(editorial);
                l.setEstanteria(estanteria);

                dbLibros.actualizarLibro(l);

                startActivity(new Intent(Editar_LibroActivity.this,Ver_Libros_Activity.class));
                break;
            }
        }
    }

    public void cargarFormulario(Libro libro) {
        String titulo = libro.getTitulo();
        String descripcion = libro.getDescripcion();
        String fecha_publicacion = libro.getFecha_publicacion();
        String copias = String.valueOf(libro.getCopias());
        String pagsLib = String.valueOf(libro.getCantidad_paginas());

        int index_autor = autores.indexOf(libro.getAutor());
        int index_editorial = editoriales.indexOf(libro.getEditorial());
        int index_estanteria = estanterias.indexOf(libro.getEstanteria());

        txt_tituloLibro_Edit.setText(titulo);
        txt_descLibro_Edit.setText(descripcion);
        txt_fechaLibro_Edit.setText(fecha_publicacion);
        txt_copiasLibro_Edit.setText(copias);
        txt_pagsLib_Edit.setText(pagsLib);
        sp_AutorEdit.setSelection(index_autor);
        sp_EditorialEdit.setSelection(index_editorial);

    }

    public void cargarSpinners(){
        DbAutores dbAutores = new DbAutores(this);
        DbEstanterias dbEstanterias = new DbEstanterias(this);
        DbEditoriales dbEditoriales = new DbEditoriales(this);

        autores = dbAutores.obtenerAutores();
        estanterias = dbEstanterias.obtenerTodas();
        editoriales = dbEditoriales.obtenerEditoriales();


        if(autores != null){
            ArrayAdapter<Autor> adapterAutor = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,autores);

            sp_AutorEdit.setAdapter(adapterAutor);
        }

        if(editoriales != null){
            ArrayAdapter<Editorial> editorialAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,editoriales);

            sp_EditorialEdit.setAdapter(editorialAdapter);
        }

        if(estanterias != null){
            ArrayAdapter<Estanteria>estanteriaAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,estanterias);

            sp_EstantEdit.setAdapter(estanteriaAdapter);
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