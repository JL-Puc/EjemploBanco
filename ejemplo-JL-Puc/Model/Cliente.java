package Model;

import java.io.Serializable;

import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;

public class Cliente implements Serializable{
    
    private String idCliente;
    private String nombre;
    private ListaDeCuentas cuentas = new ListaDeCuentas();

    public Cliente ( String idCliente, String nombre, Cuenta cuenta) {
        setNombre(nombre);
        setIdCliente(idCliente);
        this.cuentas.agregarCuenta(cuenta);
    }

    public Cliente (){
    }

    public Cliente ( String idCliente, String nombre) {
        setNombre(nombre);
        setIdCliente(idCliente);
    }


    //Funciones 
    public String imprimirCliente( ) {
        String clienteDatos = "ID: " + idCliente + "\tNombre: " + nombre + "\n";
        int contadorCuentas = 0;

        while( contadorCuentas < cuentas.size() ){
            clienteDatos += "\t\t\t\t\tCuenta: " + cuentas.getListaCuentas().get(contadorCuentas).getIdCuenta() + "\t\tSaldo: " + "$" + cuentas.getListaCuentas().get(contadorCuentas).getSaldo() + "\n";

            contadorCuentas++;
        }

        return clienteDatos;
    }

    public void borrarCuenta(String idCuenta ) throws ExcepcionCliente, ExcepcionCuenta {
        cuentas.eliminarCuenta(idCuenta);
    }

    //Getters and Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Cuenta ultimaCuentaAgregada( ) {
        return cuentas.getListaCuentas().get(cuentas.size()-1);
    }

    public ListaDeCuentas getCuentas() {
        return cuentas;
    }

    public void setCuentas(ListaDeCuentas cuentas) {
        this.cuentas = cuentas;
    }


}
