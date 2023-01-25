package Ejemplo01;

import java.util.Scanner;

import javax.swing.JOptionPane;

import DAO.DaoFichero;
import Exceptions.IncorrectData;
import Model.Cliente;

public class Main {
    
    public static void main(String[] args) {

        Scanner entrada = new Scanner(System.in);
        
        DaoFichero dao = new DaoFichero();

        String nombre;
        long numeroCuenta;
        double saldo;


try {
        nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente: ",null);
        numeroCuenta = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número de cuenta del cliente: ", null));
        saldo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el saldo del cliente: ", null));

        Cliente cliente1 = new Cliente(nombre, numeroCuenta, saldo);

        dao.agregarCliente(cliente1);

} catch (Exception e) {
   JOptionPane.showMessageDialog(null,"Ingrese los datos con el formato correcto");
}
        



       
    } 
    
    
    






}





