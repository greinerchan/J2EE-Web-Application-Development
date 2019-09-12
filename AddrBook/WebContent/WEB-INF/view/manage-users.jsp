<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>AddrBook</title>
		<link href="search-form.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="search-suggest.js"> </script>
	</head>
	
    <body onLoad="document.getElementById('last').focus()">

		<jsp:include page="search-form.jsp"/>

		<h2>Manage Users</h2>

		<jsp:include page="errors-and-messages.jsp"/>

		<table style="padding:3px">
			<c:forEach var="u" items="${userList}">
				<tr>
						<td> <font size="4"> ${u.userName} </font> </td>
					    <td>
                            <form action="manage-users.do" method="post">
                                <input type="hidden" name="userName" value="${u.userName}" />
                                <input type="submit" name="button" value="Set Password" />
            					    <c:if test="${u.userName != user.userName}">
                                    <input type="submit" name="button" value="Delete" />
            					   </c:if>
					       </form>
                       </td>
				</tr>
			</c:forEach>
			<tr>
				<td>&nbsp;  </td>
				<td colspan="2" align="center">
					<form action="create-user.do" method="get">
						<input type="submit" name="button" value="Create New User" />
					</form>
				</td>
			</tr>
		</table>
		
	</body>
</html>
