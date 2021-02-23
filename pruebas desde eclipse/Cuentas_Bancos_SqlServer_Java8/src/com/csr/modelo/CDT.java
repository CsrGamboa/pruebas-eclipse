package com.csr.modelo;

import java.time.LocalDate;

public class CDT {

	private int idInversion;
    private int idCuenta;
    private LocalDate fechaApertura;
    private double interesMensuales;
    private double valorInversion;
    private String estado;

    @Override
    public String toString ()
    {
        return "CDT{" + "idInversion=" + idInversion + ", idCuenta=" + idCuenta + ", fechaApertura=" + fechaApertura + ", interesMensuales=" + interesMensuales + ", valorInversion=" + valorInversion + ", estado=" + estado + '}' + '\n';
    }

  
        public CDT ()
    {
    }

     public CDT ( int idCuenta, double interesMensuales, double valorInversion )
    {
        this.idCuenta = idCuenta;
        this.interesMensuales = interesMensuales;
        this.valorInversion = valorInversion;
    }

    public CDT ( int idInversion, int idCuenta, LocalDate fechaApertura, double interesMensuales, double valorInversion, String estado )
    {
        this.idInversion = idInversion;
        this.idCuenta = idCuenta;
        this.fechaApertura = fechaApertura;
        this.interesMensuales = interesMensuales;
        this.valorInversion = valorInversion;
        this.estado = estado;
    }

    public int getIdInversion ()
    {
        return idInversion;
    }

    public void setIdInversion ( int idInversion )
    {
        this.idInversion = idInversion;
    }

    public int getIdCuenta ()
    {
        return idCuenta;
    }

    public void setIdCuenta ( int idCuenta )
    {
        this.idCuenta = idCuenta;
    }

    public LocalDate getFechaApertura ()
    {
        return fechaApertura;
    }

    public void setFechaApertura ( LocalDate fechaApertura )
    {
        this.fechaApertura = fechaApertura;
    }

    public double getInteresMensuales ()
    {
        return interesMensuales;
    }

    public void setInteresMensuales ( double interesMensuales )
    {
        this.interesMensuales = interesMensuales;
    }

    public double getValorInversion ()
    {
        return valorInversion;
    }

    public void setValorInversion ( double valorInversion )
    {
        this.valorInversion = valorInversion;
    }

    public String getEstado ()
    {
        return estado;
    }

    public void setEstado ( String estado )
    {
        this.estado = estado;
    }
}
