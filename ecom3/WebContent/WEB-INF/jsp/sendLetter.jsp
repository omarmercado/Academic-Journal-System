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
<title>Send Letter</title>


<script type="text/javascript">

	function validate() {

		var Aid = document.getElementById('txtEmail').value;
		
		
		
		if ( email_regex.test(Aid) ) {
			
			document.forms["formEd"].submit();
			
		}else{alert('Article ID must be numeric');}
	}
	
	function validate2() {

		var Aid = document.getElementById('txtEmail2').value;


		if ( email_regex.test(Aid) ) {
			document.forms["formA"].submit();
		}else{alert('Article ID must be numeric2');}
	}
</script>

</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<div class="well">

<center>
<h3>Send Letter</h3>
<br/>
<table align="center">
Send Letter to A Editor
<form id="formEd" name="formEd" method="get" action="send.htm">
<input type="hidden" id="txtArticleId" name="txtArticleId" value="${ArticleId}">
<tr>
<td>
Email  <input type="text" id="txtEmail" name="txtEmail">
</td>
</tr>
<tr>
<td>
Letter <textarea id="txtLetter" name="txtLetter" rows="10" cols="10"></textarea>
</td>
</tr>
<tr>
<td>
<input type="submit" value ="Send Letter to Editor" onclick="validate();return false;">
</td>
</tr>
</form>


Send Letter to the Author
<form id="formA"  name="formA" method="get" action="sendAuthor.htm">
<input type="hidden" id="txtArticleId2" name="txtArticleId2" value="${ArticleId}">
<tr>
<td>
Email  <input type="text" id="txtEmail2" name="txtEmail2">
</td>
</tr>
<tr>
<td>
Letter <textarea id="txtLetter2" name="txtLetter2" rows="10" cols="10"></textarea>
</td>
</tr>
<tr>
<td>
<input type="submit" value ="Send Letter to Author" onclick="validate2();return false;">
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