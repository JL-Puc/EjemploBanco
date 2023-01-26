package DAO;

import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import Exceptions.ClienteSinCuenta;
import Exceptions.NumeroCuentaError;
import Exceptions.SaldoIncorrecto;
import Model.Cliente;
import Model.Cuenta;

public class DaoFichero {

    private File archivo;
    private ArrayList<String> listaNumerosCuentas = new ArrayList<String>();

    public void crearArchivo(){
             archivo = new File("Ejemplo01\\Banco.txt");
    }

    public void agregarCuenta( int opcion, Cliente cliente) throws IOException, ClienteSinCuenta, HeadlessException, NumeroCuentaError, SaldoIncorrecto{

        if( archivo == null) {
            crearArchivo();
        }



        FileWriter escribirArchivo;

            if( opcion == 1) {
                cliente = idPrimeraCuentaUsuario(cliente);
            } else if(opcion == 2) {
                cliente = idCuentaNuevaUsuario(cliente);
            }

            if( cliente.getIdCliente() != 0 ) {
                if( !verificarNumeroCuenta(cliente, cliente.ultimaCuentaAgregada().getIdCuenta())) { // Aquí validamos si el número de cuenta del cliente ya existe, en dado caso exista no se registra

                    escribirArchivo = new FileWriter("Ejemplo01\\Banco.txt",true);

                    escribirArchivo.write("\n" + cliente.getIdCliente() + "," + cliente.getNombre() + "," + cliente.ultimaCuentaAgregada().getIdCuenta() + "," + cliente.ultimaCuentaAgregada().getSaldo());
                    escribirArchivo.close();
                    JOptionPane.showMessageDialog(null, "Cliente agregado\n");
                    
                } else {
                    throw new ClienteSinCuenta("Error: Este número de cuenta ya existe, introduzca uno diferente");
                }
                
            } else {
                throw new ClienteSinCuenta("Usted no tiene una cuenta existente");
            }


    }

    public Cliente idPrimeraCuentaUsuario( Cliente cliente) throws FileNotFoundException {
        File archivo = new File("Ejemplo01\\Banco.txt");

        StringTokenizer token;
        Scanner escaner = new Scanner(archivo);
        String linea;
        String numeroCliente;
        int numeroMAX = 0;

        while(escaner.hasNext()) {
             linea = escaner.nextLine();
             token = new StringTokenizer(linea,",");

             numeroCliente = token.nextToken();
                
                if( numeroMAX < Integer.parseInt(numeroCliente)) {
                    numeroMAX = Integer.parseInt(numeroCliente);
                }  

        }
        cliente.setIdCliente(numeroMAX + 1);    
        escaner.close();
        
        return cliente;
    }

    public Cliente idCuentaNuevaUsuario( Cliente cliente) throws FileNotFoundException {
        File archivo = new File("Ejemplo01\\Banco.txt");

        StringTokenizer token;
        Scanner escaner = new Scanner(archivo);
        String linea;
        String nombreExistente;
        String numeroCliente;
        int contador = 0;

        while(escaner.hasNext() && contador == 0) {
             linea = escaner.nextLine();
             token = new StringTokenizer(linea,",");

             numeroCliente = token.nextToken();
             nombreExistente = token.nextToken();
             String nombreCliente = cliente.getNombre();

             if(nombreCliente.equals(nombreExistente) ) {
                cliente.setIdCliente(Integer.parseInt(numeroCliente));
                contador = 1;
             } else {
                cliente.setIdCliente(0);
             }

        }
        escaner.close();
        
        return cliente;
    }

    public boolean verificarNumeroCuenta ( Cliente cliente, String numeroCuenta) throws FileNotFoundException, NumeroCuentaError, SaldoIncorrecto{
        int contador = 0;
        cliente = traerCuentasCliente(cliente); 

        while( contador < cliente.getCuentas().size()-1 ) {
            listaNumerosCuentas.add(cliente.getCuentas().get(contador).getIdCuenta()); //Llenamos la lista de las cuentas de un solo cliente
            contador++;
        }
        
        return listaNumerosCuentas.contains(numeroCuenta);
    }

    public Cliente traerCuentasCliente ( Cliente cliente) throws FileNotFoundException, NumeroCuentaError, SaldoIncorrecto{
        File archivo = new File("Ejemplo01\\Banco.txt");

        StringTokenizer token;
        Scanner escaner = new Scanner(archivo);
        String linea;
        int idCliente;
        String saldo;
        String idCuenta;
        int contador = 0;

        while(escaner.hasNext() && contador == 0) {
             linea = escaner.nextLine();
             token = new StringTokenizer(linea,",");

             idCliente = Integer.parseInt(token.nextToken());

             if( idCliente == cliente.getIdCliente()) {
                token.nextToken(); //El nombre del cliente
                idCuenta = token.nextToken();
                saldo = token.nextToken();

                Cuenta cuenta = new Cuenta(idCuenta, saldo);
                cliente.getCuentas().add(cuenta);
             }

        }
        escaner.close();

        return cliente;
    }






}