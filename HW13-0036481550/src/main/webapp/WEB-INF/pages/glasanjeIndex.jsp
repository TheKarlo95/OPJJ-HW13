<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="t"%>


<%@ page session="true"%>

<html>
<head>
<title>Welcome</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />">
	<div id="voting">
		<h1>Glasanje za omiljeni bend:</h1>
		<p>Od sljedećih bendova, koji Vam je bend najdraži? Kliknite na
			link kako biste glasali!</p>
		<table id="voting-table">
			<tbody>
				<c:forEach var="nom" items="${nominees}">
					<tr>
						<td>
							<div class="fancy-button">
								<a href="${nom.voteLink}">${nom.name}</a>
							</div>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</html>
