package com.gerard.mardones.biblioteca_app_certamen.Models;

import java.io.Serializable;
import java.util.Objects;

public class Editorial implements Serializable {
    private  int id;
    private String nombre;
    private String nacionalidad;

    public Editorial(){
    }

    public Editorial(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public Editorial(String nombre, String nacionalidad){
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public Editorial(int id, String nombre, String nacionalidad){
        this.id = id;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Editorial)) return false;
        Editorial editorial = (Editorial) o;
        return getId() == editorial.getId() && getNombre().equals(editorial.getNombre()) && getNacionalidad().equals(editorial.getNacionalidad());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNombre(), getNacionalidad());
    }

    @Override
    public String toString() {
        return this.nombre;
    }
}
