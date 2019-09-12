<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
        <meta charset="utf-8">
		<title>AddrBook</title>
		<link href="search-form.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="search-suggest.js"> </script>
	</head>

	
	<body onLoad="document.getElementById('userName').focus()">

		<jsp:include page="search-form.jsp"/>

		<c:choose>
			<c:when test="${(empty user)}">
				<h2>Create Initial User</h2>
			</c:when>
			<c:otherwise>
				<h2>Create User</h2>
			</c:otherwise>
		</c:choose>

        <c:forEach var="error" items="${form.formErrors}">
            <h3 style="color:red"> ${error} </h3>
        </c:forEach>
    
		<form action="create-user.do" method="post">
            <table>
                <c:forEach var="field" items="${form.visibleFields}">
                        <tr>
                            <td style="font-size: x-large">
                            <label>${field.label}</label>
                        </td>
                            <td>
                                <input id="${field.name}" type="${field.type}" name="${field.name}" value="${field.value}"/>
                            </td>
                        <td style="color:red">
                            ${field.error}
                        </td>
                        </tr>
                </c:forEach>
                <tr>
                    <td></td>
                    <td><font size="4"><input type="submit" value="Submit" name="Submit" /></font></td>
                </tr>
            </table>
            <c:forEach var="field" items="${form.hiddenFields}">
                <input type="${field.type}" name="${field.name}" value="${field.value}"/>
            </c:forEach>
        </form>

	</body>
</html>
