<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@include file="headercss.jsp" %>

<title>Read Article</title>


<script src="scripts/jquery.js"></script>
	        
	        <script type="text/javascript">
	        
	            $(document).ready(function(){
	            	//$('#txtId').val("${param.ArticleId}");
	            	$('#txtId').html("${param.ArticleId}");
        	      	$('#txtTitle').html("${param.title}");
	            	$('#txtAuthor').html("${param.author}");
	            	$('#txtAbstract').html("${param.Abstract}");

	            });

	        </script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">

<div class="well">
        
        <div style="visibility: hidden;"><h1>Article ID(must be removed) :<div id="txtId"></div> </h1></div>
        
        <h2>Title :<div id="txtTitle"></div> </h2>
        <h2>Author :<div id="txtAuthor"></div> </h2>
        Abstract : <div>   <textarea id="txtAbstract" name="txtAbstract" rows="15" cols="20" readonly="readonly"></textarea></div>
        <div style="align:right;"><a href="http://localhost:8080/ecom3/files/<c:out value="${fileIdd}"/>.pdf"target="_blank">Download full article (PDF)</a></div>
        
        
        

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

<c:if test="${letter != 'NO' }">
<div id="divLetter">
<h1>Letter</h1>
<textarea rows="15" cols="20" readonly>${letter}</textarea>
</div>
</c:if>


<a href="sendLetter.htm?txtArticleId=${param.ArticleId}">  Send Letter to Editor/ Author to Discuss this Article</a>
<div id="close">
	<input type="button" value="close window" onclick="window.close()">
</div>        
        
        
</div>


<%@include file="footer.jsp" %>
</div>










</body>
</html>