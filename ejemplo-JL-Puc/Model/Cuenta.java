package Model;

import java.io.Serializable;

import Exceptions.ExcepcionCuenta;

public class Cuenta implements Serializable{
    private String idCuenta;
    private String saldo;

    public Cuenta ( String  idCuenta, String saldo) {
        setIdCuenta(idCuenta);
        setSaldo(saldo);
    }

    public boolean depositarSaldo(double deposito ) {
        double saldoActual = Double.parseDouble(saldo);

        this.saldo = String.valueOf(saldoActual + deposito);
        return true;
    }

    public boolean retirarSaldo(double retiro) throws ExcepcionCuenta{
        double saldoActual = Double.parseDouble(saldo);
        
        if(saldoActual < retiro) {
            throw new ExcepcionCuenta("Saldo insuficiente: " + saldoActual);
        } else {
            this.saldo = String.valueOf(saldoActual - retiro);
            return true;
        }
    }

    public String getIdCuenta() {
        return idCuenta;
    }


    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta; 
    }


    public String getSaldo() {
        return saldo;
    }


    public void setSaldo(String saldo) {
        this.saldo = saldo;  
    }


    

}
