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

		var userName = document.getElementById('txtUsername').value;
		var pas = document.getElementById('txtPassword').value;

		//	if ( email_regex.test(userName) ) {
		if (username_regex.test(pas)) {

			document.forms["frmSignIn"].submit();	

		} else {
			alert('Check your User Information');
		}
		//	}else{alert('Check your User Information');}
	}
</script>

<title>log in</title>
</head>

<body>
	<%@include file="header.jsp"%>
	<div class="container">
		<div class="well">
			<center>
				<h3>Login Page</h3>
				<br />
				<form name="frmSignIn" id="frmSignIn" method="post"
					action="doSignIn.htm">
					<table align="center">
						<tr>
							<td>Username : <input type="text" id="txtUsername"
								name="txtUsername">
							</td>
						</tr>
						<tr>
							<td>Password : <input type="text" id="txtPassword"
								name="txtPassword">
							</td>
						</tr>
						<tr>
							<td><input type="submit" value="Log In"
								onclick="validate();return false;"></td>
						</tr>

					</table>
				</form>
			</center>
			${response}
		</div>
	</div>
</body>
</html>