package com.andresduque01.uniremington;

import java.io.Serializable;

public class Referencia implements Serializable {

    private  String correo;

    public Referencia() {
    }

    public Referencia(String correo) {
        this.correo = correo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
}
