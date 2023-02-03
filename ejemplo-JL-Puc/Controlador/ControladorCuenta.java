package Controlador;


import java.io.IOException;

import DAO.DaoFichero;
import Exceptions.ExcepcionCliente;
import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.ListaDeClientes;

public class ControladorCuenta {
    Cliente cliente = new Cliente();
    ControladorCliente controladorCliente;
    ListaDeClientes listaClientes = new ListaDeClientes();
    DaoFichero daoFichero = new DaoFichero();

    public ControladorCuenta( ListaDeClientes listaClientes ) {
        this.listaClientes = listaClientes;
    }


    public boolean borrarCuenta(String nombre, String idCliente, String idCuenta) throws ExcepcionCuenta, ExcepcionCliente{

        if(this.listaClientes.getCliente(idCliente).getCuentas().size() != 1 ) {

            if(Double.parseDouble(this.listaClientes.getCliente(idCliente).getCuentas().getCuenta(idCuenta).getSaldo()) == 0 ) {//Preguntar si el saldo de la cuenta es igual a 0, solo de esa manera se podrá borrar una cuenta.

                this.listaClientes.getCliente(idCliente).getCuentas().eliminarCuenta(idCuenta); //Eliminar el cliente para sobreescribirlo con la 
                daoFichero.actualizarCliente( this.listaClientes.getCliente(idCliente));
                System.out.println("Cuenta borrada exitosamente");
                return true;
            } else {
                throw new ExcepcionCuenta("La cuenta que quiere borrar aun tiene dinero");
            }

        } else {
            return false;

        }

        
    }

    public boolean depositarSaldoCuenta(String nombreCliente, String idCliente, String idCuenta, double deposito ) throws ExcepcionCuenta, ExcepcionCliente, IOException {

        if(deposito > 0) {
            this.listaClientes.getCliente(idCliente).getCuentas().getCuenta(idCuenta).depositarSaldo(deposito);

            cliente = this.listaClientes.getCliente(idCliente);
            daoFichero.actualizarCliente(cliente);

            return true;
        }

        throw new ExcepcionCuenta("Ingrese un deposito mayor que 0");

    }

    public boolean retirarSaldoCuenta(String nombreCliente, String idCliente, String idCuenta, double retiro ) throws ExcepcionCuenta, ExcepcionCliente, IOException {

        this.listaClientes.getCliente(idCliente).getCuentas().getCuenta(idCuenta).retirarSaldo(retiro);

        cliente = this.listaClientes.getCliente(idCliente);
        daoFichero.actualizarCliente(cliente);

         return true;
       
    }









}
