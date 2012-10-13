<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reviewer Profile</title>

<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>
	        

</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container" style="width=1150px;">

<div id="divAllArticles">
<h5>${title}</h5>
    <TABLE BORDER="1" id="tableAllArticles" width="100%">
      <thead>
      <th>SubmissionId</th>
      <th>Title</th>
      <th>Author - UserName</th>
      
      </thead>
      <c:forEach var="AllArticles" items="${listPreviews}">
      <TR>         
          <TD><c:out value="${AllArticles[0]}"  /></TD>
          <TD><c:out value="${AllArticles[1]}"  /></TD>
          <TD><c:out value="${AllArticles[2]}"  /></TD>
      </TR>
      </c:forEach>
    </TABLE>
</div>


</div>	         	   
<div id="close">
	<input type="button" value="close window" onclick="window.close()">
</div>
</body>
</html>