package model;

import java.io.Serializable;

public class SaleApartment extends Apartment implements Serializable {

	private double price;

	public SaleApartment(String address, double price) {
		super(address);
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	@Override
	public int compareTo(Apartment o) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double getCommission() {
		return price*0.05 ;
	}
}            
