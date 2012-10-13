<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Java Ecommerce</title>

<%@include file="headercss.jsp" %>


<script src="scripts/jquery.js"></script>
	        
	        <script type="text/javascript">
	      
	        $(document).ready(function(){
	        	
	        	  
	        	var volume = '';
	        	$('#volume').change(function(){
	            volume = document.getElementById('volume').value;
	             
	            
	        	
	        	if(volume != ''){
	        		$('#choose').submit();
	        	}
	        	});
	        	
                
	        	
	        	
	        	
	        	$('#issue').change(function(){
	        		
	        		
	        		 var issue = document.getElementById('issue').value;
	        		 
	        		if(issue != ''){
	        		$('#choose').submit();
	        		}
	        	});
	        });
	        
	        
	        </script>
</head>

<body>
<%@include file="header.jsp" %>
<input type="hidden" id="txtselJournal" name="txtselJournal">
<table>
<tr>
<td>Journal Title  :  </td> <td>${JournalInfo[0]}</td>
</tr>
<tr>
<td>Journal Obj  :  </td> <td>${JournalInfo[1]}</td>
</tr>

</table>


<div id="sidebar">




<form name="choose" id="choose" action="choose.htm">
<input type="hidden" id="selV" name="selV" value="${volume}">


<table id="choose">

<tr>
	<td>
Choose Volume : <select id="volume" name="volume"> 

<option value="">select</option>
 <c:forEach var="volume" items="${listVolume}">
 
<option value="${volume}">${volume}</option>
</c:forEach>
</select>
</td>
</tr>

<tr>
<td>
Choose Issue : <select id="issue" name="issue"> 
<option value="" selected>Select</option>
<option value="0" >All Issues</option>
 <c:forEach var="issue" items="${listIssue}">
<option value="${issue}">${issue}</option>
</c:forEach>

</select>
</td>
</table>
</form>
</div>

<div class="container">
<div id="content">
<div id="divAllArticles">
<h1>All Available Articles</h1>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
<br/>
    <TABLE BORDER="1" id="tableAllArticles">
      <c:forEach var="AllArticles" items="${ListAllArticles}">
      <TR>   
  <TD><a href="readArticle.htm?ArticleId=<c:out value="${AllArticles[0]}"/>&title=<c:out value="${AllArticles[4]}"/>&Abstract=<c:out value="${AllArticles[3]}"/>&author=<c:out value="${AllArticles[6]}"/>&MainAuthorId=<c:out value="${AllArticles[7]}"/>"  target="_blank"><c:out value="${AllArticles[4]}"/></a></TD>         
          <TD><c:out value="${AllArticles[6]}"  /></TD>
      </TR>
      </c:forEach>
    </TABLE>
</div>

</div><!--  end div content -->
<%@include file="footer.jsp" %>
</div><!-- end div container -->
</body>
</html>