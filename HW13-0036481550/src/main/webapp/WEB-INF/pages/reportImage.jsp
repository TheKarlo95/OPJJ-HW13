<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t"%>

<%@ page session="true"%>

<html>
<head>
<title>OS usage</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />">
	<table id="tbl-wrap">
		<tbody>
			<tr>
				<td id="td-wrap"><div id="div-wrap">
						<img src="chart" alt="OS usage pie chart" class="chart">
					</div></td>
			</tr>
		</tbody>
	</table>
</body>
</html>