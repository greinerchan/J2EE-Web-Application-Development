<!doctype html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <body>

		<jsp:include page="search-form.jsp"/>

        <c:forEach var="error" items="${form.formErrors}">
            <h3 style="color:red"> ${error} </h3>
        </c:forEach>
    
		<form name="entry" action="update-entry.do" method="post">
            <c:forEach var="field" items="${form.hiddenFields}">
                <input type="${field.type}" name="${field.name}" value="${field.value}"/>
            </c:forEach>
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
                        <input size="20" tabindex="1" id="lastName" name="lastName" autofocus
                            <c:choose>
                                <c:when test="${empty(form.fieldError['lastName'])}">
                                    value="${form.lastName}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['lastName']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                    </td>
                    <td class="label-cell">
                        <label for="spouseLast">Spouse's Last Name:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="3" id="spouseLast" name="spouseLast"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['spouseLast'])}">
                                    value="${form.spouseLast}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['spouseLast']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                    </td>
                </tr>
				<tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="firstNames">First Names:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="2" id="firstNames" name="firstNames"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['firstNames'])}">
                                    value="${form.firstNames}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['firstNames']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                    <td class="label-cell">
                        <label for="spouseFirst">Spouse's First Name:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="4" id="spouseFirst" name="spouseFirst"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['spouseFirst'])}">
                                    value="${form.spouseFirst}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['spouseFirst']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
				</tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="birthday">Birthday:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="5" id="birthday" name="birthday"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['birthday'])}">
                                    value="${form.birthday}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['birthday']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                    <td class="label-cell">
                        <label for="spouseBirthday">Spouse's Birthday:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="6" id="spouseBirthday" name="spouseBirthday"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['spouseBirthday'])}">
                                    value="${form.spouseBirthday}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['spouseBirthday']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="homePhone">Home Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="7" id="homePhone" name="homePhone"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['homePhone'])}">
                                    value="${form.homePhone}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['homePhone']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                    <td class="label-cell">
                        <label for="fax">Fax:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="8" id="fax" name="fax"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['fax'])}">
                                    value="${form.fax}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['fax']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="cellPhone">Cell Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="9" id="cellPhone" name="cellPhone"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['cellPhone'])}">
                                    value="${form.cellPhone}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['cellPhone']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                    <td class="label-cell">
                        <label for="spouseCell">Spouse's Cell Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="10" id="spouseCell" name="spouseCell"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['spouseCell'])}">
                                    value="${form.spouseCell}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['spouseCell']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="workPhone">Work Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="11" id="workPhone" name="workPhone"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['workPhone'])}">
                                    value="${form.workPhone}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['workPhone']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                    <td class="label-cell">
                        <label for="spouseWork">Spouse's Work Phone:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="12" id="spouseWork" name="spouseWork"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['spouseWork'])}">
                                    value="${form.spouseWork}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['spouseWork']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="email">e-mail:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="13" id="email" name="email"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['email'])}">
                                    value="${form.email}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['email']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="spouseEmail">Spouse's e-mail:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="14" id="spouseEmail" name="spouseEmail"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['spouseEmail'])}">
                                    value="${form.spouseEmail}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['spouseEmail']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
				<tr>
					<td></td>
                    <td class="label-cell">
                        <label for="additional">Additional:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="15" id="additional" name="additional"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['additional'])}">
                                    value="${form.additional}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['spouseEmail']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
				</tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="address">Address:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="16" id="address" name="address"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['address'])}">
                                    value="${form.address}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['address']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="city">City:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="17" id="city" name="city"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['city'])}">
                                    value="${form.city}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['city']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                    <td class="label-cell">
                        <label for="state">State:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="18" id="state" name="state"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['state'])}">
                                    value="${form.state}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['state']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="zip">Zip:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="19" id="zip" name="zip"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['zip'])}">
                                    value="${form.zip}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['zip']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                    <td class="label-cell">
                        <label for="country">Country:</label>
                    </td>
                    <td>
                        <input size="20" tabindex="20" id="country" name="country"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['country'])}">
                                    value="${form.country}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['country']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="receivedCards">Rec'd Cards:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="21" id="receivedCards" name="receivedCards"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['receivedCards'])}">
                                    value="${form.receivedCards}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['receivedCards']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
                     </td>
                </tr>
                <tr>
                    <td></td>
                    <td class="label-cell">
                        <label for="sentCards">Sent Cards:</label>
                    </td>
                    <td colspan="3">
                        <input size="71" tabindex="22" id="sentCards" name="sentCards"
                            <c:choose>
                                <c:when test="${empty(form.fieldError['sentCards'])}">
                                    value="${form.sentCards}"
                                </c:when>
                                <c:otherwise>
                                    placeholder="${form.fieldError['sentCards']}"
                                    class="field-error"
                                </c:otherwise>
                            </c:choose>
                         >
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
