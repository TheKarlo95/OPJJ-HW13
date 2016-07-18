<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="t" %>

<%@ page session="true"%>

<%
    java.util.Random rand = new java.util.Random();

    java.awt.Color text = new java.awt.Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));

    String red = Integer.toHexString(text.getRed());
    String green = Integer.toHexString(text.getGreen());
    String blue = Integer.toHexString(text.getBlue());

    String textColor = "#" +
            (red.length() == 1 ? "0" + red : red) +
            (green.length() == 1 ? "0" + green : green) +
            (blue.length() == 1 ? "0" + blue : blue);
%>

<html>
<head>
<title>Funny Story</title>
<link href="<c:url value="css/style.css" />" rel="stylesheet">
</head>

<body bgcolor="<t:getbackground />">
<pre style="color:<%=textColor%>">
A programmer had a problem.
He said, "Oh I know, I'll solve it recursively".
	He said, "Oh I know, I'll solve it recursively".
		He said, "Oh I know, I'll solve it recursively".
			He said, "Oh I know, I'll solve it recursively".
				He said, "Oh I know, I'll solve it recursively".
					.
					.
					.
					.
					.
					.
					.
After sometime, the programmer crashed and died because of overflow of problems.
</pre>
</body>
</html>