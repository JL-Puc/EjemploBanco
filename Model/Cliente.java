package Model;

import java.util.ArrayList;

public class Cliente {
    
    private int idCliente;
    private String nombre;
    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

    public Cliente ( String nombre, Cuenta cuenta) {
        this.nombre = nombre;
        this.cuentas.add(cuenta);
    }

    //Funciones 


    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cuenta ultimaCuentaAgregada( ) {
        return cuentas.get(cuentas.size()-1);
    }

    public ArrayList<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(ArrayList<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    

    


}
