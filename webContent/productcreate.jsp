<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<c:import url="layouts/header.jsp" />

<sql:setDataSource var="ds" dataSource="jdbc/testDB"></sql:setDataSource>
<sql:query dataSource="${ds }" var="results"
	sql="SELECT * FROM categories"></sql:query>

<div class="container col-md-6">
	<!-- form start -->
	<form action="http://localhost:8080/E-Commerce/AdminProductController?action=newproduct" method="post" enctype="multipart/form-data">
		<div class="form-group">
			<label for="category">Choose Category</label> 
			<select class="form-control" id="category" name="category">
				<c:forEach var="cat" items="${results.rows }">
					<option value="${cat.id }">${cat.name }</option>
					</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="name">Name</label> <input type="text"
				class="form-control" id="name" name="name">
		</div>

		<div class="form-group">
			<label for="image">Image</label> <input type="file"
				class="form-control-file" id="image" name="image">
		</div>

		<div class="form-group">
			<label for="name">Price</label> <input type="number"
				class="form-control" id="price" name="price">
		</div>

		<div class="form-group">
			<label for="description">Description</label>
			<textarea class="form-control" id="description" name="description"rows="3"></textarea>
		</div>
		
		<input type="submit" class="btn btn-primary"  value="Upload"/> 
	</form>

	<!-- form end -->

</div>



<c:import url="layouts/jsgp.jsp"></c:import>
>
<c:import url="layouts/footer.jsp" />