package reportes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import DAO.DaoCuentas;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.ListaDeClientes;
import Model.ListaDeCuentas;

public class GenerarTxt {
    
    DaoCuentas daoCuentas = new DaoCuentas();
    ListaDeClientes listaClientes = new ListaDeClientes();
    ArrayList<ListaDeCuentas> listaDeCuentas;

    public void cargarClientes( ) throws FileNotFoundException, ExcepcionCuenta, ExcepcionCliente {
        int contadorClientes = 0;
        listaClientes.cargarClientes(); //Cargar los clientes registrados
        listaDeCuentas = daoCuentas.traerCuentasCliente(); //Inicializar todas las cuentas existentes
        
        while(contadorClientes < listaClientes.size()) { //asignarle a cada cliente sus cuentas
            listaClientes.getListaClientes().get(contadorClientes).setCuentas(listaDeCuentas.get(contadorClientes));
            
            System.out.println(listaClientes.getListaClientes().get(contadorClientes).imprimirCliente()); //Imprimir los datos del cliente
            contadorClientes++;
        }
            
    }

    public void crearTxt( ) throws IOException, ExcepcionCuenta, ExcepcionCliente {

        File archivo = new File("ejemplo-JL-Puc\\reportes\\txtTemporal\\Reporte_Clientes.txt");

        cargarClientes();


        FileWriter fr = new FileWriter(archivo);

        int contador = 0;
        fr.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tReporte Clientes\n");
        while(contador < listaClientes.size()){
            fr.write("\t\t\t\t\t" + listaClientes.getListaClientes().get(contador).imprimirCliente() + "\n");
            contador++;
        }
        fr.close();
    }
}
