<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="layouts/header.jsp" />
<%@ page import="coder.models.*,java.util.*" %>
<div class="container mt-5">
	<%
	 int total = 0;
	int i=0;
	List<Product> products=(List<Product>)request.getAttribute("products");
	for(Product p :products){
		total+=p.getPrice()*p.getCount();
	}
	%>
	<table class="table">
		<thead>
			<tr>
				<th scope="col">No</th>
				<th scope="col">name</th>
				<th scope="col">Image</th>
				<th scope="col">Price</th>
				<th scope="col">Count</th>
				<th scope="col">Total</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="product" items="${products}">
				<tr>
					<td><%=i++ %></td>
					<td>${product.username}</td>
					<td><img src="${product.image }"
						style="width: 100px; height: 100px;" class="oproductimg"></td>
					<td>${product.price }</td>
					<td>${product.count }</td>
					<td>${product.price * product.count }</td>
					
				</tr>

			</c:forEach>
			<tr>
				<td colspan="5" class="text-right">All Total</td>
				<td><%= total %></td>
			</tr>
		</tbody>
	</table>
</div>



<c:import url="layouts/jsgp.jsp" />

<script src="${pageContext.request.contextPath }/assets/js/cart3.js"></script>
<c:import url="layouts/footer.jsp" />