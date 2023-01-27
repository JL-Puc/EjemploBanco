package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import Exceptions.IdClienteExistente;
import Exceptions.IdCustomerIncorrect;
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

    public void agregarCuenta(Cliente cliente) throws IOException, IdClienteExistente, NumeroCuentaError, SaldoIncorrecto, IdCustomerIncorrect{

        if( archivo == null) {
            crearArchivo();
        }



        FileWriter escribirArchivo;

            cliente = verificarIdClienteExistencia(cliente);
            
                if( false == verificarNumeroCuenta(cliente, cliente.ultimaCuentaAgregada().getIdCuenta())) { // Aquí validamos si el número de cuenta del cliente ya existe, en dado caso exista no se registra

                    escribirArchivo = new FileWriter("Ejemplo01\\Banco.txt",true);

                    escribirArchivo.write("\n" + cliente.getIdCliente() + "," + cliente.getNombre() + "," + cliente.ultimaCuentaAgregada().getIdCuenta() + "," + cliente.ultimaCuentaAgregada().getSaldo());
                    escribirArchivo.close();
                    JOptionPane.showMessageDialog(null, "Cliente agregado\n");
                    
                } else {
                    throw new NumeroCuentaError("El número de cuenta ya existe en tu usuario");
                }

    }

    public boolean verificarNumeroCuenta ( Cliente cliente, String numeroCuenta) throws FileNotFoundException, NumeroCuentaError, SaldoIncorrecto{
        int contador = 0;
        cliente = traerDatosCliente(cliente); 

        while( contador < cliente.getCuentas().size()-1 ) {
            listaNumerosCuentas.add(cliente.getCuentas().get(contador).getIdCuenta()); //Llenamos la lista de las cuentas de un solo cliente
            contador++;
        }
        
        return listaNumerosCuentas.contains(numeroCuenta); //Si es verdad entonces esa cuenta existe.
    }

    public Cliente traerDatosCliente ( Cliente cliente) throws FileNotFoundException, NumeroCuentaError, SaldoIncorrecto{
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

    public Cliente verificarIdClienteExistencia( Cliente cliente) throws FileNotFoundException, IdCustomerIncorrect {
        File archivo = new File("Ejemplo01\\Banco.txt");

        StringTokenizer token;
        Scanner escaner = new Scanner(archivo);
        String linea;
        String numeroCliente;

        while(escaner.hasNext()) {
             linea = escaner.nextLine();
             token = new StringTokenizer(linea,",");

             numeroCliente = token.nextToken();

             if(numeroCliente.equals(cliente.getIdCliente()) ) {
                cliente.setIdCliente(numeroCliente);
                return cliente; // Retornar el ID del cliente en un String
             }

        }
        escaner.close();


        return cliente;

    }






}