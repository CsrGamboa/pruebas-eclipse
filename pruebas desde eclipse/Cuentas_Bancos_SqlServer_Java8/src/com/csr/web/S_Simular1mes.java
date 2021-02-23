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
 * Servlet implementation class S_Simular1mes
 */
@WebServlet("/S_Simular1mes")
public class S_Simular1mes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S_Simular1mes() {
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
		//1.Declarar variables
        String textNIF;
        AccesoCuentasBancarias ac1= new AccesoCuentasBancarias();
        String mensaje;
        double respuesta;
        
        //2.Recoger valores
        textNIF = request.getParameter("Nif");
        try
        {
        	//if (!textNIF.equals("")) {
        	if (ac1.verificarVacio(textNIF)) {
        		if (ac1.verificarExisteCliente(textNIF)) {
            		respuesta = ac1.simulacion (textNIF);
            		mensaje = String.format ("Su saldo es: %.2f", respuesta );
            	} else {
            		mensaje = "Error, no existe el cliente con nif:  " + textNIF;
            	}
        	} else {
        		mensaje = "No ha introducido el NIF";
        	}
            request.setAttribute("jspMensaje", mensaje);
			RequestDispatcher rd = request.getRequestDispatcher("/simulacion.jsp"); 
			rd.forward(request, response);
        }
        catch ( ClassNotFoundException | SQLException ex ){
	        mensaje = "Error conexion Base de Datos";
			request.setAttribute("jspMensaje", mensaje);
			RequestDispatcher rd = request.getRequestDispatcher("/simulacion.jsp"); 
			rd.forward(request, response); 
        }
	}

}
