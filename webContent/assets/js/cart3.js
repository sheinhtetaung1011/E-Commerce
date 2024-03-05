function billOut() {
	var things = getAllFromDB();


	$.ajax({
		url: "http://localhost:8080/E-Commerce/CartController?action=orderout",
		type: "post",
		data: {
			items: JSON.stringify(things)
		},
		success: function(success) {
			if(success=="success"){
				clearDB();
				updateItemCount();
			}
			
		},
		error: function(error) {
			console.log("error is " + error);
		}

	});
}

billOut();