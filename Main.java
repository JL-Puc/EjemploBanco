

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import DAO.DaoFichero;
import Exceptions.ClienteSinCuenta;
import Exceptions.NameWrong;
import Exceptions.NumeroCuentaError;
import Exceptions.SaldoIncorrecto;
import Model.Cliente;
import Model.Cuenta;

public class Main {
    
    public static void main(String[] args) {
        
  
        DaoFichero dao = new DaoFichero();

        String nombre;
        Cliente cliente1;
        Cuenta cuenta;
        String idCuenta = "";
        String saldo;
        int opcion;

        //opcion = Integer.parseInt(JOptionPane.showInputDialog("Cliente nuevo -Presione 1 - para agregar primera cuenta a su usuario \n\n" + "Cliente existente -Presione 2 - para agregar una nueva cuenta a su usuario \n\n"  ));
        
        //XD
       
        try {
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
            idCuenta = JOptionPane.showInputDialog("Ingrese el número de cuenta: " );
            saldo = JOptionPane.showInputDialog("Ingrese el saldo:");
            cuenta = new Cuenta(idCuenta, saldo);
            cliente1 = new Cliente(nombre, cuenta);
        } catch (SaldoIncorrecto | NumeroCuentaError | NameWrong e) {
             System.out.println(e.getMessage());
        }
       
        
        /* 
        cuenta = new Cuenta(idCuenta, saldo);
        cliente1 = new Cliente(nombre, cuenta);

        try {
            switch(opcion ) {
                case 1:   
                    dao.agregarCuenta(opcion, cliente1);
    
                    break;
                case 2:
                    dao.agregarCuenta(opcion, cliente1);
    
                    break;
                default: 
                    System.out.println("Opcion inválida");
                    break;
            }
        } catch (IOException | ClienteSinCuenta e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        
        


*/
       
    } 

    
    
    
    






}





