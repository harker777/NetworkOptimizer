<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tasks:</title>
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
	    </tr>

	    <c:forEach items="${tasks}" var="task">
		<tr>
		    <td>
			${task.id}
		    </td>
		    <td>
			${task.startNodeName}
		    </td>
		    <td>
			${task.endNodeName}
		    </td>
		    <td>
			<a href="tasks/${task.id}"> Details </a>
		    </td>
		</tr>
	    </c:forEach>
	</table>

	<a href="uploadForm"> Upload new task </a>
    </body>
</html>
