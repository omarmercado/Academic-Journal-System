<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Read Abstract</title>

<%@include file="headercss.jsp" %>

<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>

<script src="scripts/jquery.js"></script>
	        
	        <script type="text/javascript">

	            $(document).ready(function(){
	        	    
	            	$('#txtId').val("${param.id}");
	            	$('#txtTitle').val("${param.title}");
	            	$('#txtAuthor').val("${param.author}");
	            	$('#txtAbstract').val("${param.Abstract}");

	            });

	        </script>
</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container">
<h1>Article Details and Abstract</h1>
<p><span class="label label-info">Note: below information are read only!</span></p>
<table>
<tr>
  <td>
   
  </td>
  <td><input type="hidden" value="" id="txtId"></td>
</tr>
<tr>
  <td>
    Titile :  
  </td>
  <td><input type="text" value="" id="txtTitle" disabled></td>
</tr>
<tr>
  <td>
    Author  :  
  </td>
  <td><input type="text" value="" id="txtAuthor" disabled></td>
</tr>
<tr>
  <td>
    Abstract : 
  </td>
  <td> <!--  <input type="text" value="" id="txtAbstract" disabled>  -->
      <textarea id="txtAbstract" name="txtAbstract" rows="15" cols="20" readonly="readonly"></textarea>
  </td>
</tr>
</table>
</div>

<div id="divMainAuthor">
<h1>MainAuthor</h1>
    <TABLE BORDER="1" id="tableMainAuthor">
    <c:forEach var="ListMainAuthor" items="${ListMainAuthorInfo}">
      <TR>
            <TD>UserId : <c:out value="${ListMainAuthor[0]}"  /></TD>
            <TD>First Name : <c:out value="${ListMainAuthor[1]}"  /></TD>
            <TD>Last Name : <c:out value="${ListMainAuthor[2]}"  /></TD>
            <TD>Email : <c:out value="${ListMainAuthor[3]}"  /></TD>
      </TR>
      </c:forEach>
    </TABLE> 
</div>

<div id="divListAuthors">
<h1>ListAuthors</h1>
    <TABLE BORDER="1" id="tableListAuthors">
      <c:forEach var="list" items="${ListAuthors}">
      <TR>
            <TD>UserId : <c:out value="${list[0]}"  /></TD>
            <TD>First Name : <c:out value="${list[1]}"  /></TD>
            <TD>Last Name : <c:out value="${list[2]}"  /></TD>
            <TD>Email : <c:out value="${list[3]}"  /></TD>
      </TR>
      </c:forEach>
      <tr>
       </tr>
    </TABLE> 
</div>

<div id="close">
	<input type="button" value="close window" onclick="window.close()">
</div>
</body>

</html>