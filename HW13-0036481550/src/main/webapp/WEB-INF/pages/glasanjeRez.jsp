<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t"%>


<%@ page session="true"%>

<html>
<head>
<title>Results</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />">
	<div id="content">
		<table id="no-hover-table">
			<thead>
				<tr>
					<th colspan="2">Vote results</th>
				</tr>
				<tr>
					<th>Name</th>
					<th>Vote count</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="n" items="${nominees}">
					<tr>
						<td>${n.name}</td>
						<td>${n.numOfVotes}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<div id="vote-chart">
		<table id="chart-table">
			<thead>
				<tr bgcolor="#666666">
					<th>Pie-chart</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td><img src="glasanje-grafika" alt="Pie-chart" class="chart"></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="vote-content">
		<table id="no-hover-table">
			<thead>
				<tr>
					<th>Results in XLS format</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td>Results in XLS format are available <a href="glasanje-xls">here</a></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div id="vote-content">
		<table id="no-hover-table">
			<thead>
				<tr>
					<th>Winner:</th>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="win" items="${winners}">
					<tr>
						<td><a href="${win.link}">${win.name}</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</html>