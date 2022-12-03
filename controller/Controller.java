package controller;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuItem;
import model.Apartment;
import model.Managment;
import view.View;

public class Controller {
	private Managment model;
	private View view;

	public Controller(Managment theModel, View theView) {
		this.model = theModel;
		this.view = theView;
		try {
			view.loadStoreView();
			view.showAlert("Welcome", "Hello");
			handleViewEvents();
		}catch(Exception e ) {
			view.showAlert("Error","Something went wrong.. " + e.getMessage());
		}

	}


	public interface onAddApartment {
		void addApartment(String type, String address,double price);
	}

	private void handleViewEvents() {
		EventHandler<ActionEvent> mouseClickedEvent = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				MenuItem clickedMenuItem = (MenuItem) event.getSource();
				view.clear();
				switch (clickedMenuItem.getText()) {
				case "Adding an apartment":
					view.showAddApartmentView(new onAddApartment() {
						@Override
						public void addApartment(String type, String address,double price) {
							switch(type) {
							case "Airbnb Apartment":
								model.addAirbnbApartment(address,price);
								break;
							case "Normal Apartment Rent":
								model.addNormalApartmentsForRent(address,price);
								break;
							case "Normal Apartment Sale":
								model.addNormalApartmentsForSale(address,price);
								break;
							}
							view.clear();
							view.showAllApartments(model);
						}
					});
					break;
				case "Adding a customer":
					view.showAddNewCustomerView(model);
					break;
				case "Presenting all the apartments, detailing the type of each apartment":
					view.showAllApartments(model);
					break;
				case "Showing all apartments only a certain type":
					view.showAllApartmentsViewByType(model);
					break;
				case "View the full price for the entire rental period for a particular apartment":
					view.showFullPriceRentalApartment(model);
					break;
				case "For all the apartments for rent what is the most expensive for a requested period":
					view.showMostExpensiveForRent(model);
					break;
				case "Introducing all customers to a particular apartment":			
					view.showAllCustomersForApartmentView(model,false);
					break;
				case "Presenting all the customers of a particular apartment in a sorted manner by name":
					view.showAllCustomersForApartmentView(model, true);
					break;
				case "Payment of commission for sale or rental":
					view.showAllApartmentsWithCommisionView(model);
					break;
				}
			}

		};
		this.view.addMouseClickedEventToMenu(mouseClickedEvent);
	}
}
