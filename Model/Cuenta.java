package Model;


public class Cuenta {
    private String idCuenta;
    private String saldo;

    public Cuenta ( String  idCuenta, String saldo) {
        setIdCuenta(idCuenta);
        setSaldo(saldo);
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
