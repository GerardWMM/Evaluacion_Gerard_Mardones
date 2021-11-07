package com.gerard.mardones.biblioteca_app_certamen.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.gerard.mardones.biblioteca_app_certamen.Models.Autor;
import com.gerard.mardones.biblioteca_app_certamen.Models.Editorial;
import com.gerard.mardones.biblioteca_app_certamen.Models.Estanteria;
import com.gerard.mardones.biblioteca_app_certamen.Models.Libro;

import java.util.ArrayList;

public class DbLibros extends DbHelper{
    Context context;

    public DbLibros(@Nullable Context context) {
        super(context);
        this.context = context;
    }


    public ArrayList<Libro>obtenerlibros(){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Libro>libros = new ArrayList<>();
        Libro libro;

        Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_LIBROS,null);

        DbAutores dbAutores = new DbAutores(context);
        DbEditoriales dbEditoriales = new DbEditoriales(context);
        DbEstanterias dbEstanterias = new DbEstanterias(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutores.obtenerAutor(cursor.getInt(6));
                Editorial editorial = dbEditoriales.obtenerEditorial(cursor.getInt(7));
                Estanteria estanteria = dbEstanterias.obtenerEstanteria(cursor.getInt(8));

                libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha_publicacion(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setCantidad_paginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstanteria(estanteria);

                libros.add(libro);

            }while(cursor.moveToNext());
            return libros;

        }else{
            return  libros;
        }
    }

    public ArrayList<Libro>obtenerlibros(String titulo){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Libro>libros = new ArrayList<>();
        Libro libro;

        Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_LIBROS+" WHERE titulo = ?",new String[]{titulo});

        DbAutores dbAutores = new DbAutores(context);
        DbEditoriales dbEditoriales = new DbEditoriales(context);
        DbEstanterias dbEstanterias = new DbEstanterias(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutores.obtenerAutor(cursor.getInt(6));
                Editorial editorial = dbEditoriales.obtenerEditorial(cursor.getInt(7));
                Estanteria estanteria = dbEstanterias.obtenerEstanteria(cursor.getInt(8));

                libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha_publicacion(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setCantidad_paginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstanteria(estanteria);

                libros.add(libro);

            }while(cursor.moveToNext());
            return libros;

        }else{
            return  libros;
        }
    }


    public ArrayList<Libro>obtenerlibros_autor(int id_autor){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Libro>libros = new ArrayList<>();
        Libro libro;

        Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_LIBROS+" WHERE autor = ?",
                new String[]{String.valueOf(id_autor)});

        DbAutores dbAutores = new DbAutores(context);
        DbEditoriales dbEditoriales = new DbEditoriales(context);
        DbEstanterias dbEstanterias = new DbEstanterias(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutores.obtenerAutor(cursor.getInt(6));
                Editorial editorial = dbEditoriales.obtenerEditorial(cursor.getInt(7));
                Estanteria estanteria = dbEstanterias.obtenerEstanteria(cursor.getInt(8));

                libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha_publicacion(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setCantidad_paginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstanteria(estanteria);

                libros.add(libro);

            }while(cursor.moveToNext());
            return libros;

        }else{
            return  libros;
        }
    }

    public ArrayList<Libro>obtenerlibros_editorial(int id_editorial){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        ArrayList<Libro>libros = new ArrayList<>();
        Libro libro;

        Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE_LIBROS+" WHERE editorial = ?",
                new String[]{String.valueOf(id_editorial)});

        DbAutores dbAutores = new DbAutores(context);
        DbEditoriales dbEditoriales = new DbEditoriales(context);
        DbEstanterias dbEstanterias = new DbEstanterias(context);

        if(cursor.moveToFirst()){
            do{
                Autor autor = dbAutores.obtenerAutor(cursor.getInt(6));
                Editorial editorial = dbEditoriales.obtenerEditorial(cursor.getInt(7));
                Estanteria estanteria = dbEstanterias.obtenerEstanteria(cursor.getInt(8));

                libro = new Libro();
                libro.setId(cursor.getInt(0));
                libro.setTitulo(cursor.getString(1));
                libro.setDescripcion(cursor.getString(2));
                libro.setFecha_publicacion(cursor.getString(3));
                libro.setCopias(cursor.getInt(4));
                libro.setCantidad_paginas(cursor.getInt(5));
                libro.setAutor(autor);
                libro.setEditorial(editorial);
                libro.setEstanteria(estanteria);

                libros.add(libro);

            }while(cursor.moveToNext());
            return libros;

        }else{
            return  libros;
        }
    }


    public long insertarLibro(Libro libro){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo",libro.getTitulo());
        values.put("descripcion",libro.getDescripcion());
        values.put("fecha_publicacion",libro.getFecha_publicacion());
        values.put("copias",libro.getCopias());
        values.put("cantidad_paginas",libro.getCantidad_paginas());
        values.put("autor",libro.getAutor().getId());
        values.put("editorial",libro.getEditorial().getId());
        values.put("estante",libro.getEstanteria().getId());

        return db.insert(DB_TABLE_LIBROS,null,values);
    }

    public void actualizarLibro(Libro libro){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("titulo",libro.getTitulo());
        values.put("descripcion",libro.getDescripcion());
        values.put("fecha_publicacion",libro.getFecha_publicacion());
        values.put("copias",libro.getCopias());
        values.put("cantidad_paginas",libro.getCantidad_paginas());
        values.put("autor",libro.getAutor().getId());
        values.put("editorial",libro.getEditorial().getId());
        values.put("estante",libro.getEstanteria().getId());

        String[]args = {String.valueOf(libro.getId())};

        db.update(DB_TABLE_LIBROS,values,"id = ?",args);
    }

    public void eliminarLibro(int id){
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase bd = helper.getWritableDatabase();

        String[]args = {String.valueOf(id)};
        bd.delete(DB_TABLE_LIBROS,"id = ?",args);
    }

}
