<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
        
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Respond to Comments</title>


<%@include file="headercss.jsp" %>

<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>
<script src="scripts/jquery.js"></script>
<script src="scripts/form_validation.js"></script>
	        
	        <script type="text/javascript">

	        function comResponse(val){
                       var response = document.getElementById(val).value;
                       
                       
                   if ( response != '' ) {
                	   
                  	 $.ajax({
              	        url: 'ComResponse.htm',
              	        data: ({commentId : val, response: response }),
              	        success: function(data) {
              	       
              	          alert('response has been sent to the Reviewer');
              	        }
              	      }); 
               		
               			
               			
               		}else{alert('NNo blank input');}

	        }
	        

	            $(document).ready(function(){
	        	    
	            	$('#txtId').val("${param.id}");// submissionId
	            	$('#txtTitle').val("${param.title}");
	            	$('#txtAuthor').val("${param.author}");
	            	$('#txtAbstract').val("${param.Abstract}");

   	               
   	            if(${fn:length(ListComments)} == 0){
            		
            		$('#divComent').css('display','none')
            	}
   	            
   	            
   	

	            	
	             	$('#btnComment').click(function(){
	             		var comment = document.getElementById("txtComment").value

		            	 $.ajax({
		         	        url: 'Comment.htm',
		         	        data: ({submissionId : ${param.id},comment : comment, userId :${param.userId},roleId : '2' }),
		         	        success: function(data) {
		         	       
		         	        	document.getElementById("txtComment").value = '';
		         	        }
		         	      });
		            	});
	 
	            });
	            

	  
	            
	            

	        </script>
</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container">

<table>
<tr>
  <td>
  <input type="hidden" id="txtReviewId">
  <input type="hidden" value="" id="txtId" readonly>
  </td>
</tr>
<tr>
  <td>
    Titile :  <input type="text" value="" id="txtTitle" readonly>
  </td>
</tr>
<tr>
  <td>
    Author  :  <input type="text" value="" id="txtAuthor" readonly>
  </td>
</tr>
<tr>
  <td>
    Abstract : <input type="text" value="" id="txtAbstract" readonly>
  </td>
</tr>

</table>
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

<div id="divComent">
<h1>Comments</h1>
    <TABLE BORDER="1" id="tableComent">
      <c:forEach var="list" items="${ListComments}">
        <TR>
          <TD>Author  :  <c:out value="${list[1]}"  /></TD>
          <TD>Comment  :  <c:out value="${list[3]}"  /></TD>
          <TD>Response  :  <c:out value="${list[4]}"  /></TD>
        </TR>  
          <tr>
          <td> Respond to comment..  <input type="text" id="${list[0]}" name="${list[0]}" value=""  ></td>
          <td><input type="button" id="btnComment2" name="btnComment2" value="response" onclick="comResponse(${list[0]})" ></td>
          </tr>
      </c:forEach>
    </TABLE> 
</div>
</div>

<div id="close">
	<input type="button" value="close window" onclick="window.close()">
</div>
	 	         	   
</body>
</html>