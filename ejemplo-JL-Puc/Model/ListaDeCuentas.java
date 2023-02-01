package Model;

import java.util.ArrayList;

import Exceptions.ExcepcionCliente;

public class ListaDeCuentas {

    private ArrayList<Cuenta> listaCuenta;

    public ListaDeCuentas() {
        listaCuenta = new ArrayList<>();
    }

    public void agregarCliente(Cuenta cuenta) {
        listaCuenta.add(cuenta);
    }

    public void eliminar(Cuenta cuenta ) {
        listaCuenta.remove(cuenta);
    }

    public ArrayList<Cuenta> getListaClientes ( ) {
        return listaCuenta;
    }

    public Cuenta getCuenta(String idCuenta ) throws ExcepcionCliente {
        int contador = 0;

        while(contador < listaCuenta.size() ) {
            if(idCuenta.equals(listaCuenta.get(contador)) ){
                return listaCuenta.get(contador);
            }
        contador++;
        }
        throw new ExcepcionCliente("ID no encontrado");
    }

    public int size( ) {
        return listaCuenta.size();
    }











}
