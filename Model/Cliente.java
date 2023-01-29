package Model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Exceptions.IdCustomerIncorrect;
import Exceptions.NameWrong;

public class Cliente {
    
    private String idCliente;
    private String nombre;
    private ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

    public Cliente ( String idCliente, String nombre, Cuenta cuenta) throws NameWrong, IdCustomerIncorrect {
        setNombre(nombre);
        setIdCliente(idCliente);
        this.cuentas.add(cuenta);
    }

    //Funciones 


    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws NameWrong {
        this.nombre = nombre;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) throws IdCustomerIncorrect {
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
