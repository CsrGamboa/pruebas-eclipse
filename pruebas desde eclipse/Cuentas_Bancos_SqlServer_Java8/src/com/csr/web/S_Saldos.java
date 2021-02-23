package com.csr.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csr.persistencia.AccesoCuentasBancarias;

/**
 * Servlet implementation class S_Saldos
 */
@WebServlet("/S_Saldos")
public class S_Saldos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_Saldos() {
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
		String mensaje ="";
		double respuesta = 0;
		String tipoSaldo = request.getParameter("TipoSaldo");
		String idTexto = request.getParameter("id");
		int id;
		// Llamar a capa de persistencia
		AccesoCuentasBancarias ac1 = new AccesoCuentasBancarias();
		// Verificamos se haya elegido algun tipo Movimiento
		if (tipoSaldo != null && !idTexto.equals("")) {
			try {
				// Verificar que tipo de Consulta hacer
				if (tipoSaldo.equals("SaldoCuenta")){
					// convertir e inicializar variables
					id = Integer.parseInt(idTexto);
					// verificamos si existe este registro
					if (ac1.verificarExisteCuenta(id)) {
						respuesta = ac1.consultaSaldo(id);
						// asignar y enviar mediante Request al JSP
						mensaje = String.format ("Su saldo es: %.2f", respuesta);
					} else {
						mensaje = "Error, no existe la cuenta numero:  " + id;
					}
					request.setAttribute("jspMensaje", mensaje);
					//request.setAttribute("jspRespuesta", respuesta);
					RequestDispatcher rd = request.getRequestDispatcher("/saldos.jsp");
					rd.forward(request, response); 
				} else if (tipoSaldo.equals("CuentaInversion")){
					// convertir e inicializar variables
					id = Integer.parseInt(idTexto);
					// verificamos si existe este registro
					if (ac1.verificarExisteCDT(id)) {
						respuesta = ac1.consultaSaldoCDT(id);
						// asignar y enviar mediante Request al JSP
						mensaje = String.format ("Su saldo es: %.2f", respuesta);
					} else {
						mensaje = "Error, no existe la cuenta numero:  " + id;
					}
					request.setAttribute("jspMensaje", mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/saldos.jsp");
					rd.forward(request, response); 
				} else if (tipoSaldo.equals("SaldoCliente")){ 
					// verificamos si existe este registro
					if (ac1.verificarExisteCliente(idTexto)) {
						respuesta = ac1.consultaSaldoTotal(idTexto);
						// asignar y enviar mediante Request al JSP
						mensaje = String.format ("Su saldo es: %.2f", respuesta);
					} else {
						mensaje = "Error, no existe el cliente con nif:  " + idTexto;
					}
					request.setAttribute("jspMensaje", mensaje);
					RequestDispatcher rd = request.getRequestDispatcher("/saldos.jsp");
					rd.forward(request, response);
				}
			} catch ( ClassNotFoundException | SQLException  e ) {
				mensaje = "Error conexion a BBDD, no se ha realizado la consulta";
				request.setAttribute("jspMensaje", mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/saldos.jsp"); 
				rd.forward(request, response); 
			} catch ( NumberFormatException e ) {
				mensaje = "La cuenta tiene que ser numérica"; 
				request.setAttribute("jspMensaje", mensaje);
				RequestDispatcher rd = request.getRequestDispatcher("/saldos.jsp"); 
				rd.forward(request, response); 
			}
		} else { 
			mensaje = "Seleccionar tipo de Consulta e ingrese numero";
			request.setAttribute("jspMensaje", mensaje);
			RequestDispatcher rd = request.getRequestDispatcher("/saldos.jsp"); 
			rd.forward(request, response);
		}
		
	}

}
