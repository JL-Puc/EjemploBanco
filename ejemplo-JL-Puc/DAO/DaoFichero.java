package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.SerializarObjeto;

public class DaoFichero {

    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();


    public void agregarCliente(Cliente cliente) throws ExcepcionCuenta, IOException, ExcepcionCliente, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException{ // Agregar cliente serializandolo, solamente con sus datos de ID y nombre

        SerializarObjeto.serializarObjeto("ejemplo-JL-Puc\\DataClientes\\" + cliente.getIdCliente() + ".txt", cliente);
    }

    public Cliente iniciarDatos(String idCliente) throws ExcepcionCliente{ //Deserializa un cliente 
        Cliente clienteAux = SerializarObjeto.deserializarObjeto("ejemplo-JL-Puc\\DataClientes\\" + idCliente + ".txt", Cliente.class);

        if (clienteAux != null || !clienteAux.getIdCliente().equals(null)){
            System.out.println("Cargado datos del archivo del cliente: " + idCliente);
            return clienteAux;
        } else {
           throw new ExcepcionCliente("ID inexistente");
        }

    }


    public void borrarCliente(String idCliente) {// Elimina a un cliente, borrando su archivo 
        File archivo = new File("ejemplo-JL-Puc\\DataClientes\\" + idCliente + ".txt");
        archivo.delete();
    }

    public void actualizarCliente(Cliente cliente) throws ExcepcionCliente, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException{ //Reescribe el archivo del cliente para su modificación
        String path = "ejemplo-JL-Puc\\DataClientes\\" + cliente.getIdCliente() + ".txt";
        File archivo = new File(path);

        if( archivo.exists()) {
            SerializarObjeto.serializarObjeto(path, cliente);
        } else {
            throw new ExcepcionCliente("Error al actualizar, la ruta del cliente no existe");
        }
    }


    public ArrayList<Cliente> traerClientes ( ) throws FileNotFoundException, ExcepcionCliente { //Con un ArrayList traemos todos los clientes con sus datos 
        File carpeta = new File("ejemplo-JL-Puc\\DataClientes\\"); 

        String[] listaArchivos = carpeta.list();
        Cliente cliente;
        
        for(String string : listaArchivos ) {
            StringTokenizer token = new StringTokenizer(string, ".");
            cliente = iniciarDatos(token.nextToken());
            listaClientes.add(cliente);
        }

        return listaClientes;
    }

    public ArrayList<String> traerIdClientes ( ) throws FileNotFoundException, ExcepcionCliente { //Con un ArrayList traemos todos los clientes con sus datos 
        File carpeta = new File("ejemplo-JL-Puc\\DataClientes\\"); 

        String[] listaArchivos = carpeta.list();
        ArrayList<String> listaIdClientes = new ArrayList<>();
        
        for(String string : listaArchivos ) {
            StringTokenizer token = new StringTokenizer(string, ".");
            listaIdClientes.add(token.nextToken());
        }

        return listaIdClientes;
    }

    
}