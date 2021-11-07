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

import com.gerard.mardones.biblioteca_app_certamen.Adapters.EditorialAdapter;
import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;
import com.gerard.mardones.biblioteca_app_certamen.SQLite.DbEditoriales;

import java.util.ArrayList;

public class Ver_Editoriales_Activity extends AppCompatActivity {
    RecyclerView recycler_editoriales;
    ArrayList<Editorial>editoriales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_editoriales);

        recycler_editoriales = findViewById(R.id.recycler_editoriales);
        recycler_editoriales.setLayoutManager(new LinearLayoutManager(this));

        DbEditoriales bdEditoriales = new DbEditoriales(this);
        editoriales = bdEditoriales.obtenerEditoriales();

        EditorialAdapter adapter = new EditorialAdapter(editoriales);
        recycler_editoriales.setAdapter(adapter);

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