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
import android.widget.Toast;

import com.gerard.mardones.biblioteca_app_certamen.Models.Autor;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbAutores;

public class FormAutorActivity extends AppCompatActivity {
    EditText txt_nombresAutor,txt_apellidosAutor,txt_nacionalidadAutor;
    Button btn_RegistrarAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_autor);

        txt_nombresAutor = findViewById(R.id.txt_nombresAutor);
        txt_apellidosAutor = findViewById(R.id.txt_apellidosAutor);
        txt_nacionalidadAutor = findViewById(R.id.txt_nacionalidadAutor);
        btn_RegistrarAutor = findViewById(R.id.btn_RegistrarAutor);

        btn_RegistrarAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = txt_nombresAutor.getText().toString();
                String apellidos = txt_apellidosAutor.getText().toString();
                String nacionalidad = txt_nacionalidadAutor.getText().toString();

                Autor autor = new Autor(nombres,apellidos,nacionalidad);
                DbAutores db = new DbAutores(FormAutorActivity.this);

                long id = db.insertarAutores(autor);
                if(id > 0){
                    Toast.makeText(FormAutorActivity.this, "¡Registro exitoso!",
                            Toast.LENGTH_LONG).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(FormAutorActivity.this, "¡Registro fallido!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void limpiarCampos(){
        txt_nombresAutor.setText("");
        txt_apellidosAutor.setText("");
        txt_nacionalidadAutor.setText("");
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