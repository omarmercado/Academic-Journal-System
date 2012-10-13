<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<%@ page session="true" %>
	<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	        <title>File Upload</title>
	        <meta http-equiv="Content-Type" content="text/html; charset=windows-1251" >
	        <!--  <link rel="stylesheet" href='css/bootstrap3.css'> -->
	         <%@include file="headercss.jsp" %>
	        <script src="scripts/jquery.js"></script>
</head>
	    <body>
	    
	    <%@include file="header.jsp" %>
		<div class="container">
	       
	        <h3>Upload PDF </h3><br />
	        <form:form commandName="FORM" enctype="multipart/form-data" method="POST">
	        <table>
	         <tr><td colspan="2" style="color: red;"><form:errors path="*" cssStyle="color : red;"/>
	         ${errors}
	         </td></tr>
	         <input type="hidden" id="fileId" name="fileId" value="${fileId}">
	         <input type="hidden" id="submissionId" name="submissionId" value="${submissionId}">
	         <tr><td>Name : </td><td><form:input type="file" path="file" /></td></tr>
	         <tr><td colspan="2"><input type="submit" value="Upload File" /></td></tr>
	        </table>
	        </form:form>
	        
	        	<div id="close">
	 	          <input type="button" value="close window" onclick="window.close()">
	 	        </div>
	 	<%@include file="footer.jsp" %>
	 	</div>
	 	 	   
	    </body>
</html>