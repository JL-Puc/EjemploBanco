
import java.io.IOException;
import Controlador.ControladorCliente;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.Cuenta;
import Model.ListaDeClientes;

public class Main {
    
    public static void main(String[] args) {

        String nombre;
        String idCuenta = "";
        String saldo;
        String idCliente;
        ListaDeClientes listaClientes = new ListaDeClientes();
        ControladorCliente control = new ControladorCliente();
        
        
            idCliente = "12453";
            nombre = "Luis";
            idCuenta = "1234567822222222";
            saldo = "5555";


                try {
                    listaClientes.cargarClientes();
                    control = new ControladorCliente(listaClientes);
                    control.imprimirClientes();
                    System.out.println("\n\n");

                    control.borrarCuentaCliente(nombre, idCliente, idCuenta);
                    control.imprimirClientes();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                } catch (ExcepcionCuenta e) {
                    System.out.println(e.getMessage());
                }
        
            
        
        
       
    } 

    
    
    
    






}





