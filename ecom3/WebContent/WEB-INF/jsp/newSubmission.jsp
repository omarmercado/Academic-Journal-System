<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page session="true" %>
	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create New Article</title>
<%@include file="headercss.jsp" %>
<script src="scripts/jquery.js"></script>
<script src="scripts/form_validation.js"></script>
<script type="text/javascript">
	function validate() {

		
		var title = document.getElementById('txtTitle').value;
		var Abstract = document.getElementById('txtAbstract').value;
		

		
		//Main Author
		var name = document.getElementById('txtMAfName').value;
		var last = document.getElementById('txtMALName').value;
		var Email = document.getElementById('txtMAEmail').value;
		
		//User 2
		var name2 = document.getElementById('txtU2fName').value;
		var last2 = document.getElementById('txtU2LName').value;
		var Email2 = document.getElementById('txtU2Email').value;
		
		//User3
		var name3 = document.getElementById('txtU3fName').value;
		var last3 = document.getElementById('txtU3LName').value;
		var Email3 = document.getElementById('txtU3Email').value;
		
		//User4
		var name4 = document.getElementById('txtU4fName').value;
		var last4 = document.getElementById('txtU4LName').value;
		var Email4 = document.getElementById('txtU4Email').value;
		
		if ( title != '' ) {
			if ( Abstract != '' &&  Abstract.length < 256) {
				if ( name != '' ) {
					if (last != '' ) {
						if ( email_regex.test(Email) ) {
		
					if(Email2 != ''){
						if (name2 !='' ) {
							if ( last2 != '' ) {
								if ( email_regex.test(Email2) ) {
									
									
									if(Email3 != '' ){
										if ( name3 != '' ) {
											if ( last3 != '' ) {
												if ( email_regex.test(Email3) ) {
													
													if(Email4 != '' ){
														if ( name4 != '' ) {
															if ( last4 != '' ) {
																if ( email_regex.test(Email4) ) {
																	

																	document.forms["formNewArticle"].submit();
																	
																}else{ alert('Author # 4 Email cant be blank, No special characters, Not a valid Email');  return false;}
															}else{ alert('Author # 4 Last Name cant be blank, No special characters');  return false;}
														}else{ alert('Author # 4 First Name cant be blank, No special characters');  return false;}
													
													}else{document.forms["formNewArticle"].submit();}
													
													
													
												}else{ alert('Author # 3 Email cant be blank, No special characters, Not a valid Email');  return false;}
											}else{ alert('Author # 3 Last Name cant be blank, No special characters');  return false;}
										}else{ alert('Author # 3 First Name cant be blank, No special characters');  return false;}
									}else if(Email4 != '' ){
										if ( name4 != '' ) {
											if ( last4 != '' ) {
												if ( email_regex.test(Email4) ) {
													
													document.forms["formNewArticle"].submit();
						
												}else{ alert('Author # 4 Email cant be blank, No special characters, Not a valid Email');  return false;}
											}else{ alert('Author # 4 Last Name cant be blank, No special characters');  return false;}
										}else{ alert('Author # 4 First Name cant be blank, No special characters');  return false;}
									
									}else{document.forms["formNewArticle"].submit();}
									
									
								}else{ alert('Author # 2 Email cant be blank, No special characters, Not a valid Email');  return false;}
							}else{ alert('Author # 2 Last Name cant be blank, No special characters');  return false;}
						}else{ alert('Author # 2 First Name cant be blank, No special characters');  return false;}
					}else if(Email3 != '' ){
						if ( name3 != '' ) {
							if ( last3 != '' ) {
								if ( email_regex.test(Email3) ) {
									
									if(Email4 != '' ){
										if ( name4 != '') {
											if ( last4 !='' ) {
												if ( email_regex.test(Email4) ) {
													
													document.forms["formNewArticle"].submit();
						
												}else{ alert('Author # 4 Email cant be blank, No special characters, Not a valid Email');  return false;}
											}else{ alert('Author # 4 Last Name cant be blank, No special characters');  return false;}
										}else{ alert('Author # 4 First Name cant be blank, No special characters');  return false;}

									}else{
										document.forms["formNewArticle"].submit();
									}
									
									
									
								}else{ alert('Author # 3 Email cant be blank, No special characters, Not a valid Email');  return false;}
							}else{ alert('Author # 3 Last Name cant be blank, No special characters');  return false;}
						}else{ alert('Author # 3 First Name cant be blank, No special characters');  return false;}
					}else if(Email4 != '' ){
						if ( name4 != '' ) {
							if ( last4 !='' ) {
								if ( email_regex.test(Email4) ) {
									
									document.forms["formNewArticle"].submit();
		
								}else{ alert('Author # 4 Email cant be blank, No special characters, Not a valid Email');  return false;}
							}else{ alert('Author # 4 Last Name cant be blank, No special characters');  return false;}
						}else{ alert('Author # 4 First Name cant be blank, No special characters');  return false;}

					}else{
						document.forms["formNewArticle"].submit();
					}

						}else{alert('Not a valid Email for Main Author');}
					}else{alert('Main Auhtor Last Name cant be blank, No special characters');}
				}else{alert('Main Auhtor Name cant be blank, No special characters');}
			}else{alert('Abstract cant be blank, No special characters And has to be less that 255 Characters');}
		}else{alert(title+'Title cant be blank, No special characters');}
	}
</script>

</head>

<body>
<%@include file="header.jsp" %>
<div class="container">



<form id="formNewArticle" name="formNewArticle" action="doSubmission.htm">
<span class="label label-info">
First Read the Submission Guidelines :  <a href="http://localhost:8080/ecom3/SubmissionGuidelines.jsp"target="_blank">Submission Guidelines</a>
</span>
<br/><br/><br/>

<span class="label label-info">
Submission to be considered are expected to be in this format :  <a href="http://localhost:8080/ecom3/files/template.doc"target="_blank">Download Submission Template</a>
</span>
<br/>
<br/><br/>

<table id="tableNewArticle" >



<tr><td>Article Title</td> <td>
<input type="text" id="txtTitle" name="txtTitle" value="">
</td></tr>
<tr><td>Article Abstract</td><td>
 <!--  <input type="text" id="txtAbstract" name="txtAbstract" value=""  maxlength="255">   -->
<textarea id="txtAbstract" name="txtAbstract" rows="15" cols="20"></textarea>Max 255 characters

</td></tr>


</table>
<table id="tableNewArticle2" >

<tr><td><h3>USERS</h3></td><td>




</td></tr>



 <tr>
  <td>Main Author ID</td>
 <td>First Name : <input type="text" id="txtMAfName" name="txtMAfName"></td>
 <td>Last Name : <input type="text" id="txtMALName" name="txtMALName"></td>  
 <td>Email : <input type="text" id="txtMAEmail" name="txtMAEmail"></td>
</tr>

<tr>
  <td>User 2</td>
<td>First Name :  <input type="text" id="txtU2fName" name="txtU2fName"></td>
<td>Last Name :  <input type="text" id="txtU2LName" name="txtU2LName"></td>  
<td>Email   <input type="text" id="txtU2Email" name="txtU2Email"></td>
</tr>

<tr>
  <td>User 3</td>
<td>First Name :  <input type="text" id="txtU3fName" name="txtU3fName"></td>
<td>Last Name :  <input type="text" id="txtU3LName" name="txtU3LName"></td>  
<td>Email   <input type="text" id="txtU3Email" name="txtU3Email"></td>
</tr>

<tr>
  <td>User 4</td>
<td>First Name :  <input type="text" id="txtU4fName" name="txtU4fName"></td>
<td>Last Name :  <input type="text" id="txtU4LName" name="txtU4LName"></td>  
<td>Email   <input type="text" id="txtU4Email" name="txtU4Email"></td>
</tr>



<tr><td>Keyword</td><td>
Keywords :<input type="text" id="txtKeywords" name="txtKeywords" >
</td></tr>
<td><span class="label label-info">Please separate keywords with comma ","</span></td>
<tr>
<td></td>

<td>
<input type="submit"  value="Submit Article" onclick="validate();return false;">
</td>

</tr>



</table>	 
</form>

<c:if test="${response == 'NO'}">
  <div>
      Main Author has a previous Submission on Proccess
    </div>
</c:if>



<c:if test="${response == 'This User Role Cant Publish Articles'}">
  <div>
      This User Role Cant Publish Articles
    </div>
</c:if>

<c:if test="${response == 'OK'}">
    <div>
      Submission Has been Succesful, You Will Receive an Email with User and Password Information
      <br><br>
      Please Upload Your File To Complete the Submission
      <br><br><br>
    </div>
    <table>
      <tr>
        <td>
        <span class="label label-info">
          <a href="upload.htm?submissionId=${submissionId}"target="_blank">upload file</a>
        </span>
        </td>
      </tr>
    </table>      
</c:if>

<%@include file="footer.jsp" %>
</div>
</body>
</html>