<!doctype html>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"  %>

<html>
	<head>
        <meta charset="utf-8">
		<title>AddrBook</title>
		<link href="search-form.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="search-suggest.js"> </script>
        <style>
            label {
                font-size: large;
            }
            .field-cell {
                font-size: x-large;
            }
            .label-cell {
                background-color: lightgray;
            }
            .field-error {
                background-color: pink;
            }
        </style>
	</head>

    <body onLoad="document.getElementById('last').focus()">

		<jsp:include page="search-form.jsp"/>

		<jsp:include page="errors-and-messages.jsp"/>
	
		<form name="entry" action="modify-entry.do" method="post">
			<input type="hidden" name="id" value="${entry.id}"/>
			<table>
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td colspan="2" />
					<td align="center">
						<input type="submit" name="button" value="Delete"/>
					</td>
					<td align="center">
						<input type="submit" name="button" value="Edit"/>
					</td>
				</tr>
				<tr>
					<td></td>
                    <td class="label-cell"><label>Last Name:</label></td>
                    <td><span class="field-cell">&nbsp;&nbsp;${entry.lastName}&nbsp;&nbsp;</span></td>
					<td class="label-cell"><label>Spouse's Last Name:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.spouseLast}&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<td></td>
					<td class="label-cell"><label>First Names:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.firstNames}&nbsp;&nbsp;</span></td>
					<td class="label-cell"><label>Spouse's First Name:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.spouseFirst}&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<td></td>
					<td class="label-cell"><label>Birthday:</label></td>
					<td>
						<span class="field-cell">
							&nbsp;&nbsp;<fmt:formatDate value="${entry.birthday}" type="date" pattern="MM/dd/yyyy"/>&nbsp;&nbsp;
						</span>
					</td>
					<td class="label-cell"><label>Spouse's Birthday:</label></td>
					<td>
						<span class="field-cell">
							&nbsp;&nbsp;<fmt:formatDate value="${entry.spouseBirthday}" type="date" pattern="MM/dd/yyyy"/>&nbsp;&nbsp;
						</span>
					</td>
				</tr>
				<tr>
					<td></td>
					<td class="label-cell"><label>Home Phone:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.homePhone}&nbsp;&nbsp;</span></td>
					<td class="label-cell"><label>Fax:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.fax}&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<td></td>
					<td class="label-cell"><label>Cell Phone:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.cellPhone}&nbsp;&nbsp;</span></td>
					<td class="label-cell"><label>Spouse's Cell Phone:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.spouseCell}&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<td></td>
					<td class="label-cell"><label>Work Phone:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.workPhone}&nbsp;&nbsp;</span></td>
					<td class="label-cell"><label>Spouse's Work Phone:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.spouseWork}&nbsp;&nbsp;</span></td>
				</tr>
				<c:if test="${!(empty entry.email)}">
					<tr>
						<td></td>
						<td class="label-cell"><label>e-mail:</label></td>
						<td colspan="3"><span class="field-cell">&nbsp;&nbsp;<a href="mailto:${entry.email}">${entry.email}</a>&nbsp;&nbsp;</span></td>
					</tr>
				</c:if>
				<c:if test="${!(empty entry.spouseEmail)}">
					<tr>
						<td></td>
						<td class="label-cell"><label>Spouse's e-mail:</label></td>
						<td colspan="3"><span class="field-cell">&nbsp;&nbsp;<a href="mailto:${entry.spouseEmail}">${entry.spouseEmail}</a>&nbsp;&nbsp;</span></td>
					</tr>
				</c:if>
				<c:if test="${!(empty entry.additional)}">
					<tr>
						<td></td>
						<td class="label-cell"><label>Additional:</label></td>
						<td colspan="3"><span class="field-cell">&nbsp;&nbsp;${entry.additional}&nbsp;&nbsp;</span></td>
					</tr>
				</c:if>
				<tr>
					<td></td>
					<td class="label-cell"><label>Address:</label></td>
					<td colspan="3"><span class="field-cell">&nbsp;&nbsp;${entry.address}&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<td></td>
					<td class="label-cell"><label>City:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.city}&nbsp;&nbsp;</span></td>
					<td class="label-cell"><label>State:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.state}&nbsp;&nbsp;</span></td>
				</tr>
				<tr>
					<td></td>
					<td class="label-cell"><label>Zip:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.zip}&nbsp;&nbsp;</span></td>
					<td class="label-cell"><label>Country:</label></td>
					<td><span class="field-cell">&nbsp;&nbsp;${entry.country}&nbsp;&nbsp;</span></td>
				</tr>
				<c:if test="${!(empty entry.receivedCards) || !(empty entry.sentCards)}">
					<tr>
						<td></td>
						<td class="label-cell"><label>Rec'd Cards:</label></td>
						<td colspan="3"><span class="field-cell">&nbsp;&nbsp;${entry.receivedCards}&nbsp;&nbsp;</span></td>
					</tr>
					<tr>
						<td></td>
						<td class="label-cell"><label>Send Cards:</label></td>
						<td colspan="3"><span class="field-cell">&nbsp;&nbsp;${entry.sentCards}&nbsp;&nbsp;</span></td>
					</tr>
				</c:if>
				<c:if test="${!(empty entry.updated)}">
					<tr>
						<td></td>
						<td class="label-cell"><label>Updated:</label></td>
						<td colspan="3">
							<span class="field-cell">
								&nbsp;&nbsp;<fmt:formatDate value="${entry.updated}" type="both" pattern="MMM-dd-yyyy  h:mm aa"/>&nbsp;&nbsp;
							</span>
						</td>
					</tr>
				</c:if>
				<tr>
					<td colspan="3" />
					<td align="center">
						<input type="submit" name="button" value="Delete"/>
					</td>
					<td align="center">
						<input type="submit" name="button" value="Edit"/>
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>
