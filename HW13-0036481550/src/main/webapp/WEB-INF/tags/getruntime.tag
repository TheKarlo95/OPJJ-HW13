<%@ tag description="Gets background color from the session attributes."
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    java.time.LocalDateTime startDate = (java.time.LocalDateTime) session.getServletContext()
            .getAttribute("timeStarted");
    java.time.LocalDateTime now = java.time.LocalDateTime.now();

    long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, now);
    long hours = java.time.temporal.ChronoUnit.HOURS.between(startDate, now) % 24;
    long minutes = java.time.temporal.ChronoUnit.MINUTES.between(startDate, now) % 60;
    long seconds = java.time.temporal.ChronoUnit.SECONDS.between(startDate, now) % 60;

    String runtime = "";

    if (days > 0 || !runtime.isEmpty()) {
        runtime += days + " days, ";
    }
    if (hours > 0 || !runtime.isEmpty()) {
        runtime += hours + " hours, ";
    }
    if (minutes > 0 || !runtime.isEmpty()) {
        runtime += minutes + " minutes, ";
    }
    if (seconds > 0) {
        runtime += seconds + " seconds, ";
    }

    runtime = runtime.substring(0, runtime.length() - 2);
%>

<c:out value="<%=runtime%>" />