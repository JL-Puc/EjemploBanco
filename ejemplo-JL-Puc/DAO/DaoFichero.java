package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.SerializarObjeto;

public class DaoFichero {

    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();


    public void agregarCuenta(Cliente cliente) throws ExcepcionCuenta, IOException, ExcepcionCliente{

        SerializarObjeto.serializarObjeto("ejemplo-JL-Puc\\Ejemplo01\\" + cliente.getIdCliente() + ".txt", cliente);
    }

    public Cliente iniciarDatos(String idCliente) throws ExcepcionCliente{
        Cliente clienteAux = SerializarObjeto.deserializarObjeto("ejemplo-JL-Puc\\Ejemplo01\\" + idCliente + ".txt", Cliente.class);

        if (clienteAux != null || !clienteAux.getIdCliente().equals(null)){
            System.out.println("Cargado datos del archivo del cliente: " + idCliente);
            return clienteAux;
        } else {
           throw new ExcepcionCliente("ID inexistente");
        }

    }

    public void guardarCliente(String idCliente){
        SerializarObjeto.serializarObjeto("ejemplo-JL-Puc\\Ejemplo01\\" + idCliente + ".txt", Cliente.class);
    }

    public void borrarCliente(String idCliente) {
        File archivo = new File("ejemplo-JL-Puc\\Ejemplo01\\" + idCliente + ".txt");
        archivo.delete();
    }

    public void actualizarCliente(Cliente cliente) throws ExcepcionCliente{
        String path = "ejemplo-JL-Puc\\Ejemplo01\\" + cliente.getIdCliente() + ".txt";
        File archivo = new File(path);

        if( archivo.exists()) {
            SerializarObjeto.serializarObjeto(path, cliente);
        } else {
            throw new ExcepcionCliente("Error al actualizar");
        }
    }


    public ArrayList<Cliente> traerClientes ( ) throws FileNotFoundException, ExcepcionCliente { //Con un ArrayList traemos todos los clientes con sus datos 
        File carpeta = new File("ejemplo-JL-Puc\\Ejemplo01"); 

        String[] listaArchivos = carpeta.list();
        Cliente cliente;
        
        for(String string : listaArchivos ) {
            StringTokenizer token = new StringTokenizer(string, ".");
            cliente = iniciarDatos(token.nextToken());
            listaClientes.add(cliente);
        }

        return listaClientes;
    }

    



}