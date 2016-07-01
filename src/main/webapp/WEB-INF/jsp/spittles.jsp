<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spittle</title>
</head>
<body>
	<c:forEach items="${spittleList}" var="spittle">
		<li id="spittle_<c:out value="spittle.id"/>">
			<div class="spittleMessage">
				<c:out value="${spittle.message}" />
			</div>
			<div>
				<span class="spittleTime"><c:out value="${spittle.time}" /></span>
				<span class="spittleLocation"> (<c:out
						value="${spittle.latitude}" />, <c:out
						value="${spittle.longitude}" />)
				</span>
			</div>
		</li>
	</c:forEach>
</body>
</html>