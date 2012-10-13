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
<script src="scripts/form_validation.js"></script>
	        
	        <script type="text/javascript">
	        
	            $(document).ready(function(){
	
                   	$('#btnApply').click(function(){
	            		
	            		var firstName = document.getElementById("txtfirstName").value ;
	            		var lastName = document.getElementById("txtlastName").value ;
	            		var email = document.getElementById("txtemail").value ;
	            		var msg = document.getElementById("txtmsg").value ;

	            		if(firstName != ''){
	            		
	            		if(email_regex.test(email)){
	            			 $.ajax({
	 	 	         	        url: 'applyReviewRoleForm.htm',
	 	 	         	        data: ({firstName : firstName, lastName : lastName, email : email, msg : msg }),
	 	 	         	        success: function(data) {
	 	 	         	       
	 	 	         	        	alert('application succesful');
	 	 	         	        }
	 	 	         	      });           			
	            		}else{alert('invlaid email');}
	            		}else{alert('first name cant be blank');}

	            		
	            });
	            });

	        </script>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<div class="well">
<table>
<tr>
  <td>
    firstName :  
  </td>
  <td><input type="text" value="" id="txtfirstName"></td>
</tr>
<tr>
  <td>
    lastName  :  
  </td>
  <td><input type="text" value="" id="txtlastName"></td>
</tr>
<tr>
  <td>
    email : 
  </td>
  <td><input type="text" value="" id="txtemail"></td>
</tr>

<tr>
  <td>
    Message To Editor : 
  </td>
  <td><input type="text" value="" id="txtmsg"></td>
</tr>

<tr>
  <td>
   <input type="button" id="btnApply" value="Apply">
  </td>
  <td></td>
</tr>
<tr>
  <td>
   
  </td>
</tr>

</table>
</div>
<%@include file="footer.jsp" %>
</div>
</body>
</html>