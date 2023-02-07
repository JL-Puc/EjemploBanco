package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import DAO.DaoCuentas;
import DAO.DaoFichero;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.ListaDeClientes;
import Model.ListaDeCuentas;

public class ControladorCliente {
    
    Cliente cliente = new Cliente();
    DaoFichero daoFichero = new DaoFichero();
    DaoCuentas daoCuentas = new DaoCuentas();
    ControladorCuenta controladorCuenta = new ControladorCuenta();
    Pattern p;
    Matcher m;

    //Constructor
    public ControladorCliente() {
    }

    //Validar datos
    public void agregarClienteValido(String nombreCliente,  String idCliente, String idCuenta, String saldo ) throws ExcepcionCliente, ExcepcionCuenta, IOException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException {

        if( validarDatosCliente(nombreCliente, idCliente, idCuenta, saldo)) {
            
            if(verificarIdClienteExistencia(idCliente) == false) { //Verificar el ID del cliente, si es falso significa que el nombre y el ID no coincide con alguna existencia.

                    controladorCuenta.agregarCuentaNueva(idCliente, idCuenta, saldo);

                    Cliente cliente = new Cliente(idCliente, nombreCliente);

                    daoFichero.agregarCliente(cliente);
   
            }
     
        }
    }


    public void borrarCliente( String idCliente) throws ExcepcionCliente, ExcepcionCuenta{

        daoFichero.borrarCliente(idCliente);

    }

    //Borrar una cuenta del cliente
    public void borrarCuentaCliente(String nombreCliente,  String idCliente, String idCuenta ) throws ExcepcionCuenta, ExcepcionCliente, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException {
        if(controladorCuenta.borrarCuenta(idCliente, idCuenta) == false) {
            System.out.println("Usted solamente tiene una cuenta, su usuario será borrado, bye, bye...");
            daoFichero.borrarCliente(idCliente);
        }
    }


    //Cambiar nombre de cliente
    public void cambiarNombreCliente(String nombreCliente,  String idCliente ) throws ExcepcionCliente, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, IOException {
        validarNombreCliente(nombreCliente); //Validar el nombre que se desea agregar como modificacion

        Cliente cliente = daoFichero.iniciarDatos(idCliente); //Recuperar los datos del cliente con el ID ingresado
        cliente.setNombre(nombreCliente); //Reasignarle el nuevo nombre al cliente 

        daoFichero.actualizarCliente(cliente); //Serializarlo de nuevo con su actualización
        
    }



    //Validar datos de entrada para el registro de un cliente
    public boolean validarDatosCliente(String nombre,  String idCliente, String idCuenta, String saldo  ) throws ExcepcionCliente, ExcepcionCuenta, IOException {

        if( validarNombreCliente(nombre) == true && validarIdCliente(idCliente) == true && validarNumeroCuenta(idCuenta) == true && validarSaldoCuenta(saldo) == true) {
            return true;
        }
        return false;
    }





    //Validamos el nombre del cliente
    public boolean validarNombreCliente( String nombre) throws ExcepcionCliente {

        p = Pattern.compile("^([A-Z]{1}[a-z]+)+$");
        m = p.matcher(nombre);
        if(m.matches()) {
            return true;
        } else {
            throw new ExcepcionCliente("Nombre incorrecto (El nombre comienza con Mayúscula y no contiene número o signos especiales)");
        }
    }

    //Validamos el ID del cliente
    public boolean validarIdCliente( String idCliente) throws ExcepcionCliente {
        p = Pattern.compile("^[0-9]{5}$");
        m = p.matcher(idCliente);
        if(m.matches()) {
            return true;
        } else {
            throw new ExcepcionCliente("El número de ID debe contener exactamente 10 digítos");
        }

    }

    //Validamos el numero de cuenta
    public boolean validarNumeroCuenta( String idCuenta) throws ExcepcionCuenta {

        p = Pattern.compile("^[0-9]{16}$");
        m = p.matcher(idCuenta);
        if(m.matches()) {
            return true;
        } else {
            throw new ExcepcionCuenta("El número de cuenta debe contener exactamente 16 digítos");
        }
    }

    //Validamos el saldo 
    public boolean validarSaldoCuenta(String saldo) throws ExcepcionCuenta {

        p = Pattern.compile("^[0-9]+([\\.,][0-9]+)?$");
        m = p.matcher(saldo);
        if(m.matches()) {
         return true;
        } else {
            throw new ExcepcionCuenta("Por favor ingrese un número");
        }
    }

    public boolean verificarIdClienteExistencia( String idCliente) throws ExcepcionCliente, FileNotFoundException {
        ArrayList<String> listaIdClientes = new ArrayList<>();

        listaIdClientes = daoFichero.traerIdClientes();

        if(listaIdClientes.contains(idCliente)) {
                
        throw new ExcepcionCliente("El ID ya existe, ingrese uno diferente");
        }

        return false;

    }


    public void generarReporte( ) throws FileNotFoundException, ExcepcionCuenta, ExcepcionCliente {
        int contadorClientes = 0;
        ListaDeClientes listaClientes = new ListaDeClientes();
        listaClientes.cargarClientes(); //Cargar los clientes registrados
        ArrayList<ListaDeCuentas> listaDeCuentas = daoCuentas.traerCuentasCliente(); //Inicializar todas las cuentas existentes
        
        while(contadorClientes < listaClientes.size()) { //asignarle a cada cliente sus cuentas
            listaClientes.getListaClientes().get(contadorClientes).setCuentas(listaDeCuentas.get(contadorClientes));
            
            System.out.println(listaClientes.getListaClientes().get(contadorClientes).imprimirCliente()); //Imprimir los datos del cliente
            contadorClientes++;
        }
                

    }


}
