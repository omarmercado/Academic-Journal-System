<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Re Submit Article</title>
<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>
	        
	        <script type="text/javascript">
	        
	        function validate() {
	        	
	        	
	        	var title = document.getElementById('txtTitle').value;
	    		var Abstract = document.getElementById('txtAbstract').value;
	    		
	    		
	    		
	    		if(title !=''){
	    			if(Abstract !='' &&  Abstract.length < 256){
	    				
	    				
	    				document.forms["formReSubmit"].submit();
	    				
	    			}else{alert('Abstract cant be blank and no more than 255 characters');}	
	    			
	    		}else{alert('title cant be blank');}
	        }
	        
	        function respondCriticism(val){
		     
	        	var response = document.getElementById(val).value
	        	var submissionId = document.getElementById("txtSubmissionId").value
	       
	        	 $.ajax({
	         	        url: 'crtResponse.htm',
	         	        data: ({submissionId : submissionId,response : response,crtcismId : val }),
	         	        success: function(data) {
	         	        	alert('response has been sent to the Reviewer');
	         	        }
	         	      });
	        	 
	        }
	        
	            $(document).ready(function(){
	        	    
	            	$('#txtSubmissionId').val("${param.id}");
	            	$('#txtTitle').val("${param.title}");
	            	$('#txtAbstract').val("${param.Abstract}");
	            	
	            	$('#txtUserID').val("${param.userID}");
	            	
	            	if(${fn:length(ListCriticism)} == 0){
	            		$('#divCriticism').css('display','none')
	            	}
	            });

	        </script>
	        
	        
</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container" style="width=1150px;">

<form id="formReSubmit" action="reSubmission.htm">
<table id="tableReSubmit">
  <tr>
    <td>
      Title
    </td> 
    <td>
      <input type="hidden" id="txtUserID" name="txtUserID" value="">
      <input type="hidden" id="txtSubmissionId" name="txtSubmissionId" value="">
      <input type="text" id="txtTitle" name="txtTitle" value="">
    </td>
  </tr>
  <tr>
    <td>
      Abstract
    </td>
    <td>
      <!--  <input type="text" id="txtAbstract" name="txtAbstract" value="">  -->
      <textarea id="txtAbstract" name="txtAbstract" rows="15" cols="20" ></textarea>max. 255 Characters
    </td>
  </tr>
  <tr>
    <td>
      <input type="submit"  value="Submit Article" onclick="validate();return false;">
    </td>
  </tr>
  <tr>
    <td>
      ${response}
      
      <c:if test="${param.id != '' }">
        <a href="upload.htm?submissionId=${param.id}"target="_blank">upload file</a>
      </c:if>
       <c:if test="${id != '' }">         
        <a href="upload.htm?submissionId=${id}"target="_blank">upload file</a>
      </c:if>
    </td>
  </tr>
</table>	
</form>   


<div id="divCriticism">
<h1>Criticism</h1>
    <TABLE BORDER="1" id="tableCriticism">
      <c:forEach var="list" items="${ListCriticism}">
      <TR>
            <TD>Crit  :  <c:out value="${list[1]}"  /></TD>
            <TD>Response  :  <c:out value="${list[2]}"  /></TD>
            <TD> <input type="text" id="${list[0]}" name="${list[0]}" value=""> </TD>
            <TD> <input type="button" id="Critrespond" value="respond" onClick="respondCriticism(${list[0]})"> </TD>
      </TR>
      </c:forEach>
    </TABLE> 
</div>

<div id="close">
<input type="button" value="close window" onclick="window.close()">
</div>
</div>
</body>
</html>