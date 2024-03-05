
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:import url="layouts/header.jsp"></c:import>

<div class="container-fluid mt-6">
	<div class="row">
		<c:forEach var="product" items="${products }">
			<div class="col md-3">
				<div class="card">
					<div class="card-header justify-content-center">${product.name}</div>

					<div class="card-body">
						<div class="row justify-content-center">
							<img src="${product.image}" class="productimage" />
						</div>
					</div>

					<div class="container fluid">
						<div class="card-footer ">
							<span>Price =${product.price} </span>
							<button class=" ml-5 btn btn-primary"
								onclick="addItemToDB(${product.id})">
								<i class="fa fa-shopping-cart"></i>
							</button>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>


<c:import url="layouts/jsgp.jsp" />

<c:import url="layouts/footer.jsp"></c:import>