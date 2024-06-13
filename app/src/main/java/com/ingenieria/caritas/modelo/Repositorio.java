package com.ingenieria.caritas.modelo;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ingenieria.caritas.modelo.usuario.Usuario;
import com.ingenieria.caritas.modelo.usuario.UsuarioDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Repositorio {

    private final UsuarioDao usuarioDao;

    private final Executor executor;

    public Repositorio(Application application) {
        AppDatabase database = AppDatabaseProvider.getInstance(application);
        usuarioDao = database.usuarioDao();


        executor = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<Usuario>> getAllUsuarios() {
        return usuarioDao.getAllUsuario();
    }

    public LiveData<List<Usuario>> getUsuarioPorNombre(String nombre) {
        return usuarioDao.getUsuarioPorNombre(nombre);
    }


    public void insertUsuario(Usuario usuario) {
        executor.execute(() -> usuarioDao.insert(usuario));
    }
}
