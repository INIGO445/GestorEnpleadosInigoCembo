package com.example.gestorenpleadosinigocembo;

import java.util.ArrayList;
import java.util.Collection;

public class ListaDeTrabajadores {
    private ArrayList<Trabajador> miLista;
    public ListaDeTrabajadores()
    {
        miLista = new ArrayList<>();
    }
    public ArrayList<Trabajador> getMiLista() {
        return miLista;
    }

    public void setMiLista(ArrayList<Trabajador> miLista) {
        this.miLista = miLista;
    }

}
