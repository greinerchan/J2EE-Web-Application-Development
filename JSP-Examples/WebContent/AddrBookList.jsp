<!DOCTYPE html>
<html>
	<head>
        <meta charset="utf-8"/>
		<title>AddrBook Lookup Example</title>
		<link rel="stylesheet" href="addrbook.css">
	</head>
	<body>
		<%@ page import="databeans.Entry" %>
		<% Entry[] list = (Entry[]) request.getAttribute("list"); %>

		<jsp:include page="AddrBookTemplate.html" />

		<div class="sub-title">Found <%=list.length%> entries:</h3>
		
		<table>
<%
      for (int i=0; i<list.length; i++) {
          Entry e = list[i];
%>
			<tr>
				<td>
					<a href="idsearch?id=<%=e.getId()%>">
						<%=e.getLastName()%>, <%=e.getFirstNames()%>
					</a>
				</td>
			</tr>
<%
      }
%>
		</table>
	</body>
</html>
