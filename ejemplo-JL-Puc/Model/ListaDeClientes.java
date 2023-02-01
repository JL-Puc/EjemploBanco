package Model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import DAO.DaoFichero;
import Exceptions.ExcepcionCliente;

public class ListaDeClientes {
    
    private ArrayList<Cliente> listaClientes;

    public ListaDeClientes() {
        listaClientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void eliminar(Cliente cliente ) {
        listaClientes.remove(cliente);
    }

    public void cargarClientes( ) throws FileNotFoundException{
        DaoFichero daoFichero = new DaoFichero();
        listaClientes = daoFichero.traerClientes();
    }

    public ArrayList<Cliente> getListaClientes ( ) {
        return listaClientes;
    }

    public Cliente getCliente(String idCliente ) throws ExcepcionCliente {
        int contador = 0;

        while(contador < listaClientes.size() ) {
            if(idCliente.equals(listaClientes.get(contador)) ){
                return listaClientes.get(contador);
            }
        contador++;
        }
        throw new ExcepcionCliente("ID no encontrado");
    }

    public int size( ) {
        return listaClientes.size();
    }
}
