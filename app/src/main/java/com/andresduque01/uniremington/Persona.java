package com.andresduque01.uniremington;


public class Persona {

    private String correo;
    private String nombres;
    private String apellidos;
    private String tipo_documento;
    private String cedula;
    private String fecha_nacimiento;
    private String tipo_sangre;
    private String rh;
    private String eps;
    private String enfermedades;
    private String departamento;
    private String ciudad;
    private String direccion;
    private String nombre_contacto;
    private String numero_contacto;

    public Persona() {
    }

    public Persona(String correo, String nombres, String apellidos, String tipo_documento, String cedula, String fecha_nacimiento, String tipo_sangre,
                   String rh, String eps, String enfermedades, String departamento, String ciudad, String direccion,
                   String nombre_contacto, String numero_contacto) {
        this.correo = correo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipo_documento = tipo_documento;
        this.cedula= cedula;
        this.fecha_nacimiento = fecha_nacimiento;
        this.tipo_sangre = tipo_sangre;
        this.rh = rh;
        this.eps = eps;
        this.enfermedades = enfermedades;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.nombre_contacto = nombre_contacto;
        this.numero_contacto = numero_contacto;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String gettipo_documento() {
        return tipo_documento;
    }

    public String getcedula() {
        return cedula;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public String getTipo_sangre() {
        return tipo_sangre;
    }

    public String getRh() {
        return rh;
    }

    public String getEps() {
        return eps;
    }

    public String getEnfermedades() {
        return enfermedades;
    }

    public String getDepartamento() {
        return departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre_contacto() {
        return nombre_contacto;
    }

    public String getNumero_contacto() {
        return numero_contacto;
    }
}
