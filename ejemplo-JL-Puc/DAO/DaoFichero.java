package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.Cuenta;

public class DaoFichero {

    private File archivo;
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    public void crearArchivo(){
             archivo = new File("ejemplo-JL-Puc\\Ejemplo01\\Banco.txt");
    }

    public void agregarCuenta(Cliente cliente) throws ExcepcionCuenta, IOException, ExcepcionCliente{

        if( archivo == null) {
            crearArchivo();
        }



        FileWriter escribirArchivo;

                    escribirArchivo = new FileWriter("ejemplo-JL-Puc\\Ejemplo01\\Banco.txt",true);

                    escribirArchivo.append("\n" + cliente.getIdCliente() + "," + cliente.getNombre() + "," + cliente.getCuentas().get(0).getIdCuenta() + "," + cliente.getCuentas().get(0).getSaldo());
                    escribirArchivo.close();
                    JOptionPane.showMessageDialog(null, "Cliente agregado\n");
                          

    }


    public ArrayList<Cliente> traerClientes ( ) throws FileNotFoundException { //Con un ArrayList traemos todos los clientes con sus datos 
        File archivo = new File("ejemplo-JL-Puc\\Ejemplo01\\Banco.txt");

        StringTokenizer token;
        Scanner escaner = new Scanner(archivo);
        String linea;
        String idCliente = "";
        String saldo = "";
        String idCuenta = "";
        String nombreCliente = "";
        int contador;
        Cuenta cuenta;
        Cliente cliente;

        while(escaner.hasNext()) { //Leer cada línea del Banco.txt
             contador = 0;
             linea = escaner.nextLine();
             token = new StringTokenizer(linea,",");

             idCliente = token.nextToken();
             nombreCliente = token.nextToken();
             idCuenta =token.nextToken();
             saldo = token.nextToken();

              cuenta = new Cuenta(idCuenta, saldo);
              cliente = new Cliente(idCliente, nombreCliente, cuenta); //Crear el cliente que se leyó del archivo

             listaClientes.add(cliente); //Añadirlo a la lista de clientes
            
            
             while( contador < listaClientes.size() - 1 ) { // Iterador para recorrer la listas de clientes 
                if( listaClientes.get(contador).getIdCliente().equals(idCliente) && listaClientes.get(contador).getNombre().equals(nombreCliente)) { // Si el nombre y el id de del cliente recién leído coincide con alguno de la lista entonces se le agrega la cuenta recien creada al cliente que coincide de la lista
                    listaClientes.get(contador).getCuentas().add(cuenta);
                    listaClientes.remove(cliente); //Enseguida se borra de la lista para que no se repita el cliente
                } else {
                    contador++;
                }
                
             }
             
             
        }
        escaner.close();

        return listaClientes;
    }

    



}