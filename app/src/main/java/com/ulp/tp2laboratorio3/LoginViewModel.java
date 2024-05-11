package com.ulp.tp2laboratorio3;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;


public class LoginViewModel extends AndroidViewModel {
    private MutableLiveData<String> mUsuario;


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }
    public LiveData<String> getMPersona(){
        if(mUsuario ==null){
            mUsuario =new MutableLiveData<>();
        }
        return mUsuario;
    }


    public void leerObjeto(String email, String password) {
        File archivo = new File(getApplication().getFilesDir(), "users.dat");
        boolean usuarioEncontrado = false;
        StringBuilder sb = new StringBuilder();

        try (FileInputStream fis = new FileInputStream(archivo);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            while (true) {
                try {
                    Usuario user = (Usuario) ois.readObject();
                    if (user.getMail().equals(email) && user.getPassword().equals(password)) {
                        sb.append(user.getMail()).append(" ")
                                .append(user.getPassword()).append(" ")
                                .append(user.getNombre()).append(" ")
                                .append(user.getApellido()).append(" ")
                                .append(user.getDni()).append("\n");
                        usuarioEncontrado = true;
                        break;
                    }
                } catch (EOFException eof) {
                    break;
                }
            }

            if (usuarioEncontrado) {
                mUsuario.setValue(sb.toString().trim());
            } else {
                mUsuario.setValue("");
            }

        } catch (Exception e) {
            Log.e("LoginViewModel", "Error al leer el archivo", e);
        }
    }


}
