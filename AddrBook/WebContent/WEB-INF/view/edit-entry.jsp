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
	
		<form name="entry" action="update-entry.do" method="post">
			<input type="hidden" name="id" value="${entry.id}"/>
			<input type="hidden" name="digest" value="${digest}"/>
			<table>
				<tr>
					<td>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					</td>
					<td colspan="3" />
					<td align="center">
						<input type="submit" name="button" value="Submit" />
					</td>
				</tr>
				<tr>
					<td></td>
                    <td class="label-cell">
                        <label for="lastName">Last Name:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="1" id="lastName" name="lastName" value="${entry.lastName}"/>
                    </td>
                    <td class="label-cell">
                        <label for="spouseLast">Spouse's Last Name:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="3" id="spouseLast" name="spouseLast" value="${entry.spouseLast}"/>
                    </td>
				</tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="firstNames">First Names:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="2" id="firstNames" name="firstNames" value="${entry.firstNames}"/>
                    </td>
                    <td class="label-cell">
                        <label for="spouseFirst">Spouse's First Name:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="4" id="spouseFirst" name="spouseFirst" value="${entry.spouseFirst}"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="birthday">Birthday:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="5" id="birthday" name="birthday"
                               value="<fmt:formatDate value='${entry.birthday}' type='date' pattern='MM/dd/yyyy'/>"/>
                    </td>
                    <td class="label-cell">
                        <label for="spouseBirthday">Spouse's Birthday:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="6" id="spouseBirthday" name="spouseBirthday"
                               value="<fmt:formatDate value='${entry.spouseBirthday}' type='date' pattern='MM/dd/yyyy'/>"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="homePhone">Home Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="7" id="homePhone" name="homePhone" value="${entry.homePhone}"/>
                    </td>
                    <td class="label-cell">
                        <label for="fax">Fax:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="8" id="fax" name="fax" value="${entry.fax}"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="cellPhone">Cell Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="9" id="cellPhone" name="cellPhone" value="${entry.cellPhone}"/>
                    </td>
                    <td class="label-cell">
                        <label for="spouseCell">Spouse's Cell Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="10" id="spouseCell" name="spouseCell" value="${entry.spouseCell}"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="workPhone">Work Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="11" id="workPhone" name="workPhone" value="${entry.workPhone}"/>
                    </td>
                    <td class="label-cell">
                        <label for="spouseWork">Spouse's Work Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="12" id="spouseWork" name="spouseWork" value="${entry.spouseWork}"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="email">e-mail:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="13" id="email" name="email" value="${entry.email}"/>
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="spouseEmail">Spouse's e-mail:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="14" id="spouseEmail" name="spouseEmail" value="${entry.spouseEmail}"/>
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="additional">Additional:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="15" id="additional" name="additional" value="${entry.additional}"/>
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="address">Address:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="16" id="address" name="address" value="${entry.address}"/>
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="city">City:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="17" id="city" name="city" value="${entry.city}"/>
                    </td>
                    <td class="label-cell">
                        <label for="state">State:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="18" id="state" name="state" value="${entry.state}"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="zip">Zip:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="19" id="zip" name="zip" value="${entry.zip}"/>
                    </td>
                    <td class="label-cell">
                        <label for="country">Country:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="20" id="country" name="country" value="${entry.country}"/>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="receivedCards">Rec'd Cards:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="21" id="receivedCards" name="receivedCards" value="${entry.receivedCards}"/>
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="sentCards">Sent Cards:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="22" id="sentCards" name="sentCards" value="${entry.sentCards}"/>
                     </td>
                </tr>
				<tr>
					<td></td>
					<td colspan="3"/>
					<td align="center">
						<input type="submit" name="button" value="Submit"/>
					</td>
				</tr>
			</table>
		</form>
		
	</body>
</html>
