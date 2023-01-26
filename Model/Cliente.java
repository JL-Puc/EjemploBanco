package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.NameWrong;

public class Cliente {
    
    private int idCliente;
    private String nombre;
    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
    Pattern p;
    Matcher m;

    public Cliente ( String nombre, Cuenta cuenta) throws NameWrong {
        setNombre(nombre);
        this.cuentas.add(cuenta);
    }

    //Funciones 


    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws NameWrong {
        p = Pattern.compile("^([A-Z]{1}[a-z]+)+$");
        m = p.matcher(nombre);
        if(m.matches()) {
            this.nombre = nombre;
        } else {
            throw new NameWrong("Nombre incorrecto");
        }
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
