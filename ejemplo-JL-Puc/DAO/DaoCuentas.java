package DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import Exceptions.ExcepcionCuenta;
import Model.Cuenta;
import Model.ListaDeCuentas;
import Model.SerializarObjeto;

public class DaoCuentas {

    private ArrayList<Cuenta> listaCuentas = new ArrayList<Cuenta>();

    public void agregarCuentas(String idCliente, ListaDeCuentas listaDeCuentas) throws ExcepcionCuenta, IOException{

        SerializarObjeto.serializarObjeto("ejemplo-JL-Puc\\DataCuentas\\" + idCliente + ".txt", listaDeCuentas);
    }

    public ListaDeCuentas iniciarDatosCuentas(String idCliente) throws ExcepcionCuenta {
        ListaDeCuentas listaDeCuentas = SerializarObjeto.deserializarObjeto("ejemplo-JL-Puc\\DataCuentas\\" + idCliente + ".txt", ListaDeCuentas.class);

        if (listaDeCuentas != null ){
            System.out.println("Cargado cuenta del archivo del cliente: " + idCliente);
            return listaDeCuentas;
        } else {
           return listaDeCuentas = null;
        }

    }


    public void borrarCuenta(String idCuenta, ListaDeCuentas listaDeCuentas) throws ExcepcionCuenta {
        actualizarCuenta(idCuenta, listaDeCuentas);
    }

    public void actualizarCuenta(String idCuenta, ListaDeCuentas listaDeCuentas) throws ExcepcionCuenta {
        String path = "ejemplo-JL-Puc\\DataCuentas\\" + idCuenta + ".txt";
        File archivo = new File(path);

        if( archivo.exists()) {
            SerializarObjeto.serializarObjeto(path, listaDeCuentas);
        } else {
            throw new ExcepcionCuenta("Error al actualizar cuenta");
        }
    }

    public ArrayList<ListaDeCuentas> traerCuentasCliente ( ) throws FileNotFoundException, ExcepcionCuenta { //Con un ArrayList traemos todas las cuentas con sus datos 
        File carpeta = new File("ejemplo-JL-Puc\\DataClientes\\"); 

        String[] listaArchivos = carpeta.list();
        ArrayList<ListaDeCuentas> listaDeCuentas = new ArrayList<>();
        ListaDeCuentas cuentas = new ListaDeCuentas();

        for(String string : listaArchivos ) {
            StringTokenizer token = new StringTokenizer(string, ".");
             cuentas = iniciarDatosCuentas(token.nextToken());
            listaDeCuentas.add(cuentas);
        }

        return listaDeCuentas;
    }








}