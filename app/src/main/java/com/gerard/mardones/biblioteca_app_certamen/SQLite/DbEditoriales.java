package com.gerard.mardones.biblioteca_app_certamen.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;

import java.util.ArrayList;

public class DbEditoriales extends DbHelper{
    Context context;

    public DbEditoriales(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Editorial obtenerEditorial(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Editorial editorial = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_EDITORIALES+" WHERE id = ?",new String[]{String.valueOf(id)});
        if(cursor.moveToFirst()){
            editorial = new Editorial();
            editorial.setId(cursor.getInt(0));
            editorial.setNombre(cursor.getString(1));
            editorial.setNacionalidad(cursor.getString(2));
        }

        return editorial;
    }

    public ArrayList<Editorial> obtenerEditoriales(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Editorial>editoriales = new ArrayList<>();
        Editorial editorial;

        Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_EDITORIALES,null);
        if(cursor.moveToFirst()){
            do{
                editorial = new Editorial();
                editorial.setId(cursor.getInt(0));
                editorial.setNombre(cursor.getString(1));
                editorial.setNacionalidad(cursor.getString(2));

                editoriales.add(editorial);

            }while(cursor.moveToNext());
            return editoriales;
        }else{
            return null;
        }
    }


    public long insertarEditorial(Editorial editorial){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre",editorial.getNombre());
        values.put("nacionalidad",editorial.getNacionalidad());

        return db.insert(DB_TABLE_EDITORIALES,null,values);
    }

    public void modificarEditorial(Editorial editorial){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("nombre",editorial.getNombre());
        values.put("nacionalidad",editorial.getNacionalidad());

        String[]args = {String.valueOf(editorial.getId())};

        db.update(DB_TABLE_EDITORIALES,values,"id = ?",args);
    }

    public void eliminarEditorial(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        String[]args = {String.valueOf(id)};

        db.delete(DB_TABLE_EDITORIALES,"id = ?",args);
    }
}
