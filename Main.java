

import java.io.IOException;
import javax.swing.JOptionPane;
import DAO.DaoFichero;
import Exceptions.IdClienteExistente;
import Exceptions.IdCustomerIncorrect;
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
        String idCliente;
       
        try {
            idCliente = JOptionPane.showInputDialog("Ingrese su ID (5 digitos): ");
            nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ");
            idCuenta = JOptionPane.showInputDialog("Ingrese el número de cuenta: " );
            saldo = JOptionPane.showInputDialog("Ingrese el saldo:");
            cuenta = new Cuenta(idCuenta, saldo);
            cliente1 = new Cliente(idCliente, nombre, cuenta);
            dao.agregarCuenta(cliente1);
        } catch (SaldoIncorrecto | NumeroCuentaError | NameWrong e) {
             System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IdClienteExistente e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (IdCustomerIncorrect e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        
        
       
    } 

    
    
    
    






}





