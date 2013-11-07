<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Task</title>
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

			<c:forEach items="${task.connectionCollection}" var="connection">
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

			<tr>
				<td> Solutions </td>
			</tr>

			<c:forEach items="${task.solutionCollection}" var="solution" varStatus="index">
				<tr>
					<td colspan="4">
						Solution (${index.count})
					</td>
				</tr>

				<c:forEach items="${solution.connectionCollection}" var="connection">
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
			</c:forEach>

		</table>
    </body>
</html>
