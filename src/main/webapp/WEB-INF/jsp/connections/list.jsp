<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
	<table>
	    <tr>
		<td>
		    ID:
		</td>
		<td>
		    Starting point:
		</td>
		<td>
		    Ending point:
		</td>
		<td>
		    Delay:
		</td>
	    </tr>

	    <c:forEach items="${connections}" var="connection">
		<tr>
		    <td>
			${connection.id}
		    </td>
		    <td>
			${connection.startNodeName}
		    </td>
		    <td>
			${connection.endNodeName}
		    </td>
		    <td>
			${connection.delay}
		    </td>
		</tr>
	    </c:forEach>
	</table>
    </body>
</html>
