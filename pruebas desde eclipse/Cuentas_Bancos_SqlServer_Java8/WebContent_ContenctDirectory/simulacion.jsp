<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Simulacion</title>
<link rel="stylesheet" href="estilos/estilos.css" />
</head>
<body>
	<%@ include file="cabecera.jsp" %>
	<p class="subtitulo">Simulacion ganancias 1 mes</p>
	<div>
		<form action="S_Simular1mes" method="post">
			<table class="tablePeq">
				<tr>
					<td class="derecha">NIF:</td>
					<td><input class= "input" type="text" name="Nif" /></td>
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