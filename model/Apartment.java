package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Apartment implements Serializable,Comparable<Apartment>,Cloneable,MenuOperationInterface {

	protected static int id=1;
	int ApartmentSerial;
	protected String apartmentAddress;
	protected int area;
	protected int numberOfRooms;
	protected int ratingOfQualityApartment;
	protected ArrayList<Customer> customers = new ArrayList<Customer>();


	public Apartment(){
		this.ApartmentSerial = id++;
	}
	public Apartment(String address){
		//this();
		this.apartmentAddress = address;
	}
	
	public Apartment(Apartment other)
	{
		this();
		this.apartmentAddress = other.apartmentAddress;
	}

	public void changeContent(String address) {
		apartmentAddress = address;
	}

	public String getApartmentAddress() {
		return apartmentAddress;
	}

	public int getApartmentSerial() {
		return ApartmentSerial;
	}
	public int getApartmentArea() {
		return area;
	}
	public int getNumberOfRooms() {
		return numberOfRooms;
	}
	public int getRatingOfQualityApartment() {
		return ratingOfQualityApartment;
	}

	public boolean eaquals(Object obj) {
		if(obj instanceof Apartment) {
			Apartment other = (Apartment)obj;
			if (apartmentAddress== other.apartmentAddress)
				return true; 
		}
		return false;
	}
	public boolean add(Customer customer) {
		for(Customer c : customers) {
			if(c.getName().equals(customer.getName()) && c.getPhoneNumber().equals(customer.getPhoneNumber())) {
				return false;
			}
		}
		return customers.add(customer);
	}
	@Override
	public String toString() {
		return "Apartment: ApartmentSerial=" + ApartmentSerial + ", apartmentAddress=" + apartmentAddress + ", area="
				+ area + ", numberOfRooms=" + numberOfRooms + ", ratingOfQualityApartment=" + ratingOfQualityApartment;
	}
	
	
	public int partition(List<Customer> list,int left,int right) {
		Customer p = list.get(right);
		
		int i=left-1;

		for(int j =left; j< right;j++  ) {
		
			if(list.get(j).getName().compareTo(p.getName()) < 0) {
				i++;
				Collections.swap(list, i, j);
			}
		}
		Collections.swap(list, i+1, right);
		return i+1;
	}
	
	public void quickSort(List<Customer> list,int left, int right) {
		if(left <right) {
			int p = partition(list,left,right);
			quickSort(list,left,p-1);
			quickSort(list,p+1,right);
		}
	}
	public void sort(List<Customer> list) {
		quickSort(list,0,list.size()-1);
	}
	
	public List<Customer> getAllCustomers(boolean sorted) {
		ArrayList<Customer> tempCustomers = new ArrayList<Customer>();
		for (Customer customer : customers)
			tempCustomers.add(customer);
		if(sorted)
			sort(tempCustomers);
		return tempCustomers;
	}
	
	public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
	
}
