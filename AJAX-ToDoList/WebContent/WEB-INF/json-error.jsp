<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% response.setHeader("Content-Type","application/json"); %>

{
	<c:choose>
		<c:when test="${ ! (empty error) }">
			"error": "<c:out value='${ error }'/>"
		</c:when>
		<c:when test="${ fn:length(form.allErrors) > 0 }">
			"error": "<c:out value='${ form.allErrors[0] }'/>"
		</c:when>
		<c:otherwise>
			"error": "no error passed to json-error.jsp"
		</c:otherwise>
	</c:choose>
}
