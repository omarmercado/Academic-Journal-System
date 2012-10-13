<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Information</title>

<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>
	        
	        <script type="text/javascript">
	        
	            $(document).ready(function(){
	            	$('#txtuserId').val("${param.userId}");
	            	$('#txtfirstName').val("${param.firstName}");
	            	$('#txtlastName').val("${param.lastName}");
	            	$('#txtemail').val("${param.email}");
	            	$('#txtroleName').val("${param.roleName}");
	            	
	            	$('#txtuserName').val("${param.userName}");
	            	$('#txtpassWord').val("${param.passWord}");
	            	
	            	
                   	$('#Update').click(function(){
	            		
	            		var userId = document.getElementById("txtuserId").value ;
	            		var firstName = document.getElementById("txtfirstName").value ;
	            		var lastName = document.getElementById("txtlastName").value ;
	            		var email = document.getElementById("txtemail").value ;
	            		var userName = document.getElementById("txtuserName").value ;
	            		var passWord = document.getElementById("txtpassWord").value ;
	            		var roleName = "${param.roleId}";
	            		
	            		
	            		
	            		
	            		if (firstName !='') {

		            		
	            			if (lastName !='') {
	            				
	            				if (email_regex.test(email)) {
		            				
	            					if (username_regex.test(passWord)) {
			            				


	           	            		 $.ajax({
	           	 	         	        url: 'updateUser.htm',
	           	 	         	        data: ({userId : userId,firstName : firstName, lastName : lastName, email : email, roleName : roleName,userName : email, passWord : passWord }),
	           	 	         	        success: function(data) {
	           	 	         	       alert("user Update Succesfull");
	           	
	           	 	         	        }
	           	 	         	      });
			            				
			            			}else{    alert('Check passWord : No Blank Input, No Special Characters');  }
		            				
		            				
		            			}else{    alert('Invalid E-MAIL format');  }
	            				
	            				
	            			}else{    alert('Check Last Name : No Blank Input, No Special Characters');  }
	            			
	            			

	            		} else {alert('Check First Name : No Blank Input, No Special Characters');}

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
  </td>
  <td><input type="hidden" value="" id="txtuserId"></td>
</tr>
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

  </td>
  <td><input type="hidden" value="" id="txtroleName" disabled></td>
</tr>
<tr>
  <td>
    userName : 
  </td>
  <td><input type="text" value="" id="txtuserName" disabled></td>
</tr>
<tr>
  <td>
   
  </td>
  <td><input type="hidden" value="" id="txtpassWord"></td>
</tr>
<tr>
  <td>
   <input type="button" id="Update" value="Update User">
  </td>
  <td>
  <div id="close">
<input type="button" value="close window" onclick="window.close()">
</div>
  </td>
</tr>
</table>


</div>	         	   
</body>
</html>