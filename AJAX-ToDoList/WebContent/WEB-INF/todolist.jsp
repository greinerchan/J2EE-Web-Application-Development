<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <link rel="stylesheet" href="todolist.css">
    <script src="todolist.js"></script>
    <title>AJAX ToDoList</title>
</head>

<body onload="load()">

    <h2>AJAX To Do List</h2>
	
    <table>
	    <tr>
	        <td>
	            <label for="item">Item to add:</label>
	        </td>
	        <td>
	            <input type="text" id="item" name="item" autofocus>
	        </td>
	        <td>
	        	<span id="error" class="error"></span>
	        </td>
	    </tr>
	    <tr>
	        <td></td>
	        <td>
                <button onclick="addItem('top')">
                	Add to Top
                </button>
                <button onclick="addItem('bottom')">
                    Add to Bottom
                </button>
	        </td>
	        <td></td>
	    </tr>
    </table>
    
    <div id="count" class="sub-title">
    </div>

    <ol id="list">
	</ol>


    <form action="logout.do" method="POST">
        <button type="submit">Logout</button>
    </form>
</body>
</html>
