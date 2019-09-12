<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8"/>
        <title>Family Blog</title>
    	<link rel="stylesheet" href="template.css">
    </head>
    <body>
		<jsp:include page="template.jsp" />
		<div class = "owner">
		<%String name = request.getParameter("username"); %>
		<h3><%=name%>'s Blog</h3>
		</div>
		<div class="mycomment">
		<span style="text-align:float:left;display:inline-block;">We need less homework and more sleep!! --11/10/2018 2:30pm</span><br><br>	
		<span style="text-align:float:left;padding-left:50px;display:inline-block;">
		<input type="button" name="delete" style="width:25px;height:25px;" value="X" />
		<font style="font-weight:bold; font-style:italic;">Comment by Xi Chen</font> - That's horrible, let's go to protest this!! -- 11/10/2018 2:50pm</span><br><br>
		<div class= "comment">
		<label for="comment"></label>
		<input type="text" id = "comment" size = "30" value=""/>
		<input type="button" name="comment" style="width:80px;height:22px;" value="Comment"/><br><br><br>
		</div>
		<span> I spend whole night to finish my J2EE homework -- 11/11/2018 3:50pm</span><br><br>		
		<span style="text-align:float:left; padding-left:50px;display: inline-block;">
		 <font style="font-weight:bold; font-style:italic;">Comment by Fengjiao Liu</font> - Me too!! I need more time to hang out with my friends and
		play a mobile phone -- 11/11/2018 4:20pm</span><br><br>
		<label for="comment"></label>
		<div class= "comment">
		<input type="text" id = "comment" size = "30" value=""/>
		<input type="button" name="comment" style="width:80px;height:22px;" value="Comment"/><br>
		</div>
		</div>
			
	</body>
</html>