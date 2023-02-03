package Controlador;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import DAO.DaoCuentas;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.Cuenta;
import Model.ListaDeCuentas;

public class ControladorCuenta {
    Cliente cliente = new Cliente();
    ControladorCliente controladorCliente;
    Pattern p;
    Matcher m;

    DaoCuentas daoCuentas = new DaoCuentas();

    public ControladorCuenta( ) {
    }

    public void agregarCuentaNueva(String idCliente, String idCuenta, String saldo) throws ExcepcionCuenta, IOException {

        ListaDeCuentas listaDeCuentas = new ListaDeCuentas();
        Cuenta cuenta = new Cuenta(idCuenta, saldo);
        listaDeCuentas.agregarCuenta(cuenta);

        daoCuentas.agregarCuentas(idCliente, listaDeCuentas);

    }


    public void agregarCuenta(String idCliente, String idCuenta, String saldo) throws ExcepcionCuenta, IOException, ExcepcionCliente {

        ListaDeCuentas listaDeCuentas = daoCuentas.iniciarDatosCuentas(idCliente);

        if( listaDeCuentas == null) {
            Cuenta cuenta = new Cuenta(idCuenta, saldo);
                listaDeCuentas.agregarCuenta(cuenta);
    
                daoCuentas.agregarCuentas(idCliente, listaDeCuentas);
            
        } else {

            if(listaDeCuentas.verificarNumeroCuenta(idCuenta) == false) { //Si es falso significa que no existe la cuenta en el cliente
                Cuenta cuenta = new Cuenta(idCuenta, saldo);
                listaDeCuentas.agregarCuenta(cuenta);
    
                daoCuentas.agregarCuentas(idCliente, listaDeCuentas);
    
            } else{
                throw new ExcepcionCuenta("El numero de cuenta ya existe en tu usuario");
            }
        }
        

    }


    public boolean borrarCuenta(String idCliente, String idCuenta) throws ExcepcionCuenta, ExcepcionCliente, FileNotFoundException{

        ListaDeCuentas listaDeCuentas = daoCuentas.iniciarDatosCuentas(idCliente);

        if(listaDeCuentas.verificarNumeroCuenta(idCuenta)) {

            if( listaDeCuentas.size() != 1) {
                if(Double.parseDouble(listaDeCuentas.getCuenta(idCuenta).getSaldo()) == 0 ) {//Preguntar si el saldo de la cuenta es igual a 0, solo de esa manera se podrá borrar una cuenta.

                    listaDeCuentas.eliminarCuenta(idCuenta);
    
                    daoCuentas.borrarCuenta(idCliente, listaDeCuentas);
                    System.out.println("Cuenta borrada exitosamente");
                    return true;
                } else {
                    throw new ExcepcionCuenta("La cuenta que quiere borrar aun tiene dinero");
                }
            } else {
                return false;
            }
            

        } else {
            throw new ExcepcionCuenta("La cuenta que quiere borrar no existe");

        }

        
    }

    public boolean depositarSaldoCuenta(String idCliente, String idCuenta, double deposito ) throws ExcepcionCuenta, ExcepcionCliente, IOException {

        ListaDeCuentas listaDeCuentas = daoCuentas.iniciarDatosCuentas(idCliente);

        if(deposito > 0) {
            listaDeCuentas.getCuenta(idCuenta).depositarSaldo(deposito);

            daoCuentas.actualizarCuenta(idCliente, listaDeCuentas);
            return true;
        }

        throw new ExcepcionCuenta("Ingrese un deposito mayor que 0");

    }

    public boolean retirarSaldoCuenta(String idCliente, String idCuenta, double retiro ) throws ExcepcionCuenta, ExcepcionCliente, IOException {

        ListaDeCuentas listaDeCuentas = daoCuentas.iniciarDatosCuentas(idCuenta);

        listaDeCuentas.getCuenta(idCuenta).retirarSaldo(retiro);

        
        daoCuentas.actualizarCuenta(idCliente, listaDeCuentas);

         return true;
       
    }



}
