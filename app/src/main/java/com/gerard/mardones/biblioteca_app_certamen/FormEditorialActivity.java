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

import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEditoriales;

public class FormEditorialActivity extends AppCompatActivity {
    EditText txt_nombreEditorial,txt_nacionalidadEditorial;
    Button btn_registrarEditorial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_editorial);

        txt_nombreEditorial = findViewById(R.id.txt_nombreEditorial);
        txt_nacionalidadEditorial = findViewById(R.id.txt_nacionalidadEditorial);
        btn_registrarEditorial = findViewById(R.id.btn_registrarEditorial);

        btn_registrarEditorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txt_nombreEditorial.getText().toString();
                String nacionalidad = txt_nacionalidadEditorial.getText().toString();

                DbEditoriales db = new DbEditoriales(FormEditorialActivity.this);
                Editorial editorial = new Editorial(nombre,nacionalidad);

                long id = db.insertarEditorial(editorial);

                if(id > 0){
                    Toast.makeText(getApplicationContext(), "¡Registro exitoso!",
                            Toast.LENGTH_LONG).show();
                    limpiarCampos();
                }else{
                    Toast.makeText(getApplicationContext(), "¡Registro fallido!",
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void limpiarCampos(){
        txt_nombreEditorial.setText("");
        txt_nacionalidadEditorial.setText("");
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