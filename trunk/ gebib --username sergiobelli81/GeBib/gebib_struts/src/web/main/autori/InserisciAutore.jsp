<%@ page language="java" import="java.util.*" %>

<jsp:useBean id="InserisciAutoreForm" scope="session" class="eu.sergiobelli.gebib.autori.ctrl.form.InserisciAutoreForm"/>

<%@ taglib uri="../../WEB-INF/struts.tld" prefix="struts" %>
<%@ taglib uri="../../WEB-INF/struts-form.tld" prefix="form" %>
<%@ taglib uri="../../WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="../../WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="../../WEB-INF/struts-logic.tld" prefix="logic" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
	
	<head>
	<link rel=stylesheet href="../../css/main.css" type="text/css">
	<title>Inserisci Autore</title>
	
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	</head>
	
	<body>
	
		<html:errors property="cambia"/> 
		
		<struts:form action="inserisciAutore.do" name="inserisciAutoreForm" type="eu.sergiobelli.gebib.autori.ctrl.form.InserisciAutoreForm">
	
			<h1>Modulo Inserimento Autore</h1>
		
			<div ID="layer0">
				
				<table border="0" width="87%">
		    		<tr>
		      			<td colspan="4">Nome</td>
		      			<td colspan="4"><form:text name="nome" property="nome"/></td>
		    		</tr>
		    		<tr>
		      			<td colspan="4">Cognome</td>
		      			<td colspan="4"><form:text name="cognome" property="cognome"/></td>
		    		</tr>
		    		
				</table>
			
			</div>
			
		</struts:form>
	
	</body>

</html>
