package com.csr.negocio;

import java.sql.SQLException;

import com.csr.persistencia.AccesoCuentasBancarias;

public class Cajero {
	public double consultaSaldo(int idCuenta) throws ClassNotFoundException, SQLException
    {
        AccesoCuentasBancarias acb1 = new AccesoCuentasBancarias();
        return acb1.consultaSaldo (idCuenta);
    }
    
    //consultar saldo CDT
    public double consultaSaldoCDT(int idInversion) throws ClassNotFoundException, SQLException
    {
        AccesoCuentasBancarias acb1 = new AccesoCuentasBancarias();
        return acb1.consultaSaldoCDT (idInversion);
    }
    
    //consultar saldo TOTAL
    public double ConsultaSaldoTotal (String nif) throws ClassNotFoundException, SQLException
    {
        AccesoCuentasBancarias acb1 = new AccesoCuentasBancarias();
        return acb1.consultaSaldoTotal (nif);
    }

}
