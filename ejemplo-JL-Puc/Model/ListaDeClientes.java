package Model;

import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

import DAO.DaoFichero;
import Exceptions.ExcepcionCliente;

public class ListaDeClientes implements Serializable{
    
    private ArrayList<Cliente> listaClientes;

    public ListaDeClientes() {
        listaClientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void eliminar(String idCuenta ) throws ExcepcionCliente {
        listaClientes.remove(getCliente(idCuenta));
    }

    public void cargarClientes( ) throws FileNotFoundException, ExcepcionCliente{
        DaoFichero daoFichero = new DaoFichero();
        listaClientes = daoFichero.traerClientes();
    }

    public ArrayList<Cliente> getListaClientes ( ) {
        return listaClientes;
    }

    public Cliente getCliente(String idCliente ) throws ExcepcionCliente {
        int contador = 0;
        Cuenta cuenta = new Cuenta("", "");
        Cliente cliente = new Cliente("", "", cuenta);

        while(contador < listaClientes.size() ) {
            if(idCliente.equals(listaClientes.get(contador).getIdCliente()) ){
                return listaClientes.get(contador);
            }
        contador++;
        }
        return cliente;
    }

    public int size( ) {
        return listaClientes.size();
    }
}
