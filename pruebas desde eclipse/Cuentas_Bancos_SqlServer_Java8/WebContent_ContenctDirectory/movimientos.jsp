<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Movimientos</title>
<link rel="stylesheet" href="estilos/estilos.css" />
</head>
<body>
	<%@ include file="cabecera.jsp" %>
	<p class="subtitulo">Ingresar / Retirar</p> 
	<div>
		<form action="S_Movimientos" method="post">
			<table>
				<tr>
					<td width="20%">
						<p class="radio">
					   <input type="radio" name="TipoMovimiento" value="Ingresar"/>
					   </p>
					</td>
					<td width="20%">
						<p>Ingresar</p>
					</td>
					<td width="20%">
						<p class="radio">
					   <input class="derecha" type="radio" name="TipoMovimiento" value="Retirar"/>
					   </p>
					</td>
					<td width="20%">
						<p>Retirar</p>
					</td>
				</tr>
			</table>
			<table class="tablePeq">
				<tr>
					<td class="derecha">Numero Cuenta:</td>
					<td><input class= "input" type="text" name="Cuenta" value=""/></td>
					
				</tr>
				<tr>
					<td class="derecha">Monto:</td>
					<td><input class= "input" type="text" name="Monto" value=""/></td>
				</tr>
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