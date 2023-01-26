package Model;

public class Cuenta {
    private String idCuenta;
    private double saldo;


    public Cuenta ( String  idCuenta, double saldo) {
        this.saldo = saldo;
        this.idCuenta = idCuenta;
    }


    public String getIdCuenta() {
        return idCuenta;
    }


    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }


    public double getSaldo() {
        return saldo;
    }


    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    

}
