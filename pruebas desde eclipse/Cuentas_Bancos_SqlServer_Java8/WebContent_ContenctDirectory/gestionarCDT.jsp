<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestionar CDT</title>
<link rel="stylesheet" href="estilos/estilos.css" />
</head>
<body>
	<%@ include file="cabecera.jsp" %>
	<p class="subtitulo">Gestionar CDT</p>
	<div>
		<form action="S_GestionCDT" method="post">
			<table class="table">
				<tr>
					<td width="20%">
						<p class="radio">
					   <input type="radio" name="TipoCDT" value="CrearCDT"/>
					   </p>
					</td>
					<td width="20%">
						<p>Crear CDT</p>
					</td>
					<td width="20%">
						<p class="radio">
					   <input type="radio" name="TipoCDT" value="CerrarCDT"/>
					   </p>
					</td>
					<td width="20%">
						<p>Cerrar CDT</p>
					</td>
				</tr>
				
				
				<tr>
					<td class="derecha">Numero Cuenta:</td>
					<td><input class= "input"  type="text" name="Cuenta" value=""/></td>
				</tr>
				<tr>
					<td class="derecha">Interes:</td>
					<td><input class= "input"  type="text" name="Interes" value=""/></td>
					<td class="derecha">Numero CDT:</td>
					<td><input class= "input"  type="text" name="NumeroCDT"/></td>
				</tr>
				<tr>
					<td class="derecha">Monto:</td>
					<td><input class= "input" type="text" name="Monto" value=""/></td>
			</table>
			<table class="table">
					<tr>
						<td ><input type="submit" value="Realizar Acción"/></td>
						<td width="80%"><span class="respuesta derecha">${jspMensaje}</span></td>
<%-- 						<td width="80%"><span class="respuesta derecha"><%=request.getAttribute("jspMensaje") %></span></td> --%>
					</tr>
			</table>
		</form>
	</div>

</body>
</html>