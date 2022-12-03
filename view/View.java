package view;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.UnaryOperator;


import controller.Controller;
import controller.Controller.onAddApartment;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.AirbnbApartment;
import model.Apartment;
import model.Customer;
import model.Managment;
import model.NormalApartmentsForRent;

public class View {
	private Stage stage;
	private Menu mProfit;
	private Menu mApartment;
	private MenuItem addApartmentsFromUser;
	private MenuItem addCustomerToApartment;
	private MenuItem printAllApartments;
	private MenuItem printAllApartmentsOfCertainType;
	private MenuItem fullPriceRentalApartment;
	private MenuItem mostExpensiveForRentForPeriod;
	private MenuItem allCustomersForTheApartment;
	private MenuItem showAllCustomersOfApartment;
	private MenuItem showAllCommisionApartments;
	private MenuBar mb;
	private VBox vb;
	private ComboBox<String> cmboBarcodes;
	private Button btnOneProductStr;
	private Scene sc;
	private Label lblApartmentName;
	private Label lblbarCode;
	private Label lblCost;
	private Label lblsellPrice;
	private Label lblPhone;
	private TextField tfbarCode;
	private TextField tfProductName;
	private TextField tfCost;
	private TextField tfCostumerPrice;
	private CheckBox chbWantUpdate;
	private Button btnAddApartment;
	private Label lblCostumerName;
	private TextField tfcName;
	private TextField tfPhone;
	private Button btnOneProductDelete;
	private Button btnDeleteLast;
	private Button btnDeleteAll;
	private Button btnOneProductProfit;
	private Button btnSendUpdate;
	private TextArea tfUpdateMsg;
	public View(Stage stage) {
		this.stage = stage;
	}


	public void loadStoreView() {
		this.stage.setTitle("--- Real state office ----");
		this.mApartment = new Menu("Apartment options");

		this.addApartmentsFromUser = new MenuItem("Adding an apartment");
		this.addCustomerToApartment = new MenuItem("Adding a customer");
		this.printAllApartments = new MenuItem("Presenting all the apartments, detailing the type of each apartment");
		this.printAllApartmentsOfCertainType = new MenuItem("Showing all apartments only a certain type");
		this.fullPriceRentalApartment = new MenuItem("View the full price for the entire rental period for a particular apartment");
		this.mostExpensiveForRentForPeriod = new MenuItem("For all the apartments for rent what is the most expensive for a requested period");
		this.allCustomersForTheApartment = new MenuItem("Introducing all customers to a particular apartment");
		this.showAllCustomersOfApartment = new MenuItem("Presenting all the customers of a particular apartment in a sorted manner by name");
		this.showAllCommisionApartments = new MenuItem("Payment of commission for sale or rental");
		this.mApartment.getItems().addAll(new MenuItem[]{
				this.addApartmentsFromUser,
				this.addCustomerToApartment,
				this.printAllApartments,
				this.printAllApartmentsOfCertainType,
				this.fullPriceRentalApartment,
				this.mostExpensiveForRentForPeriod,
				this.allCustomersForTheApartment,
				this.showAllCustomersOfApartment,
				this.showAllCommisionApartments});       
		this.mb = new MenuBar();
		mb.getMenus().add(mApartment);

		this.vb = new VBox(this.mb);
		this.sc = new Scene(this.vb, 700.0, 260.0);
		this.stage.setScene(this.sc);
		this.stage.show();
	}

	public void addMouseClickedEventToMenu(EventHandler<ActionEvent> mouseClickedEvent) {
		this.addApartmentsFromUser.setOnAction(mouseClickedEvent);
		this.addCustomerToApartment.setOnAction(mouseClickedEvent);
		this.printAllApartments.setOnAction(mouseClickedEvent);
		this.printAllApartmentsOfCertainType.setOnAction(mouseClickedEvent);
		this.fullPriceRentalApartment.setOnAction(mouseClickedEvent);
		this.mostExpensiveForRentForPeriod.setOnAction(mouseClickedEvent);
		this.allCustomersForTheApartment.setOnAction(mouseClickedEvent);
		this.showAllCustomersOfApartment.setOnAction(mouseClickedEvent);
		this.showAllCommisionApartments.setOnAction(mouseClickedEvent);
	}

	


	private GridPane getNewScreen() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.BASELINE_CENTER);
		grid.setHgap(15.0);
		grid.setVgap(15.0);
		return grid;
	}
	
	public void clear() {
		this.vb.getChildren().clear();
		this.vb.getChildren().add(this.mb);
	}

	private <T> ListView<T> dataList(Managment model,List<T> list) {
		ListView<T> listView = new ListView();
		listView.getItems().addAll(list);
		listView.setMinWidth(600);
		listView.setMaxWidth(stage.getWidth() / 1.5);
		return listView;
	}

	private <T> GridPane dataGrid(ListView<T> apartmentsList,String title) {
		Label lbl = new Label(title);
		GridPane grid = getNewScreen();
		grid.add(lbl, 1,1);
		grid.add(apartmentsList,1, 2);
		grid.setMaxHeight(200);
		return grid;
	}

	public void showAllApartments(Managment model) {
		ListView<Apartment> apartments = dataList(model,model.getAllApartments());
		GridPane grid = dataGrid(apartments,"All Apartments");
		this.vb.getChildren().add(grid);
	}


	public void showAllApartmentsWithCommisionView(Managment model) {
		
		List<String> apartmentsWithCommisionStrings = new ArrayList();
		List<Apartment> apartmentsWithCommision = model.getAllCommisionApartments();
		
		for(Apartment a : apartmentsWithCommision) {
			apartmentsWithCommisionStrings.add("Address: " + a.getApartmentAddress() + ", Commision: " + a.getCommission());
		}
		ListView<String> apartments = dataList(model,apartmentsWithCommisionStrings);
		GridPane grid = dataGrid(apartments,"All Apartments with commision");
		this.vb.getChildren().add(grid);
	}
	
	public void showAllApartmentsViewByType(Managment model) {
		ListView<Apartment> apartments = dataList(model,model.getAllApartments());
		GridPane grid = dataGrid(apartments,"All Apartments by type");
		TilePane tilePane = new TilePane();
		ToggleGroup tg = new ToggleGroup();
		RadioButton button = new RadioButton("Airbnb Apartment");
		RadioButton button2 = new RadioButton("Normal Apartment Rent");
		RadioButton button3 = new RadioButton("Normal Apartment Sale");

		tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				Toggle t = arg0.getValue();
				RadioButton selectedButton = (RadioButton)t;

				switch(selectedButton.getText()) {
				case "Airbnb Apartment":
					apartments.getItems().setAll(model.getAllApartmentsOfCertainType("a"));
					break;
				case "Normal Apartment Rent":
					apartments.getItems().setAll(model.getAllApartmentsOfCertainType("n"));
					break;
				case "Normal Apartment Sale":
					apartments.getItems().setAll(model.getAllApartmentsOfCertainType("s"));
					break;
				}

			}
		});
		tilePane.getChildren().add(button);
		tilePane.getChildren().add(button2);
		tilePane.getChildren().add(button3);
		button.setToggleGroup(tg);
		button2.setToggleGroup(tg);
		button3.setToggleGroup(tg);
		grid.add(tilePane, 1,3);
		this.vb.getChildren().add(grid);
	}

	public void showAddApartmentView(onAddApartment handler) {
		GridPane grid = getNewScreen();
		TextField apartmentName = new TextField();
		TextField apartmentPrice = new TextField();
		apartmentName.setPromptText("Enter apartment address");
		apartmentPrice.setPromptText("Enter apartment price");
		numbersOnly(apartmentPrice);
		TilePane tilePane = new TilePane();
		ToggleGroup tg = new ToggleGroup();
		RadioButton button = new RadioButton("Airbnb Apartment");
		RadioButton button2 = new RadioButton("Normal Apartment Rent");
		RadioButton button3 = new RadioButton("Normal Apartment Sale");

		Button b = new Button("Add Apartment");

		b.setOnAction(new EventHandler<ActionEvent>() {
			/**
			 *
			 */
			@Override
			public void handle(ActionEvent arg0) {
				RadioButton selectedOption = (RadioButton) tg.getSelectedToggle();
				handler.addApartment(selectedOption.getText(),
						apartmentName.getText(),
						Double.parseDouble(apartmentPrice.getText()));
			}
		});
		button.setToggleGroup(tg);
		button2.setToggleGroup(tg);
		button3.setToggleGroup(tg);
		tilePane.getChildren().add(button);
		tilePane.getChildren().add(button2);
		tilePane.getChildren().add(button3);
		grid.add(apartmentName, 1, 3);
		grid.add(apartmentPrice, 1, 4);
		grid.add(tilePane, 1, 5);
		grid.add(b, 1,6);
		this.vb.getChildren().add(grid);
	}

	private void numbersOnly(TextField tf) {
		UnaryOperator<Change> filter = change -> {
			String text = change.getText();
			if(text.matches("[0-9]*")) return change;

			return null;
		};

		TextFormatter<String> textFormatter = new TextFormatter(filter);
		tf.setTextFormatter(textFormatter);
	}

	public void showMostExpensiveForRent(Managment model) {
		GridPane grid = getNewScreen();
		Label lblChoose = new Label("Check most expensive apartment by period, Enter period:");
		Label answerLabel = new Label("");
		Button b = new Button("Check");
		TextField tf = new TextField("Enter Period");
		numbersOnly(tf);

		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				Apartment a = model.mostExpensiveForRentForPeriod(Integer.parseInt(tf.getText()));
				if(a!=null) {
					answerLabel.setText(a.toString());
				}else {
					answerLabel.setText("Apartments not found");
				}
			}

		});
		grid.add(lblChoose, 1,1);
		grid.add(tf, 1,2);
		grid.add(b, 1,3);
		grid.add(answerLabel,1,4);
		this.vb.getChildren().add(grid);
	}

	public double showFullPriceRentalApartment(Managment model) {

		ListView<Apartment> listView = dataList(model,model.getAllApartments());
		GridPane grid = dataGrid(listView, "Full price rental for all Apartments");
		Label lblChoose = new Label("Check Full rental price by period (Airbnb), Enter");
		listView.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				Apartment apt = listView.getSelectionModel().getSelectedItem();

				if(apt instanceof AirbnbApartment) {
					lblChoose.setText("Check Full rental price by Day (Airbnb), Enter");
				}else if(apt instanceof NormalApartmentsForRent) {
					lblChoose.setText("Check Full rental price by Month (Normal), Enter");
				}
			}

		});
		Button b = new Button("Check");
		HBox box = new HBox();
		box.getChildren().add(b);
		grid.add(lblChoose,1,3);
		grid.add(box, 1, 4);
		grid.setMaxHeight(200);
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Apartment apt = listView.getSelectionModel().getSelectedItem();
				showRentalTimePopUp(apt);
			}
		});
		this.vb.getChildren().add(grid);
		return 0;
	}

	public void showAllCustomersForApartmentView(Managment model,boolean sorted) {
		ListView<Apartment> listView = dataList(model,model.getAllApartments());
		GridPane grid = dataGrid(listView, "Show all customer for apartment");
		Label lblChoose = new Label("Check Full rental price by period (Airbnb), Enter");
		Button b = new Button("Show All Customers for selected Apartment");
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Apartment apt = listView.getSelectionModel().getSelectedItem();
				if(apt!=null) {
					allCustomers(model,apt,sorted);
				}else {
					showAlert("Error","Please select an apartment");
				}
			}
		});
		grid.add(b, 1, 3);
		this.vb.getChildren().add(grid);
	}

	private void allCustomers(Managment model, Apartment apt,boolean sorted) {
		ListView<Customer> listView = dataList(model,apt.getAllCustomers(sorted));

		listView.setMaxHeight(200);
		GridPane grid = dataGrid(listView,"Showing all customers at apartment with id: " + apt.getApartmentSerial());

		listView.setMaxHeight(200);
		Scene s =  new Scene(grid, 760.0, 300.0);
		Stage allCustomersWindow = new Stage();
		allCustomersWindow.setTitle("All Customers for apt: " + apt.getApartmentSerial());
		allCustomersWindow.setScene(s);
		Button close = new Button("Close");

		close.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				allCustomersWindow.close();
			}

		});
		grid.add(close,1,3);
		allCustomersWindow.show();
	}



	public void showAlert(String title,String message) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setContentText(message);
		alert.setHeaderText(title);
		alert.show();
	}


	private TextInputDialog showInputAlert(String message) {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setContentText(message);
		dialog.show();
		return dialog;
	}

	public void showAddNewCustomerView(Managment model) {
		ListView<Apartment> listView = dataList(model,model.getAllApartments());
		GridPane grid = dataGrid(listView,"Add Customer to apartment");

		Button b = new Button("Add Customer To selected apartment");
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Apartment apt = listView.getSelectionModel().getSelectedItem();
				if(apt == null) {
					showAlert("Error","Please select an apartment");
					return;
				}
				showAddCustomerPopUp(apt);
			}
		});
		grid.add(b, 1, 3);
		this.vb.getChildren().add(grid);
	}



	private void showAddCustomerPopUp(Apartment apt) {
		GridPane dialogGrid = getNewScreen();
		dialogGrid.setMaxHeight(300);
		dialogGrid.setMaxWidth(300);
		Button b = new Button("Submit");
		TextField nameTextField = new TextField();
		TextField phoneTextField = new TextField();
		nameTextField.setPromptText("Enter Customer Name");
		phoneTextField.setPromptText("Enter Customer Phone");
		dialogGrid.add(nameTextField, 1, 1);
		dialogGrid.add(phoneTextField,1,2);
		dialogGrid.add(b,1,3);
		Scene s =  new Scene(dialogGrid, 260.0, 260.0);
		Stage addCustomerWindow = new Stage();
		addCustomerWindow.setTitle("Add Customer");
		addCustomerWindow.setScene(s);
		b.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				String name = nameTextField.getText();
				String phone = phoneTextField.getText();
				Customer c = new Customer(name,phone);
				boolean addedCustomer = apt.add(c);

				if(addedCustomer) 
					showAlert("Success","Successfully added new client: " + c.getName() + " to apartment with id " + apt.getApartmentSerial());
				else 
					showAlert("Error","This customer is already a resident of the selected apartment");
				addCustomerWindow.close();
			}

		});

		addCustomerWindow.show();
	}

	private void showRentalTimePopUp(Apartment apt) {

		Alert dialog = new Alert(Alert.AlertType.CONFIRMATION);
		dialog.getDialogPane().setMinHeight(300);
		if(apt instanceof AirbnbApartment) {
			AirbnbApartment a = (AirbnbApartment)apt;

			dialog.setContentText("The full price for the entire rental period for the apartment"+" "+apt.getApartmentAddress()+" :"+a.getRentingPrice()*a.getTimeOfRent());
			dialog.show();
		}else if(apt instanceof NormalApartmentsForRent) {
			NormalApartmentsForRent a = (NormalApartmentsForRent)apt;
			dialog.setContentText("The full price for the entire rental period for the apartment"+" "+apt.getApartmentAddress()+" :"+a.getRentingPrice()*a.getTimeOfRent());
			dialog.show();
		}
	}

}