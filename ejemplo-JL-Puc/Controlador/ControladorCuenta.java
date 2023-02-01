package Controlador;


import Exceptions.ExcepcionCuenta;
import Model.Cliente;
import Model.Cuenta;
import Model.ListaDeClientes;

public class ControladorCuenta {
    Cliente cliente = new Cliente();
    ControladorCliente controladorCliente;
    ListaDeClientes listaClientes = new ListaDeClientes();

    public ControladorCuenta( ListaDeClientes listaClientes ) {
        this.listaClientes = listaClientes;
    }


    public boolean borrarCuenta(String nombre, String idCliente, String idCuenta, Cliente cliente) throws ExcepcionCuenta{

        Cuenta cuenta = cuentaEspecífica(cliente, nombre, idCuenta);

         if(Integer.parseInt(cuenta.getSaldo()) == 0 ) {//Preguntar si el saldo de la cuenta es igual a 0, solo de esa manera se podrá borrar una cuenta.

            this.listaClientes.eliminar(cliente); //Eliminar el cliente para sobreescribirlo con la cuenta borrada
            cliente.getCuentas().remove(cuenta);

            this.listaClientes.agregarCliente(cliente);
            System.out.println("Cuenta borrada exitosamente");
            return true;
        } else {
            throw new ExcepcionCuenta("La cuenta que quiere borrar aun tiene dinero");
        }

        
    }


    public Cuenta cuentaEspecífica(Cliente cliente, String nombreCliente, String idCuenta) throws ExcepcionCuenta {
        int contador = 0;

        if( cliente.getNombre().equals(nombreCliente)) {

            while( contador < cliente.getCuentas().size()) {
                if(cliente.getCuentas().get(contador).getIdCuenta().equals(idCuenta)) {//Recorrer todas las cuentas del cliente para encontrar la coincidencia del número de cuenta
                    Cuenta cuenta = cliente.getCuentas().get(contador);
                    return cuenta;
                }
                contador++;
            }

            throw new ExcepcionCuenta("La cuenta ingresada no coincide con alguna existencia en su usuario");
            
        } else {
            throw new ExcepcionCuenta("El ID y su nombre no coinciden con alguna existencia");
        }

    }

    public boolean depositarSaldoCuenta(Cliente cliente, String nombreCliente, String idCuenta, double deposito ) throws ExcepcionCuenta {

        Cuenta cuenta = cuentaEspecífica(cliente, nombreCliente, idCuenta);

        if(deposito > 0) {
            cuenta.depositarSaldo(deposito);
            return true;
        }

        throw new ExcepcionCuenta("Ingrese un deposito mayor que 0");

    }








}
