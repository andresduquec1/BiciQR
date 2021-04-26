package com.andresduque01.uniremington;

public class Sesion {

    private String correo;
    private String contraseña;

    public Sesion() {
    }

    public Sesion(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
    }

    public String getCorreo() {
        return correo;
    }

    public String getContraseña() {
        return contraseña;
    }
}
