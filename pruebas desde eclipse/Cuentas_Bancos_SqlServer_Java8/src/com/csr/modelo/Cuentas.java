package com.csr.modelo;

public class Cuentas {
	private int idCuenta;
	private double saldo;
	private String nif;
	private String estado;
	
	@Override
	public String toString() {
		return "Cuentas [idCuenta=" + idCuenta + ", saldo=" + saldo + ", nif=" + nif + ", estado=" + estado + "]";
	}
	
	public Cuentas() {
		// TODO Auto-generated constructor stub
	}

	public Cuentas(int idCuenta, double saldo, String nif, String estado) {
		super();
		this.idCuenta = idCuenta;
		this.saldo = saldo;
		this.nif = nif;
		this.estado = estado;
	}

	public int getIdCuenta() {
		return idCuenta;
	}

	public void setIdCuenta(int idCuenta) {
		this.idCuenta = idCuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	

}
