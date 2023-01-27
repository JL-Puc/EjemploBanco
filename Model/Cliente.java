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
    Pattern p;
    Matcher m;

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
        p = Pattern.compile("^([A-Z]{1}[a-z]+)+$");
        m = p.matcher(nombre);
        if(m.matches()) {
            this.nombre = nombre;
        } else {
            throw new NameWrong("Nombre incorrecto (El nombre comienza con Mayúscula y no contiene número o signos especiales)");
        }
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) throws IdCustomerIncorrect {
        p = Pattern.compile("^[0-9]{5}$");
        m = p.matcher(idCliente);
        if(m.matches()) {
            this.idCliente = idCliente;
        } else {
            throw new IdCustomerIncorrect("El número de ID debe contener exactamente 10 digítos");
        }

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
