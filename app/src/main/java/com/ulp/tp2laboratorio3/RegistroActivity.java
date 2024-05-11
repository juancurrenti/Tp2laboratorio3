package com.ulp.tp2laboratorio3;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.ulp.tp2laboratorio3.databinding.ActivityRegistroBinding;

public class RegistroActivity extends AppCompatActivity {
    private RegistroViewModel rm;
    private ActivityRegistroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rm = new ViewModelProvider(this).get(RegistroViewModel.class);

        if (getIntent().hasExtra("usuarioDatos")) {
            String usuarioDatos = getIntent().getStringExtra("usuarioDatos");
            cargarDatosUsuario(usuarioDatos);
        }

        binding.buttonRegistrar.setOnClickListener(v -> {
            guardarUsuario();
        });

        binding.cbPassword.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            } else {
                binding.etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
            binding.etPassword.setSelection(binding.etPassword.getText().length());
        });
    }

    private void cargarDatosUsuario(String datos) {
        String[] partes = datos.split(" ");
        if (partes.length >= 5) {
            binding.etEmail.setText(partes[0]);
            binding.etPassword.setText(partes[1]);
            binding.etNombre.setText(partes[2]);
            binding.etApellido.setText(partes[3]);
            binding.etDni.setText(partes[4]);
            binding.buttonRegistrar.setText("Actualizar Datos");
        }
    }

    private void guardarUsuario() {
        String email = binding.etEmail.getText().toString();
        String password = binding.etPassword.getText().toString();
        String nombre = binding.etNombre.getText().toString();
        String apellido = binding.etApellido.getText().toString();
        long dni = Long.parseLong(binding.etDni.getText().toString());

        boolean resultado = rm.guardarActualizarUsuario(email, password, nombre, apellido, dni);

        if (resultado) {
            Toast.makeText(getApplicationContext(), "Datos del usuario actualizados correctamente!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nuevo usuario registrado correctamente!", Toast.LENGTH_LONG).show();
        }
        finish();
    }

}
