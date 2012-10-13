<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    	<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>User Sign Up </h3>
<table>
<form name="frmSignUp" method="get" action="doSignUp.htm">
<tr>
<td>First Name </td>
  <td>
      <input type="text" id="txtfirstName" name="txtfirstName">
  </td>
</tr>
<tr>
<td>Last Name </td>
   <td>
      <input type="text" id="txtlastName" name="txtlastName">
   </td>
</tr>
<tr>
<td>Email </td>
   <td>
      <input type="text" id="txtemail" name="txtemail">
   </td>
   </tr>
<tr>
<td>User Name </td>
<td>
<input type="text" id="txtuserName" name="txtuserName">
</td>
</tr>
<tr>
<td>Password </td>
     <td>
        <input type="text" id="txtpassword" name="txtpassword">
    </td>
</tr>
<tr>
<td>Role </td>
     <td>
        <input type="text" id="txtrole" name="txtrole" value="Author">
    </td>
</tr>
<tr>
     <td>
           <input type="submit" value="Sign Up">
    </td>
</tr>
</form>

</table>
${response}
</body>
</html>