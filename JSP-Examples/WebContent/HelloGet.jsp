<%@page pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
	<meta charset="UTF-8">
    <title>HelloGet JSP Example</title>
</head>
<body>
<% 
    String name = request.getParameter("name");
    if (name == null) {
%>
    <form method="GET" action="HelloGet.jsp">
        <font size="+2">What's your name?</font><br/>
        <input type="text" name="name" /><br/>
        <input type="submit" />
    </form>
<%
    } else {
        out.println("<h2>Hello, " + name + "!</h2>");
    }
%>
</body></html>
