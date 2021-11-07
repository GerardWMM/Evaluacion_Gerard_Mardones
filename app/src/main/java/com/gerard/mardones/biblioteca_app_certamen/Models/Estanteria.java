package com.gerard.mardones.biblioteca_app_certamen.Models;

import java.io.Serializable;
import java.util.Objects;

public class Estanteria implements Serializable {
    private int id;
    private String letra;
    private int numero;
    private String color;

    public Estanteria(){

    }

    public Estanteria(String letra, int numero, String color){
        this.letra = letra;
        this.numero = numero;
        this.color = color;
    }

    public Estanteria(int id, String letra, int numero, String color) {
        this.id = id;
        this.letra = letra;
        this.numero = numero;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public String getLetra() {
        return letra;
    }

    public int getNumero() {
        return numero;
    }

    public String getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Estanteria)) return false;
        Estanteria that = (Estanteria) o;
        return getId() == that.getId() && getNumero() == that.getNumero() && getLetra().equals(that.getLetra()) && getColor().equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLetra(), getNumero(), getColor());
    }

    @Override
    public String toString() {
        return this.letra+" | "+this.numero+" | "+this.color;
    }
}
