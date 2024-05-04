package com.example.gestorenpleadosinigocembo;

import java.sql.PreparedStatement;
import java.util.ArrayList;
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

    /*public void aniadir(ArrayList<String> colum)
    {
        PreparedStatement ps = conexion.prepareStatement("INSERT INTO empleado (nombre, puesto, salario, fecha) VALUES(?,?,?,NOW())");
        ps.setString(1, colum.getFirst());
        ps.setString(2,colum.get(1));
        ps.setString(3, colum.get(2));
    }*/
}
