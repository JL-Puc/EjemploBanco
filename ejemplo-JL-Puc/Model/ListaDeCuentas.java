package Model;

import java.io.Serializable;
import java.util.ArrayList;

import Exceptions.ExcepcionCuenta;

public class ListaDeCuentas implements Serializable{

    private ArrayList<Cuenta> listaCuenta;

    public ListaDeCuentas() {
        listaCuenta = new ArrayList<>();
    }

    public void agregarCuenta(Cuenta cuenta) {
        listaCuenta.add(cuenta);
    }

    public void eliminarCuenta(String idCuenta ) throws ExcepcionCuenta {
        listaCuenta.remove(getCuenta(idCuenta));
    }

    public ArrayList<Cuenta> getListaCuentas ( ) {
        return listaCuenta;
    }

    public Cuenta getCuenta(String idCuenta ) throws ExcepcionCuenta {
        int contador = 0;
        Cuenta cuenta = null;
        while(contador < listaCuenta.size() ) {
            if(idCuenta.equals(listaCuenta.get(contador).getIdCuenta()) ){
                return listaCuenta.get(contador);
            }
        contador++;
        }
        return cuenta;
    }

    public int size( ) {
        return listaCuenta.size();
    }

    public Cuenta ultimaCuentaAgregada() {
        return listaCuenta.get(listaCuenta.size()-1);
    }

    public boolean verificarNumeroCuenta(String idCuenta ) {
        int contador = 0;

        while(contador <listaCuenta.size() ) {
            if(listaCuenta.get(contador).getIdCuenta().equals(idCuenta) ) {
                return true;
            }
            contador++;
        }
        return false;
    }


}
