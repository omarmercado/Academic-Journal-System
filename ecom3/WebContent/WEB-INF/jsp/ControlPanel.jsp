<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Control Panel</title>
<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>
</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container" style="width=1150px;">

<script type="text/javascript">


</script>
<ul class="nav nav-tabs" data-tabs="tabs">
            <li class="active"><a href="#cfg">Config User</a></li>
            <li><a href="#reviewing">Reviewing</a></li>
            <li><a href="#submit_author">Article submitted by an Author</a></li>
            <li><a href="#selected_reviews">Reviews Selected by User</a></li>
            <li><a href="#published_article">Articles published by Author</a></li>
            
            
            
        </ul>
        
        <div id="my-tab-content" class="tab-content">
            <div class="tab-pane active" id="cfg">
                <h3>Edit User Information</h3>
                <a href="user.htm?userId=<c:out value="${User[0]}"/>&firstName=<c:out value="${User[1]}"/>&lastName=<c:out value="${User[2]}"/>&email=<c:out value="${User[3]}"/>&roleName=<c:out value="${User[4]}"/>&userName=<c:out value="${User[5]}"/>&passWord=<c:out value="${User[6]}"/>&roleId=<c:out value="${User[7]}"/>"target="_blank">Edit User Info </a>
         	</div>
         	<div class="tab-pane" id="reviewing">
                <h3>Review An Article</h3>
            
                   <c:if test="${roleId != '1'}">
                      <form name="frmAllReviews" action="availableSubmissions.htm" method="get">
				        <input type="hidden" name="userId"  id="userId" value="${userId}">
				        <input type="submit"  value="Choose Reviews" >
				      </form>
				   </c:if>
	    		   <c:if test="${roleId == '1'}">
                       <div>You Have to Wait Until Your Submission has been Published to Start the Selection of your 3 Reviews</div>
				   </c:if>
         	</div>
         	<div class="tab-pane" id="submit_author">
                <h3>Submited BY Author</h3>
               <div id="divSubmited">
                	  <TABLE BORDER="1" id="tableSubmited">
				      <c:forEach var="Submited" items="${ListSubmited}">
				      <TR>
				      <TD><a href="readDocument.htm?id=<c:out value="${Submited[0]}"/>&title=<c:out value="${Submited[1]}"/>&Abstract=<c:out value="${Submited[2]}"/>&author=<c:out value="${Submited[5]}"/>&userId=${userId}&roleId=1"target="_blank"><c:out value="${Submited[1]}"/></a></TD>
				      <TD><c:out value="${Submited[4]}"/></TD>
				      <c:if test="${Submited[4] == 'waiting file'}">
				       <td> <a href="upload.htm?submissionId=${Submited[0]}"target="_blank">upload file</a> </td>
				      </c:if>
				          </TR>
				      </c:forEach>
				    </TABLE>
				</div>
         	</div>
         	<div class="tab-pane" id="selected_reviews">
				<h3>Reviews Selected by User</h3>
				<div id="divReviews">
				    <TABLE BORDER="1" id="tableReviews">
				      <c:forEach var="Reviews" items="${ListReviews}">
				      <TR>
				          <TD><a href="reviewDocument.htm?id=<c:out value="${Reviews[0]}"/>&title=<c:out value="${Reviews[1]}"/>&author=<c:out value="${Reviews[9]}"/>&Abstract=<c:out value="${Reviews[3]}"/>&status=<c:out value="${Reviews[4]}"/>&reviewId=<c:out value="${Reviews[6]}"/>&version=<c:out value="${Reviews[7]}"/>&userId=${userId}&roleId=2"target="_blank"><c:out value="${Reviews[1]}"/></a></TD>
				          <TD><c:out value="${Reviews[2]}"  /></TD>
				          <TD><c:out value="${Reviews[4]}"  /></TD>
				      </TR>
				      </c:forEach>
				    </TABLE> 
				</div>
         	</div>
         	<div class="tab-pane" id="published_article">
				<h3>Articles published by Author</h3>
				<div id="divArticles">
				
				
				    <TABLE BORDER="1" id="tableArticles">
				      <c:forEach var="Articles" items="${ListArticles}">
				      <TR>
				          <TD><a href="readArticle.htm?ArticleId=<c:out value="${Articles[0]}"/>&title=<c:out value="${Articles[1]}"/>&abstract=<c:out value="${Articles[3]}"/> "target="_blank"><c:out value="${Articles[1]}"/></a></TD>         
				          <TD><c:out value="${Articles[2]}"  /></TD>
				      </TR>
				      </c:forEach>
				    </TABLE> 
				</div>
         	</div>
         	<hr/>
         	<p>First Read the Reviewer Guidelines :  <a href="http://localhost:8080/ecom3/ReviewerGuidelines.jsp"target="_blank">Reviewer Guidelines</a></p>
        
        </div> <!--  end tabs -->







<br><br><br><br>


<!--  <a href="Submission.htm"target="_blank">New Submission</a><br><br>-->



<table id="tableControlPanel">
<form id="frmControlPanel" >
<tr>

</tr>


<tr>

</tr>

<tr>

</tr>


</form>
</table>
</div>
</body>
</html>