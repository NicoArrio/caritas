package com.ingenieria.caritas.ui.login;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ingenieria.caritas.modelo.Repositorio;
import com.ingenieria.caritas.modelo.usuario.Usuario;

import java.util.List;

public class IngresarViewModel extends AndroidViewModel {
    // TODO: Implement the ViewModel
    private final Repositorio repositorio;
    private final LiveData<List<Usuario>> usuarios;

    public IngresarViewModel(Application application) {
        super(application);
        repositorio = new Repositorio(application);
        usuarios = repositorio.getAllUsuarios();

    }

    public LiveData<List<Usuario>> getUsuarios() {
        return usuarios;
    }
    public void insertarPaciente(Usuario usuario) {
        repositorio.insertUsuario(usuario);
    }
    public LiveData<List<Usuario>> getUsuarioPorNombre(String nombre) {
        return repositorio.getUsuarioPorNombre(nombre);
    }
}