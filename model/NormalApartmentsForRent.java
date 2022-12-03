package model;

import java.io.Serializable;
import java.util.ArrayList;

public class NormalApartmentsForRent extends Renting implements Serializable {

	private int timeMonthlyRental;

	public NormalApartmentsForRent(String address, double rentingPrice) {
		super(address, 30, rentingPrice);
	}

	public int getTimeMonthlyRental() {
		return timeMonthlyRental;
	}
	
	@Override
	public double getCommission() {
		return (double)4000.0;
	}

}            
    