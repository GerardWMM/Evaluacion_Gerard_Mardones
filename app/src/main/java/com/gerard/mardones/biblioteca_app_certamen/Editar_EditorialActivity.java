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
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEditoriales;

import java.util.ArrayList;

public class Editar_EditorialActivity extends AppCompatActivity {
    EditText txt_nameEditorial_Edit,txt_nationEditorial_Edit;
    Button btn_modificarEditorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_editorial);

        txt_nameEditorial_Edit = findViewById(R.id.txt_nameEditorial_Edit);
        txt_nationEditorial_Edit = findViewById(R.id.txt_nationEditorial_Edit);
        btn_modificarEditorial = findViewById(R.id.btn_modificarEditorial);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        Editorial editorial = (Editorial) bundle.get("editorialEditar");

        cargarDatosEnPantalla(editorial);

        btn_modificarEditorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txt_nameEditorial_Edit.getText().toString();
                String nacionalidad = txt_nationEditorial_Edit.getText().toString();

                modificarEditorial(editorial,nombre,nacionalidad);
            }
        });
    }

    public void cargarDatosEnPantalla(Editorial e){
        String nombre = e.getNombre();
        String nacionalidad = e.getNacionalidad();

        txt_nameEditorial_Edit.setText(nombre);
        txt_nationEditorial_Edit.setText(nacionalidad);
    }

    public void modificarEditorial(Editorial ed,String nombre, String nacionalidad){
        DbEditoriales editorialDB = new DbEditoriales(getApplicationContext());
        ArrayList<Editorial>editoriales = editorialDB.obtenerEditoriales();
        for(Editorial e : editoriales){
            if(ed.equals(e)){
                e.setNombre(nombre);
                e.setNacionalidad(nacionalidad);

                editorialDB.modificarEditorial(e);

                startActivity(new Intent(Editar_EditorialActivity.this,Ver_Editoriales_Activity.class));
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