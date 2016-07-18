<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@ page session="true"%>

<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.temporal.ChronoUnit"%>

<html>
<head>
<title>App info</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />">
	<div id="content">
		<table id="no-hover-table">
			<thead>
				<tr>
					<th>App info</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td><b>Runtime:</b> <t:getruntime /></td>
				</tr>
			</tbody>
		</table>
	</div>
</html>