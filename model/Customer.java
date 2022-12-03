package model;

import java.io.Serializable;

public class Customer implements Serializable, Comparable<Customer> {
	private String phoneNumber;
	private String name;

	public Customer(String name, String phoneNumber) {
		this.name = name;
		this.phoneNumber = phoneNumber;
	}

	public Customer(Customer other) {
		this.name = other.name;
		this.phoneNumber = other.phoneNumber;
	}

	public Customer copy() {
		return new Customer(this);
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public boolean eaqualsType(Object other) {
		if (!(other instanceof Customer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(Customer o) {
		return name.compareTo(o.getName());
	}

}
