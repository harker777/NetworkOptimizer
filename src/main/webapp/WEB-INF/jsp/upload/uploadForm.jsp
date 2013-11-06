<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task upload form</title>
    </head>

    <body>
	<form:form method="post" enctype="multipart/form-data"  
		   modelAttribute="uploadedFile" action="uploadFile">
	    <input type="file" name="file" />
	    <input type="submit" value="Upload" />
	</form:form>
    </body>
</html>
