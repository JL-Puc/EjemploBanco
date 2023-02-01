package Model;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import DAO.DaoFichero;

public class ListaDeClientes {
    
    private ArrayList<Cliente> listaClientes;

    public ListaDeClientes() {
        listaClientes = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void eliminar(int indice ) {
        listaClientes.remove(indice);
    }

    public void cargarClientes( ) throws FileNotFoundException{
        DaoFichero daoFichero = new DaoFichero();
        listaClientes = daoFichero.traerClientes();
    }


}
