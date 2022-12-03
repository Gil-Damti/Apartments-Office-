package model;

import java.io.Serializable;

public class AirbnbApartment extends Renting implements Serializable {

	public AirbnbApartment(String address, double rentingPrice) {
		super(address, 1, rentingPrice);
	}
}