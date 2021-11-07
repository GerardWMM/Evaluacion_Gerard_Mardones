package com.gerard.mardones.biblioteca_app_certamen.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    protected static final String DB_NAME = "Biblioteca_ST";
    protected static final int DB_VERSION = 7;

    protected Context context;

    protected static final String DB_TABLE_AUTORES = "Autores";
    protected static final String DB_TABLE_ESTANTERIAS = "Estanterias";
    protected static final String DB_TABLE_EDITORIALES = "Editoriales";
    protected static final String DB_TABLE_LIBROS = "Libros";

    public DbHelper(@Nullable Context context) {
        super(context,DB_NAME,null,DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_AUTORES+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombres TEXT NOT NULL,"+
                "apellidos TEXT NOT NULL,"+
                "nacionalidad TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_ESTANTERIAS+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "letra TEXT NOT NULL,"+
                "numero INTEGER NOT NULL,"+
                "color TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_EDITORIALES+"(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "nombre TEXT NOT NULL," +
                "nacionalidad TEXT NOT NULL)");

        sqLiteDatabase.execSQL("CREATE TABLE "+DB_TABLE_LIBROS+"("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titulo TEXT NOT NULL," +
                "descripcion TEXT NOT NULL," +
                "fecha_publicacion TEXT NOT NULL," +
                "copias INTEGET NOT NULL," +
                "cantidad_paginas INTEGER NOT NULL," +
                "autor INTEGER NOT NULL," +
                "editorial INTEGER NOT NULL," +
                "estante INTEGER NOT NULL," +
                "FOREIGN KEY (autor) REFERENCES DB_TABLE_AUTORES(id)," +
                "FOREIGN KEY (editorial) REFERENCES DB_TABLE_EDITORIALES(id)," +
                "FOREIGN KEY (estante) REFERENCES DB_TABLE_ESTANTERIAS(id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_AUTORES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_ESTANTERIAS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_EDITORIALES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DB_TABLE_LIBROS);
        onCreate(sqLiteDatabase);
    }
}
