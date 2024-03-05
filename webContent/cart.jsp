<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="layouts/header.jsp" />

<div class="container">
	<table class="table">
		<thead class="thead-dark">
			<tr>
				<th scope="col">No</th>
				<th scope="col">Name</th>
				<th scope="col">Price</th>
				<th scope="col">Image</th>
				<th scope="col">Add/Remove</th>
				<th scope="col">Total</th>
			</tr>
		</thead>
		<tbody id="tablebd"></tbody>
		<tbody>
			<tr>
				<td colspan="6" class="text-right">
				<c:if test="${empty user }">
						<a href="http://localhost:8080/E-Commerce/UserController?action=login"
							class="btn btn-info">Login to Bill out</a>
				</c:if>
				<c:if test="${!empty user }">
					<a href="http://localhost:8080/E-Commerce/UserController?action=billout"
						class="btn btn-info">Bill out </a>
				</c:if>
				</td>
			</tr>
		</tbody>
	</table>
</div>


<c:import url="layouts/jsgp.jsp" />

<script src="${pageContext.request.contextPath}/assets/js/cart2.js"></script>
<c:import url="layouts/footer.jsp" />

