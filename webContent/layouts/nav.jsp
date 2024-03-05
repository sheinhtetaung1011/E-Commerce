<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container-fluid">
	<nav class="container navbar navbar-expand-lg navbar-light bg-light">
		<i class="fa fa-list mymenu" onclick="toggleMenuIcon()"></i>
		<a class="navbar-brand text-blue bg-dark text-white"
			href="http://localhost:8080/E-Commerce/CategoryServlet"> <img
			src="${pageContext.request.contextPath}/assets/imgs/coder.jpg"
			class="rounded" width="30" height="30" />E-Commerce
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse bg-dark"
			id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link text-white "
					href="http://localhost:8080/E-Commerce">Home </a></li>
				<li class="nav-item"><a
					href="http://localhost:8080/E-Commerce/cart.jsp"> <i
						class="my-3 fa fa-shopping-cart text-white"></i> <span
						class="badge badge-danger badge-pill cart-badge" id="cartcount">0</span>
				</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle text-white" href="#"
					id="navbarDropdown" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> Member </a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					
					<c:if test="${!empty user}">
							<a class="dropdown-item bg-dark text-white"
								href="http://localhost:8080/E-Commerce/UserController?action=logout">Logout</a>
						</c:if>
						
						<c:if test="${empty user }">
							<a class="dropdown-item bg-dark text-white"
								href="http://localhost:8080/E-Commerce/UserController?action=login">Login</a>
							<a class="dropdown-item bg-dark text-white"
								href="http://localhost:8080/E-Commerce/UserController?action=register">Register</a>
						</c:if>
					</div></li>
					<li class="nav-item" ><a href="" class="nav-link text-white" >My Orders</a></li>
				
			</ul>

		</div>
	</nav>
</div>