package com.csr.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csr.modelo.Movimiento;
import com.csr.persistencia.AccesoCuentasBancarias;

/**
 * Servlet implementation class S_Movimientos
 */
@WebServlet("/S_Movimientos")
public class S_Movimientos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_Movimientos() {
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
		String tipoMovimiento  = request.getParameter("TipoMovimiento");
		String cuentaTexto = request.getParameter("Cuenta");
		String montoTexto = request.getParameter("Monto");
        boolean exito = false;
        int cuenta;
        double monto;
        Movimiento m1;
    	// Llamar a capa de persistencia
        AccesoCuentasBancarias ac1 = new AccesoCuentasBancarias();
        // Verificamos se haya elegido algun tipo Movimiento
        if (ac1.verificarVacio(tipoMovimiento)) {
	        try {
	        	// 2. Recoger los valores de los controles
	            cuenta = Integer.parseInt(cuentaTexto);
	            monto = Double.parseDouble(montoTexto);
	            if (ac1.verificarExisteCuenta(cuenta)) {
		            // 3. Realizar la Acción
		            m1 = new Movimiento(monto, cuenta);
		            if (tipoMovimiento.equals("Ingresar")) {
		                exito = ac1.ingreso(m1);
		            } else if (tipoMovimiento.equals("Retirar")) {
		                exito = ac1.retiro(m1);
		            }
		            if ( exito ) {
		                mensaje = "Éxito al realizar la acción"; 
		            } else {
		            	mensaje = "Error no se ha podido realizar la acción";
		            }
	            } else {
	            	mensaje = "Error, no existe la cuenta numero:  " + cuenta;
	            }
				request.setAttribute("jspMensaje", mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/movimientos.jsp"); 
				rd.forward(request, response); 
	        } catch (ClassNotFoundException | SQLException ex) {
	            mensaje = "Error conexion a BBDD. No se ha realizado la acción"; 
				request.setAttribute("jspMensaje", mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/movimientos.jsp"); 
				rd.forward(request, response); 
	        } catch (NumberFormatException ex) {
	            mensaje = "Todos los datos deben ser numéricos"; 
				request.setAttribute("jspMensaje", mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/movimientos.jsp"); 
				rd.forward(request, response); 
	        }
        } else {
        	mensaje = "Seleccionar tipo de Movimiento"; 
			request.setAttribute("jspMensaje", mensaje);
			RequestDispatcher rd = request.getRequestDispatcher("/movimientos.jsp"); 
			rd.forward(request, response); 
        }
        
	}

}
