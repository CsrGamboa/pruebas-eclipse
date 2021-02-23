package com.csr.persistencia;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.csr.modelo.CDT;
import com.csr.modelo.Movimiento;

public class AccesoCuentasBancarias extends Conexion{

	//Metodo Consulta Saldo
    public double consultaSaldo(int idCuenta) throws ClassNotFoundException, SQLException
    {
        //1. Declarar variables
    	 CallableStatement cmd;
         ResultSet rs;
         //otra variables
         double saldo = 0;
         String sql = "EXEC CONSULTA_CC ?";
        //2. abrir conexion
         abrirConexion ();
        //3. obtener la sentencia de la conexion
         cmd = miConexion.prepareCall (sql);
        //4. ejecutar la sentencia
         cmd.setInt (1, idCuenta);
         rs = cmd.executeQuery ();
        //5. recoger los datos
         if ( rs.next ())
         {
             saldo = rs.getDouble ("SALDO");
         }
        //6. cerrar la conexion
         cerrarConexion ();
         cmd.close ();
         rs.close ();
        //7. devolver los datos
         return saldo;
    }
    
    //Metodo Consulta Saldo
    public double consultaSaldoCDT (int idInversion) throws ClassNotFoundException, SQLException
    {
        //1. Declarar variables
    	CallableStatement cmd;
        ResultSet rs;
        //otra variables
        double saldo = 0;
        String sql = "EXEC CONSULTA_CDT ?";
        //2. abrir conexion
        abrirConexion ();
        //3. obtener la sentencia de la conexion
        cmd = miConexion.prepareCall (sql);
        //4. ejecutar la sentencia
        cmd.setInt (1, idInversion);
        rs = cmd.executeQuery ();
        //5. recoger los datos
        if ( rs.next ())
        {
            //entre comillas nombre de columna
            saldo = rs.getDouble ("VALORINVERSION");
        }
        //6. cerrar la conexion
        cerrarConexion ();
        cmd.close ();
        rs.close ();
        //7. devolver los datos
        return saldo;
    }
    
    //metodo de consulta saldo total
     public double consultaSaldoTotal (String nif) throws ClassNotFoundException, SQLException
    {
        //1. Declarar variables
    	 CallableStatement cmd;
         //otra variables
         double saldo = 0;
         String sql = "EXEC SALDO_TOTAL ?, ?";
        //2. abrir conexion
         abrirConexion ();
        //3. obtener la sentencia de la conexion
         cmd = miConexion.prepareCall (sql);
        //4. ejecutar la sentencia
         cmd.setString (1, nif);
         cmd.registerOutParameter (2, Types.DOUBLE);
         cmd.execute ();
        //5. recoger los datos
         //RECOGER el valor del parametro de salida
         saldo = cmd.getDouble (2);
        //6. cerrar la conexion
         cerrarConexion ();
         cmd.close ();
        //7. devolver los datos
        return saldo;
    }
    
     //metodo para crear una inversion
     //parametro de entrada -> objeto CDT que contiene los 3 del constructor
     //prametro de salida -> un boolean
     
     public boolean crearInversion (CDT inversion) throws SQLException, ClassNotFoundException
     {
         //1. declarar variables
         //declaracion de JDBC
         CallableStatement sentencia;
         //otras variables
         //cuando ejecuto "comando" devuelve numero de
         //los reglones afectados
         int resultado = 0; 
         String sql = "EXEC ALTA_INVERSION ?, ?, ?;";
         //2. abrir conexion
         abrirConexion ();
         //3. obtener la sentencias de la conexion
         sentencia = miConexion.prepareCall (sql);
         //3a. dar valor a los parametros del Procedimiento Almacenado, la sentencia sql
         //medotos sobrecargados?
         sentencia.setInt (1, inversion.getIdCuenta());
         sentencia.setDouble (2, inversion.getInteresMensuales());
         sentencia.setDouble (3, inversion.getValorInversion());
         //4. ejecutar comando
         //executeUpdate devuelve 1 si se dio de alta, sino 0
         resultado = sentencia.executeUpdate ();
         //5. cerrar la conexion
         cerrarConexion ();
         sentencia.close ();
         //6. devolver el resultado
         return (resultado > 0);
     }
     
     //medoto para cerrar una inversion (punto 6 del ejer en pdf)
     //parametro entrada --> idInversion
     //parametro salida --> exito / no exito
     public boolean cerrarInversion(int idInversion) throws ClassNotFoundException, SQLException
     {
         //1. declarar varibales
         //1a. JDBC
         CallableStatement sentencia;
         // variables
         boolean exito;
         String sql = "EXEC CERRAR_INVERSION ?;";
         //2. abrir conexion
         abrirConexion ();
         //3. recoger la sentencia de la conexion
         sentencia = miConexion.prepareCall (sql);
         //3a. asignar valor a los parametros
         sentencia.setInt (1, idInversion);
         //4. ejecutar la sentencia
         exito = sentencia.execute ();
         //5. cerrar la conexion y la sentencia
         cerrarConexion ();
         sentencia.close ();
         //6. devolver el resultado 
         return exito;
     }
     
     
     //metodos para hacer un ingreso
     //parametro de entrada -> un movimiento con los datos de cuenta y cantidad
     //parametro de salida -> boolean
     public boolean ingreso(Movimiento m1) throws ClassNotFoundException, SQLException
     {
         CallableStatement sentencia;
         int resultado;
         String sql = "EXEC INGRESO ?, ?";
         abrirConexion ();
         sentencia = miConexion.prepareCall (sql);
         sentencia.setInt (1, m1.getIdCuenta());
         sentencia.setDouble (2, m1.getCantidad());
         resultado = sentencia.executeUpdate ();
         cerrarConexion ();
         sentencia.close ();
         return (resultado > 0);
     }
     
     
      //metodos para hacer un retiro (punto 8 del pdf)
     //parametro de entrada -> un movimiento con los datos de cuenta y cantidad
     //parametro de salida -> boolean
     public boolean retiro(Movimiento m1) throws ClassNotFoundException, SQLException
     {
         CallableStatement sentencia;
         int resultado;
         String sql = "EXEC RETIRO ?, ?";
         abrirConexion ();
         sentencia = miConexion.prepareCall (sql);
         sentencia.setInt (1, m1.getIdCuenta());
         sentencia.setDouble (2, m1.getCantidad());
         resultado = sentencia.executeUpdate ();
         cerrarConexion ();
         sentencia.close ();
         return (resultado > 0);
     }
     
     //metodo de consultar simulacion
     public double simulacion (String nif) throws ClassNotFoundException, SQLException
    {
        //1. Declarar variables
    	 CallableStatement cmd;
    	//otra variables
         double simulacion = 0;
         String sql = "EXEC SIMULACION ?, ?";
        //2. abrir conexion
         abrirConexion ();
        //3. obtener la sentencia de la conexion
         cmd = miConexion.prepareCall (sql);
        //4. ejecutar la sentencia
         cmd.setString (1, nif);
         cmd.registerOutParameter (2, Types.DOUBLE);
         cmd.execute ();
        //5. recoger los datos
        //RECOGER el valor del parametro de salida
         simulacion = cmd.getDouble (2);
        //6. cerrar la conexion
         cerrarConexion ();
         cmd.close ();
        //7. devolver los datos
        return simulacion;
    }
     
    //creo este metdo para verificar haya sido introducido un entero
     public boolean verificarNumero(String dato){
         String datoTexto = dato;
         try
         {
            Integer.parseInt (datoTexto);
             return true;
         }
         catch ( NumberFormatException ex )
         {
        	 return false;
         }
         
     }
     
   //creo este metdo para verificar exista datos introducidos
     public boolean verificarVacio(String dato){
	     String datoTexto = dato;
	     boolean respuesta = false;
	     try {
				if (!datoTexto.equals("") && !datoTexto.isEmpty()  && datoTexto != null ) {
	        	 respuesta = true;
	         }
	     }catch (NullPointerException e) {
	    	 respuesta = false;
	     }
	     return respuesta;
         
     }
     
     public boolean verificarExisteCuenta(int idcuenta) throws SQLException, ClassNotFoundException {
    	 CallableStatement cmd;
         ResultSet rs;
         boolean resultado;
         //otra variables
         String sql = "Select 1 from cuentas where idcuenta = " + idcuenta + ";";
        //2. abrir conexion
         abrirConexion ();
        //3. obtener la sentencia de la conexion
         cmd = miConexion.prepareCall (sql);
        //4. ejecutar la sentencia
         rs = cmd.executeQuery ();
        //5. recoger los datos
         if ( rs.next ())
         {
             resultado = true;
         }
         else
         {
        	 resultado = false;
         }
        //6. cerrar la conexion
         cerrarConexion ();
         cmd.close ();
         rs.close ();
        //7. devolver los datos
         return resultado;
     }
     
     public boolean verificarExisteCliente(String nif) throws SQLException, ClassNotFoundException {
    	 CallableStatement cmd;
         ResultSet rs;
         boolean resultado;
         //otra variables
         String sql = "Select 1 from clientes where nif = '" + nif + "';";
        //2. abrir conexion
         abrirConexion ();
        //3. obtener la sentencia de la conexion
         cmd = miConexion.prepareCall (sql);
        //4. ejecutar la sentencia
         rs = cmd.executeQuery ();
        //5. recoger los datos
         if ( rs.next ())
         {
             resultado = true;
         }
         else
         {
        	 resultado = false;
         }
        //6. cerrar la conexion
         cerrarConexion ();
         cmd.close ();
         rs.close ();
        //7. devolver los datos
         return resultado;
     }
     
     public boolean verificarExisteCDT(int idinversion) throws SQLException, ClassNotFoundException {
    	 CallableStatement cmd;
         ResultSet rs;
         boolean resultado;
         //otra variables
         String sql = "Select 1 from cdt where idinversion = " + idinversion + ";";
        //2. abrir conexion
         abrirConexion ();
        //3. obtener la sentencia de la conexion
         cmd = miConexion.prepareCall (sql);
        //4. ejecutar la sentencia
         rs = cmd.executeQuery ();
        //5. recoger los datos
         if ( rs.next ())
         {
             resultado = true;
         }
         else
         {
        	 resultado = false;
         }
        //6. cerrar la conexion
         cerrarConexion ();
         cmd.close ();
         rs.close ();
        //7. devolver los datos
         return resultado;
     }
      
     public boolean verificarCDTabierto(int idinversion) throws SQLException, ClassNotFoundException {
    	 CallableStatement cmd;
         ResultSet rs;
         boolean resultado;
         //otra variables
         String sql = "Select 1 from cdt where idinversion = " + idinversion + " and estadocdt = 'A';";
        //2. abrir conexion
         abrirConexion ();
        //3. obtener la sentencia de la conexion
         cmd = miConexion.prepareCall (sql);
        //4. ejecutar la sentencia
         rs = cmd.executeQuery ();
        //5. recoger los datos
         if ( rs.next ())
         {
             resultado = true;
         }
         else
         {
        	 resultado = false;
        	 System.out.println(resultado);
         }
        //6. cerrar la conexion
         cerrarConexion ();
         cmd.close ();
         rs.close ();
        //7. devolver los datos
         return resultado;
     }
}
