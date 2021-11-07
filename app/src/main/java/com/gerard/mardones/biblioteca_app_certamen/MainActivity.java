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

public class MainActivity extends AppCompatActivity {
    Button btn_verDatos, btn_registrarDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_verDatos = findViewById(R.id.btn_verDatos);
        btn_registrarDatos = findViewById(R.id.btn_registrarDatos);

        btn_verDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OpcionListar_Activity.class);
                startActivity(intent);
            }
        });

        btn_registrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),OpcionCrear_Activity.class);
                startActivity(intent);
            }
        });
    }

}