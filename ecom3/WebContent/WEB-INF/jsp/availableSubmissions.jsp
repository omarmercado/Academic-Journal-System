<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Available Reviews</title>

<%@include file="headercss.jsp" %>

<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>

<script src="scripts/jquery.js"></script>	        

<script type="text/javascript">	        

function selReview(row){
	
	  $.ajax({
	        url: 'doSelectReview.htm',
	        data: ({userId : ${param.userId}, submissionId : row }),
	        success: function(data) {
	        	location.reload();
	        }
	      });

}

function selReviewTemp(row){
	
	  $.ajax({
	        url: 'doSelectReviewTemp.htm',
	        data: ({userId : ${param.userId}, submissionId : row }),
	        success: function(data) {
	        	location.reload();
	        }
	      });

}

function deSelect(row){
	
	  $.ajax({
	        url: 'deleteTempSelect.htm',
	        data: ({userId : ${param.userId}, submissionId : row }),
	        success: function(data) {
	        	location.reload();
	        }
	      });

}

</script>


</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container">
<h1>List Available Reviews</h1>

<form id="frmReview">
<table id="tableReview">
  <c:forEach var="Reviews" items="${ListReviews}">
    <tr>
      <td><a target="_blank" href="readAbstract.htm?id=<c:out value="${Reviews[0]}"/>&title=<c:out value="${Reviews[1]}"/>&Abstract=<c:out value="${Reviews[2]}"/>&mainAuthorId=<c:out value="${Reviews[3]}"/> "target="_blank"><c:out value="${Reviews[1]}"/></a></td>
      <td><input type="button" id="<c:out value="${Reviews[0]}"/>" name="<c:out value="${Reviews[0]}"/>" value="Choose" onClick="selReviewTemp(<c:out value="${Reviews[0]}"/>)"></td>
    </tr>
  </c:forEach>

</table>
<h1>Reviews Selected by USer</h1>
<TABLE BORDER="1" id="tableReviews">

      <c:forEach var="ReviewsUser" items="${ListReviewsUser}">
      <TR>
          <TD><a href="readAbstract.htm?id=<c:out value="${ReviewsUser[0]}"/>&title=<c:out value="${ReviewsUser[1]}"/>&author=<c:out value="${ReviewsUser[2]}"/>&Abstract=<c:out value="${ReviewsUser[3]}"/>&mainAuthorId=<c:out value="${ReviewsUser[9]}"/>"target="_blank"><c:out value="${ReviewsUser[1]}"/></a></TD>
          <TD><c:out value="${ReviewsUser[2]}"  /></TD>
          <TD><c:out value="${ReviewsUser[4]}"  /></TD>
          <TD><c:out value="${ReviewsUser[8]}"  /></TD>
           <c:if test="${(ReviewsUser[8] == 5) }">
          <td><input type="button" id="<c:out value="${ReviewsUser[0]}"/>" name="<c:out value="${ReviewsUser[0]}"/>" value="Choose" onClick="selReview(<c:out value="${ReviewsUser[0]}"/>)"></td>
          <td><input type="button" id="<c:out value="${ReviewsUser[0]}"/>" name="<c:out value="${ReviewsUser[0]}"/>" value="De Select" onClick="deSelect(<c:out value="${ReviewsUser[0]}"/>)"></td>
          </c:if>
      </TR>
      </c:forEach>
</TABLE> 
<a href="back.htm">Back to Control Panel</a>
</form>
</div>
</body>
</html>