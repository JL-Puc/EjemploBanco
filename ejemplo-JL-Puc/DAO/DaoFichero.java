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
    private ArrayList<String> listaNumerosCuentas = new ArrayList<String>();
    private ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();

    public void crearArchivo(){
             archivo = new File("Ejemplo01\\Banco.txt");
    }

    public void agregarCuenta(Cliente cliente) throws ExcepcionCuenta, IOException, ExcepcionCliente{

        if( archivo == null) {
            crearArchivo();
        }



        FileWriter escribirArchivo;

            if(verificarIdClienteExistencia(cliente) == false) { //Verificar el ID del cliente, si es falso significa que el nombre y el ID no coincide

                if( false == verificarNumeroCuenta(cliente, cliente.getCuentas().get(0).getIdCuenta().toString())) { // Aquí validamos si el número de cuenta del cliente ya existe, en dado caso exista no se registra

                    escribirArchivo = new FileWriter("Ejemplo01\\Banco.txt",true);

                    escribirArchivo.append("\n" + cliente.getIdCliente() + "," + cliente.getNombre() + "," + cliente.getCuentas().get(0).getIdCuenta() + "," + cliente.getCuentas().get(0).getSaldo());
                    escribirArchivo.close();
                    JOptionPane.showMessageDialog(null, "Cliente agregado\n");
                    
                } else {
                    throw new ExcepcionCuenta("El número de cuenta ya existe en tu usuario");
                }
            } else {
                throw new ExcepcionCliente("El ID ya existe, ingrese uno diferente");
            }
             

                

    }

    public boolean verificarNumeroCuenta ( Cliente cliente, String numeroCuenta) throws FileNotFoundException{
        int contador = 1;
        cliente = traerDatosCliente(cliente); 

        while( contador < cliente.getCuentas().size() ) {
            listaNumerosCuentas.add(cliente.getCuentas().get(contador).getIdCuenta()); //Llenamos la lista de las cuentas de un solo cliente
            contador++;
        }
        
        return listaNumerosCuentas.contains(numeroCuenta); //Si es verdad entonces esa cuenta existe.
    }

    public Cliente traerDatosCliente ( Cliente cliente) throws FileNotFoundException {
        File archivo = new File("Ejemplo01\\Banco.txt");

        StringTokenizer token;
        Scanner escaner = new Scanner(archivo);
        String linea;
        String idCliente;
        String saldo;
        String idCuenta;

        while(escaner.hasNext()) {
             linea = escaner.nextLine();
             token = new StringTokenizer(linea,",");

             idCliente = token.nextToken();

             if( idCliente.equals(cliente.getIdCliente())) {
                String nombre = token.nextToken(); //El nombre del cliente
                idCuenta = token.nextToken();
                saldo = token.nextToken();

                Cuenta cuenta = new Cuenta(idCuenta, saldo);
                cliente.getCuentas().add(cuenta);
             }
        
        }
        escaner.close();

        return cliente;
    }

    public boolean verificarIdClienteExistencia( Cliente cliente) throws FileNotFoundException{
        File archivo = new File("Ejemplo01\\Banco.txt");
        
        StringTokenizer token;
        Scanner escaner = new Scanner(archivo);
        String linea;
        String numeroCliente;
        String nombreCliente;

        while(escaner.hasNext()) {
             linea = escaner.nextLine();
             token = new StringTokenizer(linea,",");

             numeroCliente = token.nextToken();
             nombreCliente = token.nextToken();

             if(numeroCliente == cliente.getIdCliente() &&  nombreCliente == cliente.getNombre()) {
                return true; 
             }
        }

        escaner.close();
        

        return false;

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

    public void imprimirClientes( ) throws FileNotFoundException{
        listaClientes = traerClientes();
        int contadorClientes = 0;
       
        while(contadorClientes < listaClientes.size()) {
            System.out.println(listaClientes.get(contadorClientes).imprimirCliente()); 
            contadorClientes++;
        }
                

    }




}