let db="products";

function getAllFromDB(){
	var items=localStorage.getItem(db);
	if(items==null){
		return [];
	}
	else return JSON.parse(items);
}

function addItemToDB(id){
	var items=getAllFromDB();
	if(items.length<1){
		items.push({
			id : id,
			count : 1
		});
		saveToDB(items);
	}else{
		var indx=items.findIndex(x => x.id==id);
		if(indx != -1){
			items[indx].count=items[indx].count+1;	
		}else {
			items.push({
				id: id,
				count :1
			});
		}
			
		saveToDB(items);
	}	
}


function saveToDB(items){
	clearDB();
	localStorage.setItem(db,JSON.stringify(items));
	updateItemCount();
	
}

function updateItemCount(){
	var items=getAllFromDB();
	document.getElementById("cartcount").innerHTML = items.length;
}


function clearDB(){
	localStorage.clear();
	
}

function toggleMenuIcon(){
	$(".sidebar").toggleClass("hidesidebar");
}
