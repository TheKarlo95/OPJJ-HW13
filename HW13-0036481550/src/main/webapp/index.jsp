<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t" %>


<%@ page session="true"%>

<html>
<head>
<title>Welcome</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />">
<div id="content">
		<table id="values-table">
			<tbody>
				<tr>
					<td><div class="fancy-button"><a href="colors.jsp">BACKGROUND COLOR CHOOSER</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="trigonometric?a=0&b=90">TRIGONOMETRIC VALUES</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="stories/funny.jsp">FUNNY STORY</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="reportImage">OS USAGE CHART</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="power?a=1&b=100&n=3">EXCEL SPREADSHEET</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="appinfo.jsp">APP INFO</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="glasanje">VOTING</a></div></td>
				</tr>
			</tbody>
		</table>
	</div>
</html>
