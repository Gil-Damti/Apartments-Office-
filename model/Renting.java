package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Renting extends Apartment implements Serializable {
	private int timeOfRent;
	private double rentingPrice;

	public Renting(String address, int timeOfRent, double rentingPrice) {
		this.apartmentAddress = address;
		this.timeOfRent = timeOfRent;
		this.rentingPrice = rentingPrice;
	}

	public Renting(String address) {
		super(address);
	}

	public double getRentingPrice() {
		return rentingPrice;
	}

	public int getTimeOfRent() {
		return timeOfRent;
	}

	@Override
	public String toString() {
		return "Rent " + super.toString();
	}

	public boolean equals(Object other) {
		if (!(other instanceof Renting))
			return false;
		return true;
	}

	@Override
	public int compareTo(Apartment o) {
		return 0;
	}

	@Override
	public double getCommission() {
		return 0;
	}

}
