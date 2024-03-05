
var things = getAllFromDB();
var responseItems = "";

$.ajax({
	url: "http://localhost:8080/E-Commerce/CartController?action=billout",
	type: "post",
	data: {
		items: JSON.stringify(things)
	},
	success: function(success) {

		responseItems = JSON.parse(success);

		loadToShowProduct();
	},
	error: function(error) {
		console.log("error is " + error);
	}

});


function loadToShowProduct() {

	var tabledata = "";
	var total = 0;
	responseItems.forEach((product, i) => {
		var count = i + 1;

		tabledata += `<tr>
    	<td>${count}</td>
    	<td>${product.name}</td>
    	<td>${product.price}</td>
    	<td><img src="${product.image}"
    	 style="width:100px; height:100px"/></td>
    	<td>
    		<button class="btn btn-primary" onclick="addOneItem(${product.id})"><i class="fa fa-plus"></i></button>
    		<span>${getItemCount(product.id)}</span>
    		<button class="btn btn-danger" onclick="removeOneItem(${product.id})"><i class="fa fa-minus"></i></button>
    		<button class="btn btn-warning" onclick="deleteItem(${product.id})"><i class="fa fa-trash"></i></button>
    	</td>
    	<td>${getItemCount(product.id) * product.price}</td>
    </tr>`;
		total += getItemCount(product.id) * product.price;
	});


	tabledata += `<tr>
					<td colspan="5" class="text-right"> All Total</td>
					<td >${total}</td>
				</tr>`;


	document.getElementById("tablebd").innerHTML = tabledata;
}

function getItemCount(id) {
	var items = getAllFromDB();
	var indx = items.findIndex(x => x.id == id);

	return items[indx].count;
}

function addOneItem(id) {
	addItemToDB(id);
	loadToShowProduct();
}

function removeOneItem(id) {
	var items = getAllFromDB();
	var indx = items.findIndex(x => x.id == id);
	if (items[indx].count > 1) {
		items[indx].count = items[indx].count - 1;
		saveToDB(items);
		loadToShowProduct();
	}

}

function deleteItem(id) {
	var items = getAllFromDB();
	var indx = items.findIndex(x => x.id == id);
	if (indx != -1) {
		items.splice(indx, 1);
	}
	saveToDB(items);

	var ind = responseItems.findIndex(x => x.id == id)
	if (ind != -1) {
		responseItems.splice(ind, 1);
	}
	loadToShowProduct();
}
















