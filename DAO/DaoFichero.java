package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Model.Cliente;

public class DaoFichero {

    private File archivo;
    private static DaoFichero dao = new DaoFichero();


    public void crearArchivo(){
             archivo = new File("Ejemplo01\\Banco.txt");
    }

    public void agregarCliente( Cliente cliente) {

        if( archivo == null) {
            crearArchivo();
        }


        FileWriter escribirArchivo;

        try {
            
            cliente = verificarNumeroCliente(cliente);

            escribirArchivo = new FileWriter("Ejemplo01\\Banco.txt",true);
            escribirArchivo.write("\n" + cliente.getNumeroCliente() + "," + cliente.getNombre() + "," + cliente.getNumeroCuenta() + "," + cliente.getSaldo());
            escribirArchivo.close();
            System.out.println("Cliente agregado\n");

        } catch (IOException e) {
           System.out.println(e.getMessage());
           System.out.println("Cliente no agregado\n");
        }

    }

    public Cliente verificarNumeroCliente( Cliente cliente) throws FileNotFoundException {
        File archivo = new File("Ejemplo01\\Banco.txt");

        StringTokenizer token;
        Scanner escaner = new Scanner(this.archivo);
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
                cliente.setNumeroCliente(Integer.parseInt(numeroCliente));
                contador = 1;
             } else {
                 cliente.setNumeroCliente(Integer.parseInt(numeroCliente) + 1);
             }

        }
        escaner.close();
        
        return cliente;
    }






}