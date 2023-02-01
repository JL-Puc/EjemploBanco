

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ResourceBundle.Control;

import javax.swing.JOptionPane;

import Controlador.ControladorCliente;
import DAO.DaoFichero;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.Cuenta;

public class Main {
    
    public static void main(String[] args) {
        
  

        String nombre;
        Cliente cliente1;
        Cuenta cuenta;
        String idCuenta = "";
        String saldo;
        String idCliente;
        DaoFichero daoFichero = new DaoFichero();
        
        /*
            idCliente = "12453";
            nombre = "Luis";
            idCuenta = "1234567811111111";
            saldo = "1243";
            cuenta = new Cuenta(idCuenta, saldo);
            cliente1 = new Cliente(idCliente, nombre, cuenta);
            
                ControladorCliente control = new ControladorCliente(cliente1, daoFichero);

                try {
                    control.crearClienteValido(cliente1);
                } catch (ExcepcionCliente | ExcepcionCuenta | IOException e) {
                    System.out.println(e.getMessage());
                }
         * 
         */
            
        try {
            daoFichero.imprimirClientes();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
       
    } 

    
    
    
    






}





