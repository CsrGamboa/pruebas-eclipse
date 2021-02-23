package com.csr.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csr.modelo.CDT;
import com.csr.persistencia.AccesoCuentasBancarias;

/**
 * Servlet implementation class S_GestionCDT
 */
@WebServlet("/S_GestionCDT")
public class S_GestionCDT extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_GestionCDT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. Declarar variables y obtener valores
		String mensaje;
		String tipoCDT = request.getParameter("TipoCDT");
		boolean exito;
		int cuenta;
		int CDT;
        double monto;
        double interes;
		CDT inversion;
		// Llamar a capa de persistencia
		AccesoCuentasBancarias ac1 = new AccesoCuentasBancarias ();
		  
	    // identificar si hubo seleccion
	    if (ac1.verificarVacio(tipoCDT)) {
	        try {
	        	// identificar que tipo de accion ejecutar
	        	if (tipoCDT.equals("CrearCDT")) {
	        		exito = false;
	        		//Recoger valores de los controles
		        	cuenta = Integer.parseInt(request.getParameter("Cuenta"));
			    	interes = Double.parseDouble(request.getParameter("Interes"));
			    	monto = Double.parseDouble(request.getParameter("Monto"));
			    	if (ac1.verificarExisteCuenta(cuenta)) {
				    	// Instanciar  con contructor de .modelo -> CDT
			            inversion = new CDT (cuenta, interes, monto);
			            // llamar a creacion del CDT pasando un objeto como parametro
		            	exito = ac1.crearInversion (inversion);
		            	if ( exito ) {
			            	// asignar y enviar mediante Request al JSP
							mensaje = "Se ha realizado el alta de su CDT";
		            	} else {
		            		mensaje = "Error, al momento de ejecutar la acción";
		            	}
			    	} else {
			    		mensaje = "Error, no existe el numero de cuenta: " + cuenta;
			    	}
		    		request.setAttribute("jspMensaje", mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/gestionarCDT.jsp");
				    rd.forward(request, response);
	            } else if (tipoCDT.equals("CerrarCDT")) {
    	            //2. Recoger los datos de los controles
    	        	CDT = Integer.parseInt(request.getParameter("NumeroCDT"));
    	        	// verificamos si existe este registro y ejecuto metodo
     	        	if (ac1.verificarExisteCDT(CDT)){
     	        		if (ac1.verificarCDTabierto(CDT)) {
     	        			//3. Llamar al metodo correspondiente para cerrarla
     	    	        	exito = ac1.cerrarInversion (CDT);
     	        			if ( exito) {
     	        				mensaje = "Se ha realizado la baja de su CDT";
     	        		
		     	        	} else {
		     	        		mensaje = "Error en ejecucion. No se ha realizado la Accion";
		     	        	}
     	        		} else {
     	        			mensaje = "Error, ya estaba cerrado el CDT: " + CDT;
     	        		}
     	        	} else { 
     	        		mensaje = "Error, no existe el CDT: " + CDT;
     	        	}
					request.setAttribute("jspMensaje", mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/gestionarCDT.jsp");
				    rd.forward(request, response);
	            }
	        }catch ( SQLException | ClassNotFoundException ex ) {
	        	mensaje = ("Error conexion a BBDD. No se ha realizado la acción");
				request.setAttribute("jspMensaje", mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/gestionarCDT.jsp");
			    rd.forward(request, response);
	        } catch (NumberFormatException ex) {
	        	mensaje = ("Error. Todos los datos deben ser numéricos");
				request.setAttribute("jspMensaje", mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/gestionarCDT.jsp");
			    rd.forward(request, response);
	        }
	    } else {
	    	mensaje = "Seleccione tipo de gestion";
			request.setAttribute("jspMensaje", mensaje);
			RequestDispatcher rd = request.getRequestDispatcher("/gestionarCDT.jsp");
		    rd.forward(request, response);
	    }
	}

}
