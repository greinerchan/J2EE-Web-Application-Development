<%@page import="xc4.hw4.databean.User"%>
<%@page import="xc4.hw4.model.UserDAO"%>
<%@page import= "javax.servlet.http.HttpServletRequest" %>
<%@page import= "javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Family Blog</title>
    		<link rel="stylesheet" href="template.css">
    </head>
    <body>
   	<form action="user.do" method="POST">
		<jsp:include page="template.jsp" />
		<div class = "owner">
		<%
		HttpSession session2 = request.getSession();
		User currUser = (User)session2.getAttribute("user");
		String homeName = currUser.getFirstName();%>
		<h3><%=homeName%>'s Home Page</h3>
		</div>
		<div class="mycomment">
		<span style="text-align:float:left;display:inline-block;">
		<input type="button" name="delete" style="width:25px;height:25px;" value="X" />We need less homework and more sleep!! --11/10/2018 2:30pm</span><br><br>	
		<span style="text-align:float:left;padding-left:50px;display:inline-block;">
		<input type="button" name="delete" style="width:25px;height:25px;" value="X" />
		<font style="font-weight:bold; font-style:italic;">Comment by Xi Chen</font> - That's horrible, let's go to protest this!! -- 11/10/2018 2:50pm</span><br><br>
		<div class= "comment">
		<label for="comment"></label>
		<input type="text" id = "comment" size = "30" value=""/>
		<input type="button" name="comment" style="width:80px;height:22px;" value="Comment"/><br><br><br>
		</div>
		<span><input type="button" name="delete" style="width:25px;height:25px;" value="X" /> I spend whole night to finish my J2EE homework -- 11/11/2018 3:50pm</span><br><br>		
		<span style="text-align:float:left; padding-left:50px;display: inline-block;">
		<input type="button" name="delete" style="width:25px;height:25px;" value="X" />
		 <font style="font-weight:bold; font-style:italic;">Comment by Fengjiao Liu</font> - Me too!! I need more time to hang out with my friends and
		play a mobile phone -- 11/11/2018 4:20pm</span><br><br>
		<label for="comment"></label>
		<div class= "comment">
		<input type="text" id = "comment" size = "30" value=""/>
		<input type="button" name="comment" style="width:80px;height:22px;" value="Comment"/><br><br><br>
		</div>
		New Post:<textarea rows="5" cols="45" id="post" placeholder="Give us a new post" name="post"></textarea>
		<div class="postButton">
    	<button id="PostButton" type="submit" name="button" value="postButton">Submit</button>
    	</div>
		</div>
	</form>	

			
	</body>
</html>