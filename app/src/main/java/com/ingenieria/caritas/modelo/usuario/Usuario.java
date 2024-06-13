package com.ingenieria.caritas.modelo.usuario;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = "nombre")
public class Usuario {
    @NonNull
    private String nombre;

    private String pass;


    // Constructor vacío necesario para Room
    public Usuario() {
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}


