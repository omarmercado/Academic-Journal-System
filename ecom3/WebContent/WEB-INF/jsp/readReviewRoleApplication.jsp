<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Apply Review Role</title>

<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>

	        
	        <script type="text/javascript">
	        
	            $(document).ready(function(){
	
	            	$('#txtId').val("${param.id}");
	            	$('#txtfirstName').val("${param.firstName}");
	            	$('#txtlastName').val("${param.lastName}");
	            	$('#txtemail').val("${param.email}");
	            	$('#txtmsg').val("${param.msg}");
	   
	            	
  	$('#btnAccept').click(function(){

  		
	            		 $.ajax({
	 	         	        url: 'acceptNewReviewer.htm',
	 	         	        data: ({id : ${param.id}}),
	 	         	        success: function(data) {
	 	         	        	 alert('User has Been Accepted');
	
	 	         	        	
	 	         	        }
	 	         	      });
	            		
	            	});
  	
  	
  	$('#btnReject').click(function(){
  		
		 $.ajax({
  	        url: 'rejectNewReviewer.htm',
  	      data: ({id : ${param.id} }),  	        
  	      success: function(data) {

  	    	  alert('User has Been Rejected');

  	        }
  	      });
		
	});
	            	
	            });

	        </script>
</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container" style="width=1150px;">
<table>
<tr>
  <td>
    firstName :  <input type="text" value="" id="txtfirstName">
  </td>
</tr>
<tr>
  <td>
    lastName  :  <input type="text" value="" id="txtlastName">
  </td>
</tr>
<tr>
  <td>
    email : <input type="text" value="" id="txtemail">
  </td>
</tr>

<tr>
  <td>
    Message To Editor : <input type="text" value="" id="txtmsg">
  </td>
</tr>

<tr>
  <td>
   <input type="button" id="btnAccept" value="Accept">
  </td>
  <td>
   <input type="button" id="btnReject" value="Reject">
  </td>
</tr>
<tr>
  <td>
   
  </td>
</tr>

</table>
<div id="close">
	<input type="button" value="close window" onclick="window.close()">
</div>
</div>
</body>
</html>