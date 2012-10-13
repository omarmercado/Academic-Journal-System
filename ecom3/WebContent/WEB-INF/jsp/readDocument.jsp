<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Read Document</title>

<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/dropdown.js"></script>
<script src="scripts/tabs.js"></script>
	        
	        <script type="text/javascript">
	        
	        function respondCriticism(val){
		        
	        	var response = document.getElementById(val).value
	       
	        	alert(response);
	        	
	     //   	 $.ajax({
	      //   	        url: 'crtResponse.htm',
	       //  	        data: ({submissionId : ${param.id},response : response,crtcismId : val }),
	        // 	        success: function(data) {
	        // 	       	
	        // 	        }
	        // 	      });
	        	 
	        }
	        
	            $(document).ready(function(){
	        	    
	            	$('#txtId').val("${param.id}");
	            	$('#txtTitle').val("${param.title}");
	            	$('#txtAuthor').val("${param.author}");
	            	$('#txtAbstract').val("${param.Abstract}");
	            	
	            	
	            	
	            	
	            	if(${fn:length(ListCriticism)} == 0){$('#divCriticism').css('display','none')}
	            	if(${fn:length(ListComments)} == 0){$('#divComments').css('display','none')}
                     
	            	if(${roleId} == 1){
	            		$("#divComments").css('display','none');   		
	            	}


	            	
	            });

	        </script>
</head>
<body>
<%@include file="adminheader.jsp" %>
   <div class="container" style="width=1150px;">
   
<table>
<tr>
  <td>
    id :  
  </td>
  <td><input type="text" value="" id="txtId"></td>
</tr>
<tr>
  <td>
    Titile :  
  </td>
  <td><input type="text" value="" id="txtTitle"></td>
</tr>
<tr>
  <td>
    Author  :  
  </td>
  <td><input type="text" value="" id="txtAuthor"></td>
</tr>
<tr>
  <td>
    Abstract : 
  </td>
  <td>
  <!--  <input type="text" value="" id="txtAbstract">  --> 
    <textarea id="txtAbstract" name="txtAbstract" rows="15" cols="20" readonly="readonly"></textarea>
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
  
            <td> Respond to criti..  <a href="reSubmit.htm?id=${param.id}&title=${param.title}&Abstract=${param.Abstract}&userID=${param.userId}">Respond to Criticism</a></td>
        
    </tr>
    </TABLE> 
</div>


<div id="divComments">
<h1>Comments</h1>
    <TABLE BORDER="1" id="tableComments">
      <c:forEach var="Comments" items="${ListComments}">
      <TR>
            <TD>Crit  :  <c:out value="${Comments[1]}"  /></TD>
            <TD>Response  :  <c:out value="${Comments[2]}"  /></TD>
            <TD>Crit  :  <c:out value="${Comments[3]}"  /></TD>
            <TD>Response  :  <c:out value="${Comments[4]}"  /></TD>
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