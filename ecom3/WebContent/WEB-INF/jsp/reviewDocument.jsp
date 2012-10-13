<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fnc" %> 
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Document</title>

<%@include file="headercss.jsp" %>
<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>
<script src="scripts/jquery.js"></script>
	        
	        <script type="text/javascript">
	        
	        function acceptCriticism(val){
	        
	        	
	       
	        	 $.ajax({
	         	        url: 'crtAccept.htm',
	         	        data: ({submissionId : ${param.id},crtcismId : val }),
	         	        success: function(data) {
	         	       
	         	        	alert('Criticisms has been Accepted');
	         	        	document.getElementById("txtCriticism").value = '';
	         	        }
	         	      });
	        	 
	        }
	        
	        function comResponse(val){
                       var response = document.getElementById(val).value;
            	 $.ajax({
         	        url: 'ComResponse.htm',
         	        data: ({commentId : val, response: response }),
         	        success: function(data) {
         	       
         	        	alert('Croment has been responded');
         	        	document.getElementById("txtComment").value = '';
         	        }
         	      });
	        }
	        
	       function setReview(){
          
	    	    var scoreId = document.getElementById("selectScore").value;
                var reviewerExpertiseId = document.getElementById("selectExpertise").value;
                var reviewId =  document.getElementById("txtReviewId").value; 
                var reviewText = document.getElementById("txtReview").value; 
                	
     	        $.ajax({
     	        	url: 'setReview.htm',
  	                data: ({scoreId : scoreId, reviewerExpertiseId : reviewerExpertiseId, reviewId : reviewId, reviewText : reviewText }),
  	                success: function(data) {
  	                  $('#frmReview').submit();
  	                  
  	                        }
  	                });
                  }  
	        
	    
	        
	            $(document).ready(function(){
	        	    
	            	$('#txtId').val("${param.id}");// submissionId
	            	$('#txtTitle').val("${param.title}");
	            	$('#txtAuthor').val("${param.author}");
	            	$('#txtAbstract').val("${param.Abstract}");
	            	$('#txtReviewId').val("${param.reviewId}");
	            	$('#txtReview').val("${reviewText}");
	            	
	  
	      
	            	if (${param.roleId} == 1){
	            		
	            		$("#divComent").css('display','none');
	            		$("#divBtnComent").css('display','none');
	            		
	            	}
	            	
	            //	alert(${param.roleId});
	            	
	            	if (${param.roleId} == 3){
	            		$("#divBtnReview").css('display','none');
	            		$("#divBtnComent").css('display','none');
	            		$("#divBtnCrit").css('display','none');
	            	}
	            	

   	               if(${fnc:length(ListCriticism)} == 0){
	            		
	            		$('#divCriticism').css('display','none')
	            	}
   	               
   	            if(${fnc:length(ListComments)} == 0){
            		
            		$('#divComent').css('display','none')
            	}
   	            
   	         if (${param.version} == 2){
   	        	document.getElementById('btnCriticism').disabled = true; 
   	        	alert('This Submission is now on his Second Version, No more interaction is possible with the Author');
         	}
   	            
   	            
   	
	            	$('#btnCriticism').click(function(){
	            		var content = document.getElementById("txtCriticism").value
	            		var reviewId = document.getElementById("txtReviewId").value;
	            	 $.ajax({
	         	        url: 'Criticism.htm',
	         	        data: ({submissionId : ${param.id},content : content,userId :${param.userId},reviewId : reviewId  }),
	         	        success: function(data) {
	         	       
	         	       	alert('Criticisms has been sent');
	         	        	document.getElementById("txtCriticism").value = '';
	         	        }
	         	      });
	            	});
	            	
	             	$('#btnComment').click(function(){
	             		var comment = document.getElementById("txtComment").value

		            	 $.ajax({
		         	        url: 'Comment.htm',
		         	        data: ({submissionId : ${param.id},comment : comment, userId :${param.userId},roleId : '2' }),
		         	        success: function(data) {
		         	       	alert('Coment has been sent');
		         	        	document.getElementById("txtComment").value = '';
		         	        }
		         	      });
		            	});
	             	
	        
	             	
	                $('#showReview').click(function(){	
	            	$('#divReviewArea').show();
	            	$('#divCommentText').css('display','none');
	            	$('#divCritArea').css('display','none');
	            	
	            });
	            
	            $('#showComents').click(function(){   
	            	$('#divCommentText').show();
	            	$('#divReviewArea').css('display','none');
	            	$('#divCritArea').css('display','none');
	            	
	            });
	            
	            $('#showCriticisms').click(function(){   
	            	$('#divCritArea').show();
	            	$('#divReviewArea').css('display','none');
	            	$('#divCommentText').css('display','none');
	            });
	            
	            	 
	            });
	            
	            function clean(){
	            	document.getElementById('txtComment').value='';
	            }
	            
	  
	            function imposeMaxLength(Object, MaxLen)
	            {
	              return (Object.value.length <= MaxLen);
	            }
	            

	        </script>
</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container">
<form id="frmReview" name="frmReview" action="succesfulReview.htm">
</form>
<table>
<tr>
  <td>
  <input type="hidden" id="txtReviewId">
  </td>
  <td><input type="hidden" value="" id="txtId">
           	<td>First Read How To Review Guidlines :  <a href="http://localhost:8080/ecom3/HowtoReview.jsp"target="_blank">How To Review</a></td>
  </td>
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
  <td>
  <!--   <input type="text" value="" id="txtAbstract" disabled>  -->
  <textarea id="txtAbstract" name="txtAbstract" rows="15" cols="20" readonly="readonly"></textarea></td>
</tr>
<tr>
<td>
 Select Review Score : 
</td>
<td><select id="selectScore"> 
     <c:forEach var="list" items="${ListScore}">
          <option value="<c:out value="${list[1]}"/>"> <c:out value="${list[0]}"/> </option> 
     </c:forEach>
 </select></td>
</tr>
<tr>
<td>
Select Your Level of Expertise : 
</td>
<td>
<select id="selectExpertise">  
      <c:forEach var="list" items="${ListExpertise}">
          <option value="<c:out value="${list[0]}"/>"> <c:out value="${list[1]}"/> </option> 
     </c:forEach>
 </select>
</td>
</tr>
</table>

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




<table id="tableButtons">
  <tr>
    <td>
       <div id="divBtnReview"><input type="button" id="showReview" value="Make Review"></div>
    </td>
    <td>
       <div id="divBtnComent"> <input type="button" id="showComents" value="Send Coment To Editor"></div>
    </td>
    <td>
       <div id="divBtnCrit"> <input type="button" id="showCriticisms" value="Send Criticism To Author"></div>
    </td>
  </tr>
</table>


<div id="divReviewArea" style="display: none;">
  <table id="tableReviewArea">
    <tr>
    Write your Review and Sumarize the text
      <td>
        <textarea id="txtReview" name="txtReview" rows="10" cols="10" >${param.reviewText}</textarea>
      </td>
      <td> max 255 char.
        <input type="button" id="btnReview" name="btnReview" value="Review" onClick="setReview()">
      </td>
    </tr>
  </table>
</div>



<div id="divCritArea"  style="display: none;">
  <table id="tableCritArea">
    <tr>
      <td>
        Criticism
      </td>
      <td>
        <textarea id="txtCriticism" name="txtCriticism" rows="10" cols="10" ></textarea>
      </td>
      <td>
      max 255 char.
        <input type="button" id="btnCriticism" name="btnCriticism" value="Criticism">
      </td>
    </tr>
  </table>
</div>

<div id="divCommentText" style="display: none;">
  <table>
    <tr>
      <td>
      Comment
      </td>
      <td>
        <textarea id="txtComment" name="txtComment" rows="10" cols="10"  ></textarea>
      </td>
      <td> max 255 char.
        <input type="button" id="btnComment" name="btnComment" value="Comment">
      </td>
    </tr>
  </table>
</div>

<div id="divCriticism" >
<h1>Criticism</h1>
    <TABLE BORDER="1" id="tableCriticism">
      <c:forEach var="list" items="${ListCriticism}">
        <TR>
            <TD>Crit  :  <c:out value="${list[1]}"  /></TD>
            <TD>Response  :  <c:out value="${list[2]}"  /></TD>
        </TR>
         <c:if test="${(param.roleId == 1) ||  (param.roleId == 4) }">
            <tr>
             <TD>Responde to Criticism :  <input type="text" id="${list[0]}" name="${list[0]}" value=""> </TD>
             <TD> <input type="button" id="Critrespond" value="Accept" onClick="acceptCriticism(${list[0]})"> </TD>
           </tr>
         </c:if>
      </c:forEach>
    </TABLE> 
</div>

<div id="divComent">
<h1>Comments</h1>
    <TABLE BORDER="1" id="tableComent">
      <c:forEach var="list" items="${ListComments}">
        <TR>
          <TD>comment ID  :  <c:out value="${list[0]}"  /></TD>
          <TD>Author  :  <c:out value="${list[1]}"  /></TD>
          <TD>Role ID  :  <c:out value="${list[2]}"  /></TD>
          <TD>Comment  :  <c:out value="${list[3]}"  /></TD>
          <TD>Response  :  <c:out value="${list[4]}"  /></TD>
        </TR>  
          <c:if test="${param.roleId == 3}">
          <tr>
          <td> Respond to comment..  <input type="text" id="${list[0]}" name="${list[0]}" value=""  ></td>
          <td><input type="button" id="btnComment2" name="btnComment2" value="response" onclick="comResponse(${list[0]})" ></td>
          </tr>
          </c:if>   
      </c:forEach>
    </TABLE> 
</div>
</div>
<div id="close">
<input type="button" value="close window" onclick="window.close()">
</div>
</body>
</html>