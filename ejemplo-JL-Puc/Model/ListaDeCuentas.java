package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Exceptions.ExcepcionCliente;

public class ListaDeCuentas implements Serializable{

    private ArrayList<Cuenta> listaCuenta;

    public ListaDeCuentas() {
        listaCuenta = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        listaCuenta.add(cuenta);
    }

    public void eliminarCuenta(String idCuenta ) throws ExcepcionCliente {
        listaCuenta.remove(getCuenta(idCuenta));
    }

    public ArrayList<Cuenta> getListaCuentas ( ) {
        return listaCuenta;
    }

    public Cuenta getCuenta(String idCuenta ) throws ExcepcionCliente {
        int contador = 0;

        while(contador < listaCuenta.size() ) {
            if(idCuenta.equals(listaCuenta.get(contador).getIdCuenta()) ){
                return listaCuenta.get(contador);
            }
        contador++;
        }
        throw new ExcepcionCliente("Número de cuenta no encontrado");
    }

    public int size( ) {
        return listaCuenta.size();
    }

    public Cuenta ultimaCuentaAgregada() {
        return listaCuenta.get(listaCuenta.size()-1);
    }










}
