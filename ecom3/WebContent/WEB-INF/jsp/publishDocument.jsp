<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Publish Document</title>

<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
	        
	        <script type="text/javascript">
	        
	        function RejectReview(val){
	        	
	           	 $.ajax({
	        	        url: 'rejectReview.htm',
	        	        data: ({reviewId : val}),
	        	        success: function(data) {alert('Review Has Been Rejected');
	        	        }
	        	      });
		        	
		        }
	        
	        function rejectArticle(){
	        	
           	 $.ajax({
        	        url: 'rejectArticle.htm',
        	        data: ({submissionId : ${param.submissionId}}),
        	        success: function(data) {
        	        	alert('Article Has Been Rejected');
        	        }
        	      });
	        	
	        }
	        
	        function Conflict(val){
	        	
	           	 $.ajax({
	        	        url: 'conflictReview.htm',
	        	        data: ({reviewId : val}),
	        	        success: function(data) {
	        	        	alert('Review has been Rejected because of conflict');
	        	        }
	        	      });
		        	
		        }
	        
	        
	        $(document).ready(function(){	        	    
	            	$('#txtSubmissionId').val("${param.submissionId}");
	            	$('#txtTitle').val("${param.title}");
	            	$('#txtAuthor').val("${param.author}");
	            	$('#txtAbstract').val("${param.Abstract}");
	            });

	        </script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<form id="formPublish" action="publish.htm">
<table>
<tr>
  <td>
    id :  <input type="text"  id="txtSubmissionId" name="txtSubmissionId" readonly>
  </td>
</tr>
<tr>
  <td>
    Titile :  <input type="text"  id="txtTitle" name="txtTitle" readonly>
  </td>
</tr>
<tr>
  <td>
    Author  :  <input type="text"  id="txtAuthor" name="txtAuthor" readonly>
  </td>
</tr>
<tr>
  <td>
    Abstract : <!--   <input type="text"  id="txtAbstract" name="txtAbstract" readonly> -->
    <textarea id="txtAbstract" name="txtAbstract" rows="15" cols="20" readonly="readonly"></textarea>
  </td>
</tr>
${response}
</table>

<input type="submit" value="Publish Article">
<input type ="button" value="Reject Article" onclick="rejectArticle();">
</form>
</body>
<a href="http://winsrv4/DesktopR$/acp11omm/Desktop/files/<c:out value="${fileIdd}"/>"target="_blank">Download File</a>
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

<div id="divCriticism">
<h1>Criticism</h1>
    <TABLE BORDER="1" id="tableCriticism">
      <c:forEach var="list" items="${ListCriticism}">
      <TR>
            <TD>Crit  :  <c:out value="${list[1]}"  /></TD>
            <TD>Response  :  <c:out value="${list[2]}"  /></TD>
      </TR>
      </c:forEach>
      <tr>
       </tr>
    </TABLE> 
</div>


<div id="divComments">
<h1>Coments</h1>
    <TABLE BORDER="1" id="tableComments">
      <c:forEach var="Comments" items="${ListComments}">
      <TR>
            <TD>Reviewer  :  <c:out value="${Comments[1]}"  /></TD>
            <TD>Comment  :  <c:out value="${Comments[3]}"  /></TD>
            <TD>Response  :  <c:out value="${Comments[4]}"  /></TD>
      </TR>
      </c:forEach>
    </TABLE> 
</div>

<div id="divReview">
<h1>Review</h1>
    <TABLE BORDER="1" id="tableReview">   
      <c:forEach var="Review" items="${ListReview}">   
      <TR>
            <TD>Score  :  <c:out value="${Review[4]}"  /></TD>
            <TD>Review  :  <c:out value="${Review[0]}"  /></TD>
            <TD>Author  :   <a href="profile.htm?userId=<c:out value="${Review[3]}"/>"target="_blank" >  <c:out value="${Review[1]}"  /> </a> </TD>
            <TD> <input type="button" id="RReview" value="Reject Review" onClick="RejectReview(${Review[2]})"> </TD>     
            <TD> <input type="button" id="Conflict" value="Conflict" onClick="Conflict(${Review[2]})"> </TD>   
      </TR>
      </c:forEach>
    </TABLE> 
</div>

<div id="close">
	<input type="button" value="close window" onclick="window.close()">
</div>
<%@include file="footer.jsp" %>
</div>
</html>