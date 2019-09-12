<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% response.setHeader("Content-Type","application/json"); %>

[
    <c:forEach var="item" items="${items}">
		<c:if test="${ item.id != items[0].id }">
			,
		</c:if>
	    {
		   "id":        "${item.id}",
		   "text":      "<c:out value='${item.item}'/>",
		   "ip_addr":   "${item.ipAddress}",
		   "user_name": "${item.userName}"
		}
    </c:forEach>
]
