package com.ingenieria.caritas.modelo;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.ingenieria.caritas.modelo.usuario.Usuario;
import com.ingenieria.caritas.modelo.usuario.UsuarioDao;

@Database(entities = {Usuario.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsuarioDao usuarioDao();
}