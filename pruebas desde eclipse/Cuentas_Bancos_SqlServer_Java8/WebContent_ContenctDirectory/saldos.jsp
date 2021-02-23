
<%@page import="com.csr.negocio.Cajero"%>
<%@page import="com.csr.persistencia.AccesoCuentasBancarias"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Saldos</title>
<link rel="stylesheet" href="estilos/estilos.css" />
</head>
<body>
	<%@ include file="cabecera.jsp" %>
	<p class="subtitulo">Visualizar Saldos</p>
	<div>
		<form action="S_Saldos" method="post">
			<table class="table">
				<tr>
					<td width="20%">
						<p class="radio">
					   		<input type="radio" name="TipoSaldo" value="SaldoCuenta"/>
					   </p>
					</td>
					<td width="20%">
						<p>Saldo Cuenta</p>
					</td>
					
				</tr>
				<tr>
					<td>
					<p class="radio">
					   <input type="radio" name="TipoSaldo" value="CuentaInversion"/>
					   </p>
					   </td>
					   <td width="20%">
					    <p>Cuenta Inversion</p>
					</td>
					<td width="20%" class="derecha">Numero :</td>
					<td width="20%"><input class= "input" type="text" name="id"/></td>
				</tr>
				<tr>
					<td>
					<p class="radio">
					   <input type="radio" name="TipoSaldo" value="SaldoCliente"/>
					   </p>
					</td>
					<td width="20%">
					    <p>Saldo Cliente</p>
					</td>
				</tr>
		</table>
		<table class="table">
				<tr>
					<td ><input type="submit" value="Consultar"/></td>
<%-- 					<td width="80%"><span class="respuesta"> <%=request.getAttribute("jspMensaje") %></span></td> --%>
					<td width="80%"><span class="respuesta">${jspMensaje}</span></td>
				</tr>
		</table>
		</form>
	</div>
</body>
</html>