package com.example.gestorenpleadosinigocembo;

import java.util.Date;

public class Trabajador {
    private String nombre;
    private String puesto;
    private int salario;

    private Date fecha_alta;


    public Trabajador(String nomb, String pues, int sal) {
        nombre=nomb;
        puesto=pues;
        salario=sal;

    }


    public int getSalario() {
        return salario;
    }

    public void setSalario(int salario) {
        this.salario = salario;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void aniadir()
    {

    }
}
