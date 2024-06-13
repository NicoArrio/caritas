package com.ingenieria.caritas.modelo.usuario;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UsuarioDao {
    @Insert
    void insert(Usuario usuario);

    @Query("SELECT * FROM Usuario")
    LiveData<List<Usuario>> getAllUsuario();

    @Query("SELECT * FROM Usuario WHERE nombre = :nombre")
    LiveData<List<Usuario>> getUsuarioPorNombre(String nombre);


}
