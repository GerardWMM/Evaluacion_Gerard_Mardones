package com.gerard.mardones.biblioteca_app_certamen.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;

import java.util.ArrayList;

public class DbEstanterias extends DbHelper{
    Context context;

    public DbEstanterias(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public Estanteria obtenerEstanteria(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        Estanteria estanteria = null;
        Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_ESTANTERIAS+" WHERE id = ?",
                new String[]{String.valueOf(id)});

        if(cursor.moveToFirst()){
            estanteria = new Estanteria();
            estanteria.setId(cursor.getInt(0));
            estanteria.setLetra(cursor.getString(1));
            estanteria.setNumero(cursor.getInt(2));
            estanteria.setColor(cursor.getString(3));

        }

        return estanteria;
    }

    public ArrayList<Estanteria>obtenerTodas(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Estanteria>estanterias = new ArrayList<>();
        Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_ESTANTERIAS,null);

        if(cursor.moveToFirst()){
            do{
                Estanteria estant = new Estanteria();
                estant.setId(cursor.getInt(0));
                estant.setLetra(cursor.getString(1));
                estant.setNumero(cursor.getInt(2));
                estant.setColor(cursor.getString(3));

                estanterias.add(estant);

            }while(cursor.moveToNext());
            return estanterias;

        }else{
            return null;
        }
    }

    public long insertarEstanterias(Estanteria estanteria){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("letra",estanteria.getLetra());
        values.put("numero",estanteria.getNumero());
        values.put("color",estanteria.getColor());

        return db.insert(DB_TABLE_ESTANTERIAS,null,values);
    }

    public void actualizarEstanterias(Estanteria estanteria){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("letra",estanteria.getLetra());
        values.put("numero",estanteria.getNumero());
        values.put("color",estanteria.getColor());

        String[]args = {String.valueOf(estanteria.getId())};

        db.update(DB_TABLE_ESTANTERIAS,values,"id = ?",args);
    }

    public void eliminarEstanterias(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        String[]args = {String.valueOf(id)};

        db.delete(DB_TABLE_ESTANTERIAS,"id = ?",args);
    }
}
