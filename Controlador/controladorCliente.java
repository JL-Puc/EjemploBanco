package Controlador;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import DAO.DaoFichero;
import Exceptions.NameWrong;
import Model.Cliente;
import Model.Cuenta;

public class ControladorCliente {
    
    private Cliente cliente;
    private Cuenta cuenta;
    private DaoFichero daoFichero;
    Pattern p;
    Matcher m;

    public boolean validaNombreCliente( String nombre) throws NameWrong {

        p = Pattern.compile("^([A-Z]{1}[a-z]+)+$");
        m = p.matcher(nombre);
        if(m.matches()) {
            return true;
        } else {
            throw new NameWrong("Nombre incorrecto (El nombre comienza con Mayúscula y no contiene número o signos especiales)");
        }
    }

    public boolean validarIdCliente( String idCliente) {
        p = Pattern.compile("^[0-9]{5}$");
        m = p.matcher(idCliente);
        if(m.matches()) {
            return true;
        } else {
            throw new IdCustomerIncorrect("El número de ID debe contener exactamente 10 digítos");
        }

    }




}
