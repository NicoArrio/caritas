package com.ingenieria.caritas.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.ingenieria.caritas.MainActivity;
import com.ingenieria.caritas.R;
import com.ingenieria.caritas.databinding.FragmentIngresarBinding;
import com.ingenieria.caritas.modelo.usuario.Usuario;

import java.util.List;

public class ingresarFragment extends Fragment {

    private IngresarViewModel mViewModel;
    private FragmentIngresarBinding binding;
    private EditText nombreEditext,passEditext;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentIngresarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(IngresarViewModel.class);
        nombreEditext= binding.usernameInput;
        passEditext=binding.passInputInput;

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validar()) {
                    Usuario usuario= new Usuario();
                    LiveData<List<Usuario>> usuarioPorNombre = mViewModel.getUsuarioPorNombre(nombreEditext.getText().toString());
                    usuarioPorNombre.observe(getViewLifecycleOwner(),usuarios -> {
                        if (usuarios!= null && !usuarios.isEmpty()){

                            NavHostFragment.findNavController(ingresarFragment.this)
                                    .navigate(R.id.action_ingresarFragment_to_nav_home);
                        }else {
                            Toast.makeText(requireContext(),"No existe usuario",Toast.LENGTH_LONG).show();
                        }

                    });
                }
            }
        });

        binding.irRegistroBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ingresarFragment.this)
                        .navigate(R.id.action_ingresarFragment_to_registrarFragment);
            }
        });
    }

    public void ingresar(){

    }

    public boolean validar (){
        boolean resultado = true;
        if (nombreEditext.getText().toString().equals("")){
            resultado= false;
            nombreEditext.setError("ingresar nombre");
        } else if (passEditext.getText().toString().equals("")) {
            resultado=false;
            passEditext.setError("ingresar pass");
        }

        return  resultado;

    }
}