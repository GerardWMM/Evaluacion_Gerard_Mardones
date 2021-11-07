package com.gerard.mardones.biblioteca_app_certamen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.gerard.mardones.biblioteca_app_certamen.Adapters.LibroAdapter;
import com.gerard.mardones.biblioteca_app_certamen.Models.Autor;
import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;
import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;
import com.gerard.mardones.biblioteca_app_certamen.Models.Libro;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbAutores;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEditoriales;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEstanterias;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbLibros;

import java.util.ArrayList;

public class Ver_Libros_Activity extends AppCompatActivity {
    RecyclerView recyler_libros;
    Spinner sp_filtrarAutor, sp_filtrarEditorial,sp_filtrarLibro;
    ArrayList<Libro> libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_libros);

        sp_filtrarAutor = findViewById(R.id.sp_filtrar_Autor);
        sp_filtrarEditorial = findViewById(R.id.sp_filtrar_Edit);
        sp_filtrarLibro = findViewById(R.id.sp_filtrar_libro);


        recyler_libros = findViewById(R.id.recycler_libros);
        cargarSpinners();

        sp_filtrarAutor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Autor autor = (Autor) sp_filtrarAutor.getSelectedItem();
                if(autor.getId() == 0){
                    cargarLibros_ByAutor(null);
                }else{
                    cargarLibros_ByAutor(autor);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_filtrarEditorial.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Editorial editorial = (Editorial) sp_filtrarEditorial.getSelectedItem();
                if(editorial.getId() == 0){
                    cargarLibros_ByEditorial(null);
                }else{
                    cargarLibros_ByEditorial(editorial);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_filtrarLibro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Libro libro = (Libro) sp_filtrarLibro.getSelectedItem();
                if(libro.getId() == 0){
                    cargarLibros_ByNombre(null);
                }else{
                    cargarLibros_ByNombre(libro);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void cargarLibros_ByAutor(Autor autor){
        recyler_libros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DbLibros dbLibros = new DbLibros(getApplicationContext());

        LibroAdapter adapter = null;
        if(autor == null){
            adapter = new LibroAdapter(dbLibros.obtenerlibros());
        }else{
            adapter = new LibroAdapter(dbLibros.obtenerlibros_autor(autor.getId()));
        }

        recyler_libros.setAdapter(adapter);
    }

    public void cargarLibros_ByEditorial(Editorial editorial){
        recyler_libros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DbLibros dbLibros = new DbLibros(getApplicationContext());

        LibroAdapter adapter = null;
        if(editorial == null){
            adapter = new LibroAdapter(dbLibros.obtenerlibros());
        }else{
            adapter = new LibroAdapter(dbLibros.obtenerlibros_editorial(editorial.getId()));
        }

        recyler_libros.setAdapter(adapter);
    }

    public void cargarLibros_ByNombre(Libro libro){
        recyler_libros.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        DbLibros dbLibros = new DbLibros(getApplicationContext());

        LibroAdapter adapter = null;
        if(libro == null){
            adapter = new LibroAdapter(dbLibros.obtenerlibros());
        }else{
            adapter = new LibroAdapter(dbLibros.obtenerlibros(libro.getTitulo()));
        }
    }

    public void cargarSpinners(){
        DbAutores dbAutores = new DbAutores(this);
        DbEditoriales dbEditoriales = new DbEditoriales(this);
        DbLibros dbLibros = new DbLibros(this);

        ArrayList<Autor>autores = dbAutores.obtenerAutores();
        autores.add(new Autor(0,"Todos los","registros"));

        ArrayList<Editorial>editoriales = dbEditoriales.obtenerEditoriales();
        editoriales.add(new Editorial(0,"Todos los registros"));

        ArrayList<Libro>libros = dbLibros.obtenerlibros();
        libros.add(new Libro(0,"Todos los registros"));

        if(autores != null){
            ArrayAdapter<Autor> adapterAutor = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,autores);

            sp_filtrarAutor.setAdapter(adapterAutor);
        }

        if(editoriales != null){
            ArrayAdapter<Editorial> editorialAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,editoriales);

            sp_filtrarEditorial.setAdapter(editorialAdapter);
        }

        if(libros != null){
            ArrayAdapter<Libro>librosAdapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item,libros);

            sp_filtrarLibro.setAdapter(librosAdapter);
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