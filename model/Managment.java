package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Managment implements Serializable {

	private ArrayList<Apartment> allApartments = new ArrayList<Apartment>();
	private ArrayList<Customer> customers = new ArrayList<Customer>();

	public Managment() {
		getCustomer("Rami Bronstein","0529226112");
		getCustomer("Rami Bronstein","0529226112");
		getCustomer("Rami Bronstein","0529226112");
		getCustomer("Rami Bronstein","0529226112");

		addAirbnbApartment("19 Bogarshov, Tel Aviv", 2300);
		addAirbnbApartment("King George 11, Tel Aviv", 2300);
		addAirbnbApartment("Abba Hillel Silver 9, Tel Aviv", 2300);
		addAirbnbApartment("35 Herzl, Tel Aviv", 2300);

		addNormalApartmentsForRent("Yura 12, Petah Tikva", 2300);
		addNormalApartmentsForRent("10 Yavneali, Rosh Ha'Ain", 2300);
		addNormalApartmentsForRent("21 Tabor, Shoham", 2300);
		addNormalApartmentsForRent("Sderot Emek Ayalon 3, Shoham", 2300);

		addNormalApartmentsForSale("Handev 22, Herzliya",4100000);
		addNormalApartmentsForSale("Stampfer 15 , Petah Tikva",2750000);
		addNormalApartmentsForSale("21 Tabor, Shoham",2100000);
		addNormalApartmentsForSale("Ramat Yam 48, Herzliya",3580000);
	}

	public Managment(ArrayList<Apartment> Apartments) {
		this.allApartments = Apartments;
	}

	public boolean isEmpty() {
		return allApartments.isEmpty() && customers.isEmpty();
	}
	public int getApartmentCount() {
		return allApartments.size();
	}

	public void eraseApartmentById(int id) {
		if (!isSerialExists(id))
			System.out.println("You cannot delete a none existing id.");
		else {
			allApartments.removeIf((Apartment q) -> q.ApartmentSerial == id);
		}
	}
	
	public List<Apartment> getAllApartments() {
		return new ArrayList<Apartment>(allApartments); 
	}

	public void addAirbnbApartment(String address, double rentingPrice) {
		allApartments.add(new AirbnbApartment(address, rentingPrice));
	}

	public void addNormalApartmentsForRent(String address, double rentingPrice) {
		allApartments.add(new NormalApartmentsForRent(address, rentingPrice));
	}
	public void addNormalApartmentsForSale(String address,double price) {
		allApartments.add(new SaleApartment(address,price));
	}

	
	public List<Apartment> getAllCommisionApartments() {
		List<Apartment> list = new ArrayList();
		for (Apartment q : allApartments)
			if(q.getCommission() > 0)
				list.add(q);
		return list;
	}

	public Apartment getApartmentByIndex(int index) {
		return (Apartment) allApartments.get(index);
	}

	public boolean isSerialExists(int id) {
		for (Apartment q : this.allApartments)
			if (q.getApartmentSerial() == id)
				return true;
		return false;
	}

	public Apartment getApartmentBySerial(int id) {
		for (Apartment q : this.allApartments)
			if (q.ApartmentSerial == id)
				return q;
		System.out.println("Warning: You are trying to get an invalid Apartment.");
		return null;
	}
	
	

	public boolean isAlreadyExists(String address) {
		for (int i = 0; i < allApartments.size(); i++)
			if (allApartments.get(i) != null && address.equals(allApartments.get(i).getApartmentAddress()))
				return true;
		return false;
	}

	/*
	 * Method that presents a selection of a choice in the set of questions in
	 * creating a test
	 */

	public static void print(Apartment[] a) {
		for (int i = 0; i < a.length; i++)
			System.out.println(a[i]);
	}

	public Customer getCustomer(String phoneNumber, String name) {
		for (Customer customer : customers) {
			if (customer.getName().equals(name) && customer.getPhoneNumber().equals(phoneNumber))
				return customer;
		}
		return null;
	}

	public void addCustomerToApartment() {
		Scanner s = new Scanner(System.in);
		System.out.println("Select an apartment:");


		int index = Utilities.readInt() - 1;
		if ((index < 0) || (index >= allApartments.size())) {
			System.out.println("Bad apartment");
			return;
		}

		Apartment apartment = allApartments.get(index);
		System.out.print("Enter customer phone number: ");
		String phoneNumber = s.next();
		System.out.print("Enter customer name: ");
		String name = s.next();
		Customer customer = getCustomer(phoneNumber, name);

		if (customer == null) {
			customer = new Customer(name, phoneNumber);
			customers.add(customer);
		}
		apartment.add(customer);
	}

	

	
	public void allCustomersForTheApartment(){

		Scanner s = new Scanner(System.in);
		String address;
		System.out.println("Enter a daily price for the apartment");
		address=s.next();

		for (int i = 0; i < allApartments.size(); i++) {
			Apartment apartment = allApartments.get(i);
			if (apartment.getApartmentAddress().equals(address))
				System.out.println(apartment.customers.get(i).getName());
			System.out.println(apartment.customers.get(i).getPhoneNumber());
		}
	}

	public List<Apartment> getAllApartmentsOfCertainType(String kindOfApartment) {
		List<Apartment> aptList = new ArrayList();
		switch(kindOfApartment) {
		case "a":
			for (int i = 0; i < allApartments.size(); i++) {
				Apartment apartment = allApartments.get(i);
				if (apartment instanceof AirbnbApartment)
					aptList.add(apartment);
				
			}
			break;
		case "n":
			for (int i = 0; i < allApartments.size(); i++) {
				Apartment apartment = allApartments.get(i);
				if (apartment instanceof NormalApartmentsForRent)
					aptList.add(apartment);
			}
			break;
		case "s":
			for (int i = 0; i < allApartments.size(); i++) {
				Apartment apartment = allApartments.get(i);
				if (apartment instanceof SaleApartment)
					aptList.add(apartment);
			}
			break;
		}
		return aptList;
	}

	public Apartment mostExpensiveForRentForPeriod(int period) {
		double expensivePrice = 0;
		Apartment expensiveApartment = null;
		for (int i = 0; i < allApartments.size(); i++) {
			Apartment apartment = allApartments.get(i);
			if (apartment instanceof NormalApartmentsForRent)
			{
				NormalApartmentsForRent normalApartmentsForRent = (NormalApartmentsForRent) apartment;
				double price = normalApartmentsForRent.getRentingPrice() * period;
				if (price > expensivePrice)
				{
					expensivePrice = price;
					expensiveApartment = apartment;
				}
			}
		}
		if (expensiveApartment != null)
		{
			
			System.out.println("The most expensive apartment is: " + expensiveApartment.toString() + " cost " + expensivePrice + " NIS");
		}
		else
		{
			System.out.println("Apartment not found");
		}
		return expensiveApartment;
	}


	public void saveToBinaryFile() {
		try {
			// Write
			FileOutputStream fileOutputStream = new FileOutputStream("FullExamBinaryDump.bin");
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this);
			objectOutputStream.flush();
			objectOutputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Managment readFromBinaryFileOrCreateNew() {
		try {
			// Write
			FileInputStream fileInputStream = new FileInputStream("FullExamBinaryDump.bin");
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
			Object obj = objectInputStream.readObject();
			objectInputStream.close();
			return (Managment) obj;
		} catch (Exception e) {
		}
		return new Managment();
	}

	public void cloneApartment() {
		System.out.println("Select an apartment:");
		
		int index = Utilities.readInt() - 1;
		if ((index < 0) || (index >= allApartments.size())) {
			System.out.println("Bad apartment");
			return;
		}
		Apartment apartment = allApartments.get(index);
		try {
			Apartment newApartment = (Apartment) apartment.clone();
			allApartments.add(newApartment);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
