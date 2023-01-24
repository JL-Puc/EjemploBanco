package Ejemplo01;

import DAO.DaoFichero;
import Model.Cliente;

public class Main {
    
    public static void main(String[] args) {
        
        DaoFichero dao = new DaoFichero();

        Cliente cliente1 = new Cliente("Jose", 1241421421, 100);

        dao.agregarCliente(cliente1);



       
    } 
    
    
    






}





