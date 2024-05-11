package com.ulp.tp2laboratorio3;


import java.io.Serializable;

public class Usuario implements Serializable {
    private String mail;
    private String password;
    private String nombre;
    private String apellido;
    private long dni;

    public Usuario(String mail, String password, String nombre, String apellido, long dni) {
        this.mail = mail;
        this.password = password;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Usuario() {

    }


    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }


    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public long getDni() {
        return dni;
    }

    public void setDni(long dni) {
        this.dni = dni;
    }
    @Override
    public String toString(){
        return "Usuario{" +
                "dni=" + dni +
                ", apellido='" + apellido + '\'' +
                ", nombre='" + nombre + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

