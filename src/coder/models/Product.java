package coder.models;

public class Product {
	
	private int id,cat_id,price,count;
	private String name,image,description,username;

	public Product() {
		
	}

	public Product(int id, int cat_id, int price, String name, String image, String description) {
		this.id = id;
		this.cat_id = cat_id;
		this.price = price;
		this.name = name;
		this.image = image;
		this.description = description;
	}
	
	

	public Product(int cat_id, int price, String name, String image, String description) {
		this.cat_id = cat_id;
		this.price = price;
		this.name = name;
		this.image = image;
		this.description = description;
	}

	public Product(int id, int cat_id, int price, int count, String name, String image, String description,
			String username) {
		this.id = id;
		this.cat_id = cat_id;
		this.price = price;
		this.count = count;
		this.name = name;
		this.image = image;
		this.description = description;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCat_id() {
		return cat_id;
	}

	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", cat_id=" + cat_id + ", price=" + price + ", name=" + name + ", image=" + image
				+ ", description=" + description + "]";
	}
	
	

}
