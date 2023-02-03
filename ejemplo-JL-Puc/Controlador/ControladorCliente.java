package Controlador;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.DaoFichero;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.Cuenta;
import Model.ListaDeClientes;

public class ControladorCliente {
    
    Cliente cliente = new Cliente();
    DaoFichero daoFichero = new DaoFichero();
    ControladorCuenta controladorCuenta;
    Pattern p;
    Matcher m;

    //Constructor
    public ControladorCliente() {
    }


    //Validar datos
    public void agregarClienteValido(String nombreCliente,  String idCliente, String idCuenta, String saldo ) throws ExcepcionCliente, ExcepcionCuenta, IOException {

        if( validarDatosCliente(nombreCliente, idCliente, idCuenta, saldo)) {
            
            if(verificarIdClienteExistencia(idCliente, nombreCliente) == false) { //Verificar el ID del cliente, si es falso significa que el nombre y el ID no coincide con alguna existencia.
                if( false == verificarNumeroCuenta(idCliente, idCuenta)) { // Aquí validamos si el número de cuenta del cliente ya existe, en dado caso exista no se registra
                    
                    Cuenta cuenta = new Cuenta(idCuenta, saldo);
                    Cliente cliente = new Cliente(idCliente, nombreCliente, cuenta);

                    this.listaClientes.agregarCliente(cliente);

                    daoFichero.agregarCuenta(cliente);
                    
                }
                
            }
     
        }
    }


    public void borrarCliente(String nombreCliente,  String idCliente) throws ExcepcionCliente{
        Cliente cliente = new Cliente();

        cliente = traerDatosCliente(idCliente);

        if( nombreCliente.equals(cliente.getNombre()) && idCliente.equals(cliente.getIdCliente())) {
            this.listaClientes.eliminar(idCliente);
            daoFichero.borrarCliente(idCliente);
            
        } else {
            throw new ExcepcionCliente("ID del cliente y el nombre no coinciden");
        }

    }

    //Borrar una cuenta del cliente
    public void borrarCuentaCliente(String nombreCliente,  String idCliente, String idCuenta ) throws ExcepcionCuenta, ExcepcionCliente {
        if(controladorCuenta.borrarCuenta(nombreCliente, idCliente, idCuenta) == false) {
            System.out.println("Usted solamente tiene una cuenta, su usuario será borrado, bye, bye...");
            daoFichero.borrarCliente(idCliente);
            this.listaClientes.eliminar(idCliente);
        }
    }

    //Depositar dinero en cuenta
    public void depositarDineroCuenta(String nombreCliente,  String idCliente, String idCuenta, double deposito) throws ExcepcionCuenta, ExcepcionCliente, IOException {
        
        controladorCuenta.depositarSaldoCuenta(nombreCliente, idCliente, idCuenta, deposito);

    }

    //Retirar dinero en cuenta
    public void retirarDineroCuenta(String nombreCliente,  String idCliente, String idCuenta, double retiro) throws ExcepcionCuenta, ExcepcionCliente, IOException {
        
        controladorCuenta.retirarSaldoCuenta(nombreCliente, idCliente, idCuenta, retiro);
    }

    //Cambiar nombre de cliente
    public void cambiarNombreCliente(String nombreCliente,  String idCliente ) throws ExcepcionCliente {
        validarNombreCliente(nombreCliente); //Validar el nombre que se desea agregar como modificacion

        listaClientes.getCliente(idCliente).setNombre(nombreCliente);

        daoFichero.actualizarCliente(listaClientes.getCliente(idCliente));
        
    }



    //Validar datos
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

    public boolean verificarNumeroCuenta (String idCliente, String idCuenta) throws ExcepcionCuenta, ExcepcionCliente {

        int contador = 0;

        cliente = traerDatosCliente(idCliente); 
        ArrayList<String> listaNumerosCuentas = new ArrayList<String>();

        while( contador < cliente.getCuentas().size() ) {
            listaNumerosCuentas.add(cliente.getCuentas().getListaCuentas().get(contador).getIdCuenta()); //Llenamos la lista con los numeros de las cuentas del cliente
            contador++;
        }

        if(listaNumerosCuentas.contains(idCuenta) ) { //Si es verdad entonces esa cuenta existe.
            throw new ExcepcionCuenta("El número de cuenta ya existe en tu usuario");
        }
        
        return false;
    }



    public void imprimirClientes( ) {
        int contadorClientes = 0;
        ListaDeClientes listaClientes = new ListaDeClientes();
       
        while(contadorClientes < listaClientes.size()) {
            System.out.println(listaClientes.getListaClientes().get(contadorClientes).imprimirCliente()); 
            contadorClientes++;
        }
                

    }


}
