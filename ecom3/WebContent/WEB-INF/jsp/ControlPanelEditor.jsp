<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editor Control Panel</title>
<%@include file="headercss.jsp" %>
<script src="scripts/form_validation.js"></script>
<script src="scripts/jquery.js"></script>
<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function ($) {
            $(".tabs").tabs();
        });
        
        
        
    function validate() {

    		var title = document.getElementById('txtTile').value;
    		var obj = document.getElementById('txtObj').value;

    	if ( username_regex.test(title) ) {
    		if (username_regex.test(obj)) {

    			frmCreateJournal.submit();
    			alert('New Issue has been added to Current Journal');

    		} else {
    			alert('No Special Characters, Title and Objectives cant be blank');
    		}
        }else{alert('No Special Characters, Title and Objectives cant be blank');}
    }
    </script>
</head>

<body>
<%@include file="adminheader.jsp" %>
   <div class="container" style="width=1150px;">
        <ul class="nav nav-tabs" data-tabs="tabs">
            <li class="active"><a href="#cfg">Journal config</a></li>
            <li><a href="#user">List Users</a></li>
            <li><a href="#review">Reviewing</a></li>
            <li><a href="#published_articles">All Published Articles</a></li>
            <li><a href="#waiting_published">Waiting To Be Published</a></li>
            <li><a href="#need_respond">Needs To Respond</a></li>
            <li><a href="#reviewer_waiting">Applied To Be Reviewer</a></li>
            <li><a href="#review_user">Reviews Selected by User</a></li>
            <li><a href="postLetter.htm"target="_blank">Post Letter  </a></li>
            
            
        </ul>
        <div id="my-tab-content" class="tab-content">
            <div class="tab-pane active" id="cfg">
                <h3>Journal config</h3>
                
                <form name="frmCreateJournal" id="frmCreateJournal" action="createJournal.htm" method="get">
				Journal Title  <input type="text" id="txtTile" name="txtTile">
				Journal Objectives  <input type="text" id="txtObj" name="txtObj">
				<input type="submit"  value="Config Journal" onclick="validate();return false;">
				</form>
				<br/>
				<form name="frmNewIssue" action="newIssue.htm" method="get">
					<input type="submit"  value="Create New Issue">
				</form>
				<br><br>
				<form name="frmChangeTemp" action="changeTemp.htm" method="get">
				Change Template : <select id="template" name="template"> 
                                      <option value="" selected>Select</option>
                                      <c:forEach var="temp" items="${listTemplates}">
                                            <option value="${temp[0]}">${temp[1]}</option>
                                      </c:forEach>
                                      </select>
					    <input type="submit"  value="Change Style Template">
				</form>
				
				<br><br><br><br><br>
				
				<c:if test="${TimeToPublish == 'Exist' }">
                    <div>New Articles Are Available and +3 Months had passed since last New Issue was Created </div>
                </c:if>
                
                	<form name="frmResign" action="resign.htm" method="get">
					<input type="submit"  value="Resign as Editor">				
					<c:if test="${resign != '' }">
                    <div>${resign} </div>
                    </c:if>
                
				</form>
         
            </div>
            
            <div class="tab-pane" id="user">
                
      
	                <div id="divAllUsers">
					<h3>List of Users</h3>
					    <TABLE BORDER="1" id="tableAllUsers">
					      <c:forEach var="AllUsers" items="${ListAllUsers}">
					      <TR>
					  <TD><a href="editUser.htm?userId=<c:out value="${AllUsers[0]}"/>&firstName=<c:out value="${AllUsers[1]}"/>&lastName=<c:out value="${AllUsers[2]}"/>&email=<c:out value="${AllUsers[3]}"/>&roleName=<c:out value="${AllUsers[4]}"/>&userName=<c:out value="${AllUsers[5]}"/>&passWord=<c:out value="${AllUsers[6]}"/>"target="_blank">   <c:out value="${AllUsers[1]}"/>  <c:out value="${AllUsers[2]}"/>  </a></TD>
					          <TD><c:out value="${AllUsers[3]}"  /></TD>
					      </TR>
					      </c:forEach>
					    </TABLE>
					</div>
            </div>
            
            <div class="tab-pane" id="review">
                <h1>Reviewing Articles</h1>
                <form name="frmAllReviews" action="availableSubmissions.htm" method="get">
					<input type="hidden" name="userId"  id="userId" value="${userId}">
					<input type="submit"  value="Choose Reviews" >
				</form>
            </div>
            
            <div class="tab-pane" id="published_articles">
                <h3>All Available Articles</h3>
                <div id="divAllArticles">

				    <TABLE BORDER="1" id="tableAllArticles">
				      <c:forEach var="AllArticles" items="${ListAllArticles}">
				      <TR>
				  <TD><a href="readArticle.htm?ArticleId=<c:out value="${AllArticles[0]}"/>&title=<c:out value="${AllArticles[4]}"/>&Abstract=<c:out value="${AllArticles[3]}"/>&author=<c:out value="${AllArticles[6]}"/>&MainAuthorId=<c:out value="${AllArticles[7]}"/>  "target="_blank"><c:out value="${AllArticles[4]}"/></a></TD>         
				          <TD><c:out value="${AllArticles[2]}"  /></TD>
				      </TR>
				      </c:forEach>
				    </TABLE>
				</div>
            </div>
            
            <div class="tab-pane" id="waiting_published">
      			<div id="divToPublish">
                <h3>Waiting to be Published</h3>
				    <TABLE BORDER="1" id="tableToPublish">
				      <c:forEach var="ToPublish" items="${ListToPublish}">
				      <TR>                                           
				          <TD><a href="publishDocument.htm?submissionId=<c:out value="${ToPublish[0]}"/>&title=<c:out value="${ToPublish[2]}"/>&author=<c:out value="${ToPublish[1]}"/>&Abstract=<c:out value="${ToPublish[3]}"/>&userId=${userId}"target="_blank"><c:out value="${ToPublish[2]}"/></a></TD>
				          <TD><c:out value="${ToPublish[1]}"  /></TD>
				      </TR>
				      </c:forEach>
				    </TABLE>
				</div>
			</div>
			
			<div class="tab-pane" id="need_respond">
				<div id="divAllSubWCom">
					<h3>Need to Respond</h3>
					    <TABLE BORDER="1" id="tableAllSubWCom">
					      <c:forEach var="AllSubWCom" items="${ListAllSubWCom}">
					      <TR>
					  <TD><a href="respondComent.htm?id=<c:out value="${AllSubWCom[0]}"/>&title=<c:out value="${AllSubWCom[1]}"/>&author=<c:out value="${AllSubWCom[2]}"/>&Abstract=<c:out value="${AllSubWCom[3]}"/>&userId=${userId}&roleId=3"target="_blank"><c:out value="${AllSubWCom[1]}"/></a></TD>
					          <TD><c:out value="${AllSubWCom[2]}"  /></TD>
					      </TR>
					      </c:forEach>
					    </TABLE>
				</div>
			</div>
			
			<div class="tab-pane" id="reviewer_waiting">
				
				<div id="divAllRevApply">
					<h1>Applied to be a Reviewer</h1>
					    <TABLE BORDER="1" id="tableAllRevApply">
					      <c:forEach var="AllRevApp" items="${ListReviewRoleApplications}">
					      <TR>
					  <TD><a href="readReviewRoleApplication.htm?id=<c:out value="${AllRevApp[0]}"/>&firstName=<c:out value="${AllRevApp[1]}"/>&lastName=<c:out value="${AllRevApp[2]}"/>&email=<c:out value="${AllRevApp[3]}"/>&msg=<c:out value="${AllRevApp[4]}"/>"target="_blank"><c:out value="${AllRevApp[1]}"/></a></TD>
					          <TD><c:out value="${AllRevApp[3]}"  /></TD>
					      </TR>
					      </c:forEach>
					    </TABLE>
					</div>
			
			</div>
			
			<div class="tab-pane" id="review_user">
			<div id="divReviews">

				<h1>Reviews Selected by USer</h1>
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
			
        </div>
    
            









<table id="tableControlPanelEditor">
<tr>


</tr>

<tr>

</tr>

<tr>

</tr>


<tr>

</tr>




<tr>

</tr>
<tr>
</tr>


</table>
</div>
</body>
</html>