<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu Principal</title>
<link rel="stylesheet" href="estilos/estilos.css" />
</head>
<body>
	<header>
		<div class="cajaConcepto"> <a class="txtLogo" href="index.jsp">
			<img  class="redondo" alt="dientecitos" src="imagenes/Ragondin.jfif" alt="ragondin"/> 
			<span>Banco El Diezmo</span></a >
		</div>
	</header>
		<p class="subtitulo">Opciones Principales</p>
	
		<input type="button" onclick="location.href='saldos.jsp'" value="Saldo"/>
		<input type="button" onclick="location.href='gestionarCDT.jsp'" value="Gestionar CDT"/>
		<input type="button" onclick="location.href='movimientos.jsp'" value="Ingresar / Retirar"/>
<!-- 		<input type="button" onclick="location.href='simulacion.jsp'" value="Simular 1 mes"/> -->
</body>
</html>