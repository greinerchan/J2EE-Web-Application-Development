<!DOCTYPE html>
<html>
	<head>
        <meta charset="utf-8"/>
		<title>AddrBook Lookup Example -- Model 1</title>
    		<link rel="stylesheet" href="addrbook.css">
	</head>
	<body>
		<%@ page import="databeans.Entry" %>
		<%@ page import="dao.EntryDAO" %>

		<div class="title">Model 1 AddrBook Example</div>
		
		<div class="navbar">
			<a href="AddrBookLookup1.jsp?start=A">A</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=B">B</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=C">C</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=D">D</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=E">E</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=F">F</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=G">G</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=H">H</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=I">I</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=J">J</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=K">K</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=L">L</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=M">M</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=N">N</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=O">O</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=P">P</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=Q">Q</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=R">R</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=S">S</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=T">T</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=U">U</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=V">V</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=W">W</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=X">X</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=Y">Y</a>&nbsp;
			<a href="AddrBookLookup1.jsp?start=Z">Z</a>
		</div>

<%
    String start = request.getParameter("start");
    if (start != null) {
        EntryDAO dao = new EntryDAO();
        Entry[] list = dao.lookupByStartOfLastName(start);
%>
		<table>
			<tr><td colspan="2"><hr/></td></tr>
			<tr><td colspan="2">Found <%= list.length %> entries</td></tr>
<% 
        for (Entry e : list) {
%>
			<tr><td colspan="2"><hr/></td></tr>
			<tr>
				<td> Last Name: </td>
				<td> <%= e.getLastName() %> </td>
			</tr>
			<tr>
				<td> First Names: </td>
				<td> <%= e.getFirstNames() %> </td>
			</tr>
			<tr>
				<td> Home Phone: </td>
				<td> <%= e.getHomePhone() %> </td>
			</tr>
			<tr>
				<td> Cell Phone: </td>
				<td> <%= e.getCellPhone() %> </td>
			</tr>
			<tr>
				<td> Work Phone: </td>
				<td> <%= e.getWorkPhone() %> </td>
			</tr>
			<tr>
				<td> Fax: </td>
				<td> <%= e.getFax() %> </td>
			</tr>
			<tr>
				<td> E-mail: </td>
				<td> <%= e.getEmail() %> </td>
			</tr>
			<tr>
				<td> Address: </td>
				<td> <%= e.getAddress() %> </td>
			</tr>
			<tr>
				<td> City: </td>
				<td> <%= e.getCity() %> </td>
			</tr>
			<tr>
				<td> State: </td>
				<td> <%= e.getState() %> </td>
			</tr>
			<tr>
				<td> Zip: </td>
				<td> <%= e.getZip() %> </td>
			</tr>
			<tr>
				<td> Country: </td>
				<td> <%= e.getCountry() %> </td>
			</tr>
<% 
        }
%>
		</table>
<% 
    }
%>
	</body>
</html>
