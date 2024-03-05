<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<c:import url="layouts/header.jsp"></c:import>

<sql:setDataSource var="ds" dataSource="jdbc/testDB"></sql:setDataSource>
<sql:query dataSource="${ds }" var="users" sql="SELECT * FROM users"></sql:query>

<h1 class="text-center" style="color:green;">Hello admin page.</h1>

<div class="container mt-5">
	<table class="table">
		<thead>
			<tr>
				<th scope="col">No</th>
				<th scope="col">name</th>
				<th scope="col">email</th>
				<th scope="col">orders</th>
				<th scope="col">Actions</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="user" items="${users.rows }">
				<tr>

					<td>${user.id }</td>
					<td>${user.name }</td>
					<td>${user.email }</td>
					<td><a href="http://localhost:8080/E-Commerce/AdminUserController?action=orderdetail&user_id=${user.id }"
					 class="btn btn-info"> View Orders</a></td>
					<td><a href="" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i></a> <a
						href="" class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></a></td>
				</tr>
			</c:forEach>


		</tbody>
	</table>
</div>



<c:import url="layouts/jsgp.jsp"></c:import>
<c:import url="layouts/footer.jsp"></c:import>