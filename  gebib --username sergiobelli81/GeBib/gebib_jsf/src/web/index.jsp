<%
  final String contextRoot = request.getContextPath();
  System.out.println("contextRoot="+contextRoot);
  
  final String target = contextRoot + "/main/home/home.jsf";
  System.out.println("target="+target);
  
  response.sendRedirect(target);
%>

<html>
	<body>
		<label>CIAO !!! </label>
	</body>
</html>