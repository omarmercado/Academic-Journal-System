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

<title>POst A Letter</title>

<script type="text/javascript">
	function validate() {

		var Aid = document.getElementById('txtArticleId').value;

		if ( num_regex.test(Aid) ) {
			alert('Letter has been Posted to Article');
			document.forms["frmpostLetter"].submit();
		}else{alert('Article ID must be numeric');}
	}
</script>

</head>
<body>
	<%@include file="header.jsp"%>
	<div class="container">
		<div class="well">
			<center>
				<h3>Send Letter</h3>
				<br/>
				<form id="frmpostLetter" method="get" action="postLet.htm">
					<table align="center">
						Post Letter
						<tr>
							<td>Introduce Article Id <input type="text"
								id="txtArticleId" name="txtArticleId">
							</td>
						</tr>
						<tr>
							<td>Letter <textarea id="txtLetter" name="txtLetter"
									rows="10" cols="10"></textarea>
							</td>
						</tr>
						<tr>
							<td><input type="submit" value="Send"  onclick="validate();return false;"></td>
						</tr>
					</table>
				</form>
			</center>
		</div>
	</div>
</body>
</html>