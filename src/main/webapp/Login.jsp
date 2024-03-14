<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="CSS/style.css">
<title>ACCESO BD</title>
</head>
<body>
	<div id="contenedor">
		<s:form action="acceder">
			<s:label>INGRESE SUS DATOS</s:label>
			<s:textfield label="usuario" name="usuario" />
			<s:password label="password" name="password" />
			<s:submit label="Acceder" value="Acceder" />
		</s:form>
	</div>
</body>
</html>