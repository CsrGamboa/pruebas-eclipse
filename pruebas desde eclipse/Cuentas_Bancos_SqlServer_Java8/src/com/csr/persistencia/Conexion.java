package com.csr.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	protected Connection miConexion;
	 public void abrirConexion() throws ClassNotFoundException, SQLException{
	
		 //nombre del driver
		 //Class.forName("com.mysql.cj.jdbc.Driver");
		 Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	  

		 //String stringConexion =  "jdbc:mysql://localhost:3306/cuentas_bancos?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false";
		 String stringConexion =  "jdbc:sqlserver://localhost:1433;" +
                 "databaseName=Cuentas_Bancos;" +
                 "user=sa;" +
                 "password=prueba1;" +
                 "encrypt=false;" +
                 "trustServerCertificate=false;" +
                 "loginTimeout=30;";

	  
		 //Obtener un objeto de tipo conexion
		 //miConexion = DriverManager.getConnection(stringConexion, "root", "root");
		 miConexion = DriverManager.getConnection(stringConexion);
		 
// 	  miConexion = DriverManager.getConnection
//	  ("jdbc:mysql://localhost:3306/salvadorebooks?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&autoReconnect=true&useSSL=false",
//	                "root", "root");
		 System.out.println("\nExito al abrir la conexion");
	 }
	 public void cerrarConexion() throws SQLException {
	  miConexion.close();
	 }

	
}
