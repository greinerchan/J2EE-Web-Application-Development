<%@page import="java.util.List"%>
<%@page import="edu.cmu.cs.webapp.todolist4.databean.ItemBean"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="todolist.css">
    <title>To Do List #4</title>
</head>

<body>

	<div class="title">To Do List #4</div>
<%
	List<String> errors = (List<String>) request.getAttribute("errors");
	if (errors != null) {
		for (String error : errors) {
%>		
			<p class="error"> <%= error %> </p>
<%
		}
	}
%>	

    <form class="add-form" method="POST" action="ToDoList">
        <table>
            <tr>
                <td>
                    <label for="item">Item to add:</label>
                    </td>
                <td>
                    <input type="text" id="item" name="item" autofocus>
                </td>
            </tr>
            <tr>
                    <td></td>
                <td>
                        <button type="submit" name="action" value="top">Add to Top</button>
                        <button type="submit" name="action" value="bottom">Add to Bottom</button>
                </td>
            </tr>
        </table>
    </form>
        
<%
	ItemBean[] items = (ItemBean[]) request.getAttribute("items");
%>
    <div class="sub-title"> Current todo list has <%= items.length %> items: </div>

    <ol>
<%
        for (int i = 0; i < items.length; i++) {
            ItemBean item = items[i];
%>
            <li>
                <form class="delete-form" method="POST" action="Delete">
                    <input type="hidden" name="id" value="<%= item.getId() %>">
                    <button type="submit">X</button>
                </form>
                <%= item.getItem().replace("&","&amp;")
                            .replace("<","&lt;")
                            .replace(">","&gt;")
                            .replace("\"","&quot;") %>
                <span class="details">
                       (uniqueId = <%= item.getId() %>,
                        user = <%= item.getUserName() %>,
                        ipAddr = <%= item.getIpAddress() %>)
                </span>
            </li>
<%
        }
%>
    </ol>

    <form method="POST" action="Logout">
        <button type="submit">Logout</button>
    </form>
</body>
</html>