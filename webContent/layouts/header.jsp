<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Insert title here</title>

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<link rel="icon"
	href="${pageContext.request.contextPath}/assets/imgs/coder.jpg" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/custom.css" />

</head>
<body>
	<c:import url="layouts/nav.jsp" />
	<c:import url="layouts/sidebar.jsp"/>
	
	<div class="container text-center col-md-4 my-5">
		<c:if test="${!empty msgerror }">
			<div class="alert alert-warning alert-dismissible fade show"
				role="alert">
				<strong>${msgerror }</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

		</c:if>


		<c:if test="${!empty msgsuccess }">
			<div class="alert alert-warning alert-dismissible fade show"
				role="alert">
				<strong>${msgsuccess}</strong>
				<button type="button" class="close" data-dismiss="alert"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>

		</c:if>

		<%
		request.removeAttribute("msgerror");
		request.removeAttribute("msgsuccess");
		%>

	</div>