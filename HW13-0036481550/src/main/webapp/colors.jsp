<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@ page session="true"%>

<html>
<head>
<title>Background color chooser</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />">
	<div id="content">
		<table id="values-table">
			<thead>
				<tr>
					<th>Color</th>
				</tr>
			</thead>

			<tbody>
				<tr>
					<td><div class="fancy-button"><a href="setBackgroundColor?backgroundColor=white">WHITE</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="setBackgroundColor?backgroundColor=red">RED</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="setBackgroundColor?backgroundColor=green">GREEN</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="setBackgroundColor?backgroundColor=cyan">CYAN</a></div></td>
				</tr>
				<tr>
					<td><div class="fancy-button"><a href="setBackgroundColor?backgroundColor=%23595959">GRAY</a></div></td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>