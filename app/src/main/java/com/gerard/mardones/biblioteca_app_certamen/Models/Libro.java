package com.gerard.mardones.biblioteca_app_certamen.Models;

import java.io.Serializable;
import java.util.Objects;

public class Libro implements Serializable {
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha_publicacion;
    private int copias;
    private int cantidad_paginas;
    private Autor autor;
    private Editorial editorial;
    private Estanteria estanteria;

    public Libro(){

    }

    public Libro(int id,String titulo){
        this.id = id;
        this.titulo = titulo;
    }

    public Libro(String titulo, String descripcion, String fecha_publicacion, int copias, int cantidad_paginas, Autor autor, Editorial editorial, Estanteria estanteria) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_publicacion = fecha_publicacion;
        this.copias = copias;
        this.cantidad_paginas = cantidad_paginas;
        this.autor = autor;
        this.editorial = editorial;
        this.estanteria = estanteria;
    }

    public Libro(int id, String titulo, String descripcion, String fecha_publicacion, int copias, int cantidad_paginas, Autor autor, Editorial editorial, Estanteria estanteria) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha_publicacion = fecha_publicacion;
        this.copias = copias;
        this.cantidad_paginas = cantidad_paginas;
        this.autor = autor;
        this.editorial = editorial;
        this.estanteria = estanteria;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getFecha_publicacion() {
        return fecha_publicacion;
    }

    public int getCopias() {
        return copias;
    }

    public int getCantidad_paginas() {
        return cantidad_paginas;
    }

    public Autor getAutor() {
        return autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public Estanteria getEstanteria() {
        return estanteria;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha_publicacion(String fecha_publicacion) {
        this.fecha_publicacion = fecha_publicacion;
    }

    public void setCopias(int copias) {
        this.copias = copias;
    }

    public void setCantidad_paginas(int cantidad_paginas) {
        this.cantidad_paginas = cantidad_paginas;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    public void setEstanteria(Estanteria estanteria) {
        this.estanteria = estanteria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Libro)) return false;
        Libro libro = (Libro) o;
        return getId() == libro.getId() && getCopias() == libro.getCopias() && getCantidad_paginas() == libro.getCantidad_paginas() && getTitulo().equals(libro.getTitulo()) && getDescripcion().equals(libro.getDescripcion()) && getFecha_publicacion().equals(libro.getFecha_publicacion()) && getAutor().equals(libro.getAutor()) && getEditorial().equals(libro.getEditorial()) && getEstanteria().equals(libro.getEstanteria());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitulo(), getDescripcion(), getFecha_publicacion(), getCopias(), getCantidad_paginas(), getAutor(), getEditorial(), getEstanteria());
    }

    @Override
    public String toString() {
        return this.titulo;
    }
}
