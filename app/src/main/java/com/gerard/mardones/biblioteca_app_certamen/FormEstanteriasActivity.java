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

import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEstanterias;

public class FormEstanteriasActivity extends AppCompatActivity {
    EditText txt_letraEstanteria, txt_numeroEstanteria, txt_colorEstanteria;
    Button btn_registrarEstanteria;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_estanterias);

        txt_letraEstanteria = findViewById(R.id.txt_letraEstanteria);
        txt_numeroEstanteria = findViewById(R.id.txt_numeroEstanteria);
        txt_colorEstanteria = findViewById(R.id.txt_colorEstanteria);
        btn_registrarEstanteria = findViewById(R.id.btn_registrarEstanteria);

        btn_registrarEstanteria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String letra = txt_letraEstanteria.getText().toString();
                int numero = Integer.parseInt(txt_numeroEstanteria.getText().toString());
                String color = txt_colorEstanteria.getText().toString();

                DbEstanterias db = new DbEstanterias(FormEstanteriasActivity.this);
                Estanteria estanteria = new Estanteria(letra,numero,color);

                long id = db.insertarEstanterias(estanteria);

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
        txt_letraEstanteria.setText("");
        txt_numeroEstanteria.setText("");
        txt_colorEstanteria.setText("");
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