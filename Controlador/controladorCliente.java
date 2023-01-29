package Controlador;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.DaoFichero;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;

public class ControladorCliente {
    
    Cliente cliente;
    DaoFichero daoFichero;
    Pattern p;
    Matcher m;

    //Constructor
    public ControladorCliente(Cliente cliente, DaoFichero daoFichero) {
        this.cliente = cliente;
        this.daoFichero = daoFichero;
    }


    //Validar datos
    public void crearClienteValido(Cliente cliente ) throws ExcepcionCliente, ExcepcionCuenta, IOException {

        if( validaNombreCliente(cliente.getNombre()) == true && validarIdCliente(cliente.getIdCliente()) == true && validarNumeroCuenta(cliente.ultimaCuentaAgregada().getIdCuenta()) == true && validarSaldoCuenta(cliente.ultimaCuentaAgregada().getSaldo()) == true) {
            
            daoFichero.agregarCuenta(cliente);
            
        }
    }







    //Validamos el nombre del cliente
    public boolean validaNombreCliente( String nombre) throws ExcepcionCliente {

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


}
