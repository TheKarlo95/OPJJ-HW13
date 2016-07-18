<%@page import="java.io.IOException"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@ page session="true"%>

<html>
<head>
<title>Power excel spreadsheet generator fail</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />" id="error">

	<h1>Illegal or missing parameter - /webapp2/power</h1>
	<div class="line"></div>
	<p>
		<b>Parameter:</b> ${param.name}
	</p>
	
	<c:choose>
		<c:when test="${param.cause == 'null'}">
			<p>
				<b>Error type:</b> Parameter ${param.name} doesn't exist
			</p>
		</c:when>
		<c:when test="${param.cause == 'nfe'}">
			<p>
				<b>Error type:</b> Parameter ${param.name} isn't integer
			</p>
		</c:when>
		<c:when test="${param.cause == 'tooSmall'}">
			<p>
				<b>Error type:</b> Parameter ${param.name} is smaller than minimum(${param.bound})
			</p>
		</c:when>
		<c:when test="${param.cause == 'tooBig'}">
			<p>
				<b>Error type:</b> Parameter ${param.name} is bigger than maximum(${param.bound})
			</p>
		</c:when>
	</c:choose>
</body>
</html>