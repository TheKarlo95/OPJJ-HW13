<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@ page session="true"%>

<html>
<head>
<title>Trigonometric table</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />">
	<div id="content">
		<table id="values-table">
			<thead>
				<tr>
					<th>x</th>
					<th>sin(x)</th>
					<th>cos(x)</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="v" items="${values}">
					<tr>
						<td>${v.x}</td>
						<td>${v.sin}</td>
						<td>${v.cos}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>