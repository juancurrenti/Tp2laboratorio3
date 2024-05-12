package com.ulp.tp2laboratorio3;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.ulp.tp2laboratorio3.databinding.ActivityMainBinding;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding.btnLogin.setOnClickListener(v -> {
            String email = binding.etEmail.getText().toString();
            String password = binding.etPassword.getText().toString();
            loginViewModel.leerObjeto(email, password);
        });

        binding.btnRegistrar.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
            startActivity(intent);
        });

        loginViewModel.getMPersona().observe(this, usuario -> {
            if (usuario != null && !usuario.isEmpty()) {
                Intent intent = new Intent(LoginActivity.this, RegistroActivity.class);
                intent.putExtra("usuarioDatos", usuario);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Usuario no registrado o contraseña incorrecta.", Toast.LENGTH_LONG).show();
            }
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
}