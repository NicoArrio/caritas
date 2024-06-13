package com.ingenieria.caritas.ui.registro;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ingenieria.caritas.R;
import com.ingenieria.caritas.databinding.FragmentRegistrarBinding;
import com.ingenieria.caritas.modelo.usuario.Usuario;
import com.ingenieria.caritas.ui.login.IngresarViewModel;
import com.ingenieria.caritas.ui.login.ingresarFragment;

public class RegistrarFragment extends Fragment {

    private IngresarViewModel mViewModel;
    FragmentRegistrarBinding binding;
    EditText nombreUsuario,passUsuario;



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegistrarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(IngresarViewModel.class);
        nombreUsuario = binding.registroUsername;
        passUsuario = binding.registroPass;

        binding.registroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (validar()){
                Usuario usuario = new Usuario();
                usuario.setNombre(nombreUsuario.getText().toString());
                usuario.setPass(nombreUsuario.getText().toString());
                guardarUsuario(usuario);
                   NavHostFragment.findNavController(RegistrarFragment.this)
                           .navigate(R.id.action_registrarFragment_to_ingresarFragment);
               }

            }
        });

        mostrarUsuarios();
    }
    public void guardarUsuario (Usuario usuario){


            mViewModel.insertarPaciente(usuario);
            Toast.makeText(requireContext(), "Usuario guardado correctamente", Toast.LENGTH_SHORT).show();


    }
    public boolean validar (){
        boolean resultado = true;
        if (nombreUsuario.getText().toString().equals("")){
            resultado= false;
            nombreUsuario.setError("ingresar nombre");
        } else if (passUsuario.getText().toString().equals("")) {
            resultado=false;
            passUsuario.setError("ingresar pass");
        }

        return  resultado;

    }

    public void mostrarUsuarios(){
        mViewModel.getUsuarios().observe(getActivity(),usuarios -> {
            for (Usuario us:usuarios
                 ) {
                Log.e( "Nombre usuario: ", us.getNombre() );
            }
        });
    }
}