package org.seckill.entity;

public class Product {

	private int id;
	
	private String name;
	
	private double price;

	private int inventory;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", " + (getName() != null ? "Name=" + getName() + ", " : "") + "price=" + price
				+ ", inventory=" + inventory + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
