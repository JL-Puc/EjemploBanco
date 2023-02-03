
import java.io.FileNotFoundException;
import java.io.IOException;
import Controlador.ControladorCliente;
import Controlador.ControladorCuenta;
import DAO.DaoFichero;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.Cuenta;
import Model.ListaDeClientes;

public class Main {
    
    public static void main(String[] args) throws ExcepcionCliente {

        String nombre;
        String idCuenta = "";
        String saldo;
        String idCliente = "";
        ControladorCliente control = new ControladorCliente();
        ControladorCuenta controlCuentas = new ControladorCuenta();
        
        
        idCliente = "12453";
        nombre = "Pepe";
        idCuenta = "1234567822222222";
        saldo = "1000";
        

                
        try {
            control.imprimirClientes();
            control.cambiarNombreCliente(nombre, idCliente);
            control.imprimirClientes();
        } catch (ExcepcionCuenta e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
                    
            
            
        
            
    } 

    
    
    
    






}





