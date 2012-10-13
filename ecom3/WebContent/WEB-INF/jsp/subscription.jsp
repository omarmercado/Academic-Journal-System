<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/form_validation.js"></script>

<script type="text/javascript">

function validate() {

	var email = document.getElementById('txtEmail').value;

	if (email_regex.test(email)) {

		frmSignIn.submit();

	} else {
		alert('Invalid Email or No Email Supplied');
	}

}

</script>


<title>Subscriptions</title>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<div class="well">

<center>
<h3>Subscription</h3>
<br/>
<table align="center">
<form id="frmSignIn" method="get" action="subscribe.htm">
<tr>
<td>
Email  <input type="text" id="txtEmail" name="txtEmail">
</td>
</tr>
<tr>
<td>
Subscribe to next issues ? <input type="text" id="txtissue" name="txtissue"> Leave blank if you dont want to subscribe, else put any letter
</td>
</tr>
<tr>
<td>
Subscribe to Specific Keyword <input type="text" id="txtkeyword" name="txtkeyword"> only 1 keyword
</td>
</tr>
<tr>
<td>
<input type="submit" value ="Send" onclick="validate();return false;">
</td>
</tr>
</form>
</table>
</center>
${response}
</div>
</div>

</body>
</html>