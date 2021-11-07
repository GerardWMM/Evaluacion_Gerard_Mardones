package com.gerard.mardones.biblioteca_app_certamen.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gerard.mardones.biblioteca_app_certamen.Models.Autor;

import java.util.ArrayList;

public class DbAutores extends DbHelper{
    Context context;

    public DbAutores(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Autor obtenerAutor(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getReadableDatabase();
        Autor autor = null;
        Cursor cursor = bd.rawQuery("SELECT * FROM "+DB_TABLE_AUTORES+" WHERE id = ?",
                new String[]{String.valueOf(id)});

        if(cursor.moveToFirst()){
            autor = new Autor();
            autor.setId(cursor.getInt(0));
            autor.setNombres(cursor.getString(1));
            autor.setApellidos(cursor.getString(2));
            autor.setNacionalidad(cursor.getString(3));
        }

        return autor;
    }

    public ArrayList<Autor>obtenerAutores(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getReadableDatabase();

        ArrayList<Autor>arrayAutor = new ArrayList<>();
        Autor autorBD;
        Cursor cursor = bd.rawQuery("SELECT * FROM "+DB_TABLE_AUTORES,null);

        if(cursor.moveToFirst()){
            do{
                autorBD = new Autor();
                autorBD.setId(cursor.getInt(0));
                autorBD.setNombres(cursor.getString(1));
                autorBD.setApellidos(cursor.getString(2));
                autorBD.setNacionalidad(cursor.getString(3));

                arrayAutor.add(autorBD);

            }while(cursor.moveToNext());
            return arrayAutor;

        }else{
            return null;

        }
    }

    public long insertarAutores(Autor autor){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombres",autor.getNombres());
        values.put("apellidos",autor.getApellidos());
        values.put("nacionalidad",autor.getNacionalidad());

        return bd.insert(DB_TABLE_AUTORES,null,values);
    }

    public long actualizarAutor(Autor autor){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombres",autor.getNombres());
        values.put("apellidos",autor.getApellidos());
        values.put("nacionalidad",autor.getNacionalidad());

        String[]args = {String.valueOf(autor.getId())};

        return bd.update(DB_TABLE_AUTORES,values,"id  = ?",args);
    }

    public void eliminarAutor(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getWritableDatabase();

        String[]args = {String.valueOf(id)};

        bd.delete(DB_TABLE_AUTORES,"id = ?",args);
    }
}
