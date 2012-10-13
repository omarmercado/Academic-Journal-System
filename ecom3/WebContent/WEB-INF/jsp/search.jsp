<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search</title>
<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/form_validation.js"></script>

<script type="text/javascript">
	function validate() {

		var se = document.getElementById('txtSearch').value;

		if (se != '' ) {
			mainForm.submit();
		}else{alert('No special characters on search ');}
	}
</script>

</head>
<body>
<%@include file="header.jsp" %>
<div class="container">
<div class="form-actions">
       <p><h3>Search database :</h3></p>
       
<form id="mainForm" name="mainForm" action="doSearch.htm" method="get">

Search BY <select id="searchOPT" name="searchOPT">
           
            <option value="authors" selected="selected">Author</option>
            <option value="title">Title</option>
          </select>

<input type="text"  id="txtSearch"  name="txtSearch"  value="">
<input type="submit"  id="btnSearch"  name="btnSearch"  value="Search" onclick="validate();return false;">
</form>
<hr/>
<form id="mainForm2"  action="doSearch2.htm" method="get">

Search BY DATE

<input type="text" value="" id="date1"  name="date1">  format "YYYY-MM-DD"
<input type="text" value="" id="date2"  name="date2">  format "YYYY-MM-DD"

<input type="submit"  id="btnSearch"  name="btnSearch"  value="Search">
</form>


</div>

<div id="results">



 
<div id="divAllArticles">
<h5>Results:</h5>
    <TABLE BORDER="1" id="tableAllArticles" width="100%">
      <thead>
      <th>Title</th>
      <th>Name</th>
      <th>Surname</th>
      
      </thead>
      <c:forEach var="AllArticles" items="${response}">
      
      <TR>
  <TD>
  
 <a href="readArticle.htm?ArticleId=<c:out value="${AllArticles[0]}"/>&title=<c:out value="${AllArticles[1]}"/>&Abstract=<c:out value="${AllArticles[2]}"/>&author=<c:out value="${AllArticles[3]}"/>&MainAuthorId=<c:out value="${AllArticles[5]}"/>  "target="_blank"><c:out value="${AllArticles[1]}"/></a>
  </TD>         
           <TD><c:out value="${AllArticles[3]}"  /></TD>
            <TD><c:out value="${AllArticles[4]}"  /></TD>
      </TR>
      </c:forEach>
    </TABLE>
</div>


</div>
<%@include file="footer.jsp" %>
</div><!--  end contianer -->
</body>
</html>