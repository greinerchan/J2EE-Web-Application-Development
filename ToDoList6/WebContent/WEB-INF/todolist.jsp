<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="todolist.css">
    <title>ToDoList6 </title>
</head>

<body>

    <h2>To Do List #6</h2>
	
    <c:forEach var="error" items="${form.formErrors}">
		<p class="error"> ${error} </p>
    </c:forEach>

    <form class="add-form" method="POST" action="add.do">
        <table>
            <c:forEach var="field" items="${form.visibleFields}">
                    <tr>
                        <td style="font-size: x-large">
                        <label>${field.label}</label>
                    </td>
                        <td>
                            <input type="${field.type}" name="${field.name}" value="${field.value}" autofocus/>
                        </td>
                    <td style="color:red">
                        ${field.error}
                    </td>
                    </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td colspan="2">
                        <button type="submit" name="action" value="top">Add to Top</button>
                        <button type="submit" name="action" value="bottom">Add to Bottom</button>
                </td>
            </tr>
        </table>
    </form>

    <div class="sub-title"> Current todo list has ${ fn:length(items) } items: </div>

    <ol>
        <c:forEach var="item" items="${items}">
            <li>
                <form class="delete-form" method="POST" action="delete.do">
                    <input type="hidden" name="id" value="${ item.id }" />
                    <button type="submit">X</button>
                </form>
	           <c:out value="${ item.item }" />
                <span class="details">
                    (uniqueId = ${ item.id },
                    user = ${ item.userName },
                    ipAddr = ${ item.ipAddress })
                </span>
            </li>
		</c:forEach>
	</ol>


    <form method="POST" action="logout.do">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
