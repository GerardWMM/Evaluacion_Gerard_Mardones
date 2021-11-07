package com.gerard.mardones.biblioteca_app_certamen.Models;

import java.io.Serializable;
import java.util.Objects;

public class Autor implements Serializable {
    private int id;
    private String nombres;
    private String apellidos;
    private String nacionalidad;

    public Autor(){

    }

    public Autor(int id,String mensaje1,String mensaje2){
        this.id = id;
        this.nombres = mensaje1;
        this.apellidos = mensaje2;
    }

    public Autor(String nombres, String apellidos, String nacionalidad) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
    }

    public Autor(int id, String nombres, String apellidos, String nacionalidad) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.nacionalidad = nacionalidad;
    }

    public int getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Autor)) return false;
        Autor autor = (Autor) o;
        return getId() == autor.getId() && getNombres().equals(autor.getNombres()) && getApellidos().equals(autor.getApellidos()) && getNacionalidad().equals(autor.getNacionalidad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombres(), getApellidos(), getNacionalidad());
    }

    @Override
    public String toString() {
        return this.nombres+" "+this.apellidos;
    }
}
