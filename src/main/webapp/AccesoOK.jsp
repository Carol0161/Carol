<%@page import="org.apache.el.parser.BooleanNode"%>
<%@page import="javax.sound.sampled.TargetDataLine"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="CSS/style.css">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
		<h1>
			BIENVENIDO
			<s:property value="nombre" />
			<s:property value="datosAnyadidos" />

		</h1>
		<H2>El acceso ha sido correcto</h2>
		<form action="guardarDatos" method="post">
			<div id="contenedor2">

				<table border="0">
					<tbody>
						<tr>
							<s:textfield label="APELLIDO" name="apellido" />
						</tr>

						<tr>
							<s:radio label="GENERO" name="genero"
								list="#{'1':'Masculino','2':'Femenino'}" value="1" />
						</tr>
						<tr>
							<td><s:select label="CICLO" name="ciclo" headerKey="-1"
									headerValue="Elige ciclo" list="#{'01':'DAM', '02':'DAW'}"
									required="true" /></td>
							<td><s:select label="CURSO" name="curso" headerKey="-1"
									headerValue="Elige curso" list="#{'01':'1º', '02':'2º'}"
									required="true" /></td>
							<td><s:select label="MODULO" name="modulo" headerKey="-1"
									headerValue="Elige modulo"
									list="#{'01':'ACCESO DATOS', '02':'PROG.MULTIMEDIA', '03':'SERVICIOS Y PROCESOS'}"
									required="true" /></td>
						</tr>
						<tr>
							<s:textarea label="OTROS" name="observaciones" />
						<tr>
							<s:submit label="Guardar" value="Guardar" />
						</tr>
					</tbody>
				</table>
			</div>
			<div>

				<%
				String ok = (String) request.getSession().getAttribute("datosAnyadidos");

				if (ok != null && ok.trim().equals("OK")) {
				%>
				<H2>
					Se han guardado los datos correctamente del usuario
					<%=request.getSession().getAttribute("usuario")%></h2>
				<%
				}
				%>
			</div>
		</form>
	</div>
</body>
</html>