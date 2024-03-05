<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:import url="layouts/header.jsp" />
<c:import url="layouts/slideshow.jsp" />

<sql:setDataSource var="ds" dataSource="jdbc/testDB"></sql:setDataSource>
<sql:query dataSource="${ds }" var="results"
	sql="SELECT * FROM categories"></sql:query>

<div class="container mt-5">
	<div class="row">
		<c:forEach var="cat" items="${results.rows }">
			<div class="col-md-4">
				<div class="card">
					<div class="card-header">${cat.name}</div>
					<div class="card-body">
						<div class="row justify-content-center">
							<a
								href="http://localhost:8080/E-Commerce/ProductServlet?cat_id=${cat.id}">
								<img
								src="${pageContext.request.contextPath}/assets/imgs/${cat.image}"
								class="img-fluid myiconimage">
							</a>
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
<c:import url="layouts/jsgp.jsp" />
<c:import url="layouts/footer.jsp" />