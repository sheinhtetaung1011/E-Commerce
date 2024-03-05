<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="layouts/header.jsp"></c:import>

<div class="container col-md-3">
	<h1 class="text-center my-5">Please Register.</h1>
	<form method="post"
		action="http://localhost:8080/E-Commerce/UserController?action=registerform" autocomplete="off">
		<div class="form-group">
			<label for="name">UserName</label><br> <input type="text"
				name="name" id="name">
		</div>
		<div class="form-group">
			<label for="email">Email address</label> <input type="email"
				class="form-control" id="email" name="email">

		</div>
		<div class="form-group">
			<label for="password">Password</label> <input type="password"
				class="form-control" id="password" name="password">
		</div>

		<div class="row justify-content-between">
			<button type="submit" class="btn btn-primary ">Register</button>
			<a href="./login.jsp">Already a member.GO to Login!</a>
		</div>
	</form>
</div>
<c:import url="layouts/jsgp.jsp" />
<c:import url="layouts/footer.jsp" />