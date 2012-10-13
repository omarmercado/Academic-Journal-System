<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ page import="org.ecom.journalBeans.emailSender" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Spring MVC Ajax Demo</title>
  <script type="text/javascript" src="jquery.js"></script>
  <script type="text/javascript">
    function doAjax() {
      $.ajax({
        url: 'time.html',
        data: ({name : "me"}),
        success: function(data) {
          $('#time').html(data);
        }
      });
    }
  </script>
</head>
<body>


<%

emailSender e = new emailSender();
//e.newMail();

%>

<button id="demo" onclick="doAjax()" title="Button">Get the time!</button>
<div id="time">
</div>
</body>
</html>