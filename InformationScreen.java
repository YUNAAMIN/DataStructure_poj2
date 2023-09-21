package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class InformationScreen
		extends BorderPane {/*
							 * this is the information gui graphical user enter face that shows the user
							 * information about the car and all of it using filters that the user can
							 * insert delete and update and search for the car with specific things like put
							 * the brand or model or the year to be clear when choosing what we need.
							 */
	BrandList list;
	public InfoCarList li;

	public InformationScreen(BrandList list) {
		this.list = list;
		BorderPane pane = new BorderPane();
		GridPane grid = new GridPane();
		TextField brand = new TextField();
		TextField model = new TextField();
		TextField year = new TextField();
		TextField color = new TextField();
		TextField price = new TextField();
		Label lbAdded = new Label("enter number ");
//Brand, Model, Year, 
		// Color, and color
		Label brandl = new Label("Brand:");
		Label modell = new Label("Model:");
		Label yearl = new Label("Year:");
		Label colorl = new Label("Color:");
		Label pricel = new Label("Price:");
		brandl.setTextFill(Color.WHITE);
		modell.setTextFill(Color.WHITE);
		yearl.setTextFill(Color.WHITE);
		pricel.setTextFill(Color.WHITE);
		colorl.setTextFill(Color.WHITE);

		brandl.setFont(new Font("Comic Sans MS", 15));
		modell.setFont(new Font("Comic Sans MS", 15));
		yearl.setFont(new Font("Comic Sans MS", 15));
		colorl.setFont(new Font("Comic Sans MS", 15));
		pricel.setFont(new Font("Comic Sans MS", 15));
		grid.setVgap(10);

		grid.add(brandl, 0, 0);
		grid.add(brand, 1, 0);
		grid.add(modell, 0, 2);
		grid.add(model, 1, 2);
		grid.add(yearl, 0, 3);
		grid.add(year, 1, 3);
		grid.add(colorl, 0, 4);
		grid.add(color, 1, 4);
		grid.add(pricel, 0, 5);
		grid.add(price, 1, 5);

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color:  #987974");
		final Image labImmodel = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\helping-customers.jpg");

		ImageView labImmodelView = new ImageView(labImmodel);
		labImmodelView.setFitWidth(300);
		labImmodelView.setPreserveRatio(true);
		Label welcomeLabel = new Label("Car Information");
		welcomeLabel.setFont(new Font("Impact", 50));
		welcomeLabel.setTextFill(Color.web("#370F08"));

		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		Button insert = new Button("*Insert* ");
		insert.setFont(new Font("Comic Sans MS", 15));
		insert.setTextFill(Color.WHITE);
		insert.setStyle("-fx-background-color: #370F08");
		Button search = new Button("*Search* ");
		search.setFont(new Font("Comic Sans MS", 15));
		search.setTextFill(Color.WHITE);
		search.setStyle("-fx-background-color: #370F08");
		Button update = new Button("*Update*");
		update.setStyle("-fx-background-color: #370F08");

		update.setFont(new Font("Comic Sans MS", 15));
		update.setTextFill(Color.WHITE);
		Button delete = new Button("*Delete*");
		delete.setStyle("-fx-background-color: #370F08");

		delete.setFont(new Font("Comic Sans MS", 15));
		delete.setTextFill(Color.WHITE);
		buttonBox.getChildren().addAll(insert, delete, update, search);
		grid.setAlignment(Pos.CENTER);
		Label lbMsg = new Label();
		vbox.getChildren().addAll(labImmodelView, welcomeLabel, grid, lbMsg, buttonBox);
		setCenter(vbox);

		insert.setOnAction(e -> {
			String yea;
			String pric;
			Node temp;
			CarBrand obj;
			String brandText = brand.getText();
			String modelText = model.getText();
			String yearText = year.getText();
			String colorText = color.getText();
			String priceText = price.getText();

			if (brandText.isEmpty() == true || modelText.isEmpty() == true || yearText.isEmpty() == true
					|| colorText.isEmpty() == true || priceText.isEmpty() == true) {
				lbMsg.setVisible(true);
				lbMsg.setText("plzzz put elements in the text fields!!!.");
			} else {
				if (!yearText.matches("\\d+") || !priceText.matches("\\d+")) {
					lbMsg.setVisible(true);
					lbMsg.setText("Please enter valid numeric values for year and price.succesfully inserted");
				} else {
					yea = yearText;
					pric = priceText;

					temp = list.search(brandText);
					if (temp == null) {
						obj = new CarBrand(brandText);
						obj.list_Car.insertSorted1(new Cars(brandText, modelText, yearText, colorText, priceText));
						list.insertSorted1(obj);
						lbMsg.setVisible(true);
						lbMsg.setText("Brand has been successfully added.");

					} else {
						temp.data.list_Car
								.insertSorted1(new Cars(brandText, modelText, yearText, colorText, priceText));
						lbMsg.setVisible(true);
						lbMsg.setText("Brand already exists. Car has been added to the existing brand.");
						showAlert(AlertType.ERROR, "Invalid Input", "the brand not foud",
								"Please its already an exist Brand!!!.");

					}
				}
			}

		});

		update.setOnAction(e -> {

		});

		search.setOnAction(e -> {
			String brandName = brand.getText();
			String yearNumber = year.getText();
			String modelName = model.getText();
			String colorName = color.getText();
			String priceName = price.getText();
			// by entering year and
			// brand.
			if (!yearNumber.isEmpty() && yearNumber.matches("\\d+") && !brandName.isEmpty()
					&& brandName.matches("[a-zA-Z]+")) {
				Cars car = list.NameSearch(brandName);
				Cars years = list.YearSearch(yearNumber);

				if (car != null) {
					model.setText(car.getModel());
					year.setText(car.getYear());
					color.setText(car.getColor());
					price.setText(car.getPrice());
					lbMsg.setVisible(false);
				} else {
					model.setText("");
					year.setText("");
					color.setText("");
					price.setText("");
					lbMsg.setVisible(true);
					lbMsg.setText("Brand not found. You can insert!");
				}
			} else if (!yearNumber.isEmpty() && yearNumber.matches("\\d+") && !brandName.isEmpty()// by entering year
																									// and brand and
																									// model.
					&& brandName.matches("[a-zA-Z]+") && !modelName.isEmpty() && modelName.matches("[a-zA-Z]+")) {
				Cars models = list.ModelSearch(modelName);
				Cars car = list.NameSearch(brandName);
				Cars years = list.YearSearch(yearNumber);
				if (car != null) {
					model.setText(car.getModel());
					year.setText(car.getYear());
					color.setText(car.getColor());
					price.setText(car.getPrice());
					lbMsg.setVisible(false);
				} else {
					model.setText("");
					year.setText("");
					color.setText("");
					price.setText("");
					lbMsg.setVisible(true);
					lbMsg.setText("Brand not found. You can insert!");
				}

			} else if (!colorName.isEmpty() && colorName.matches("\\d+") && !brandName.isEmpty()
					&& brandName.matches("[a-zA-Z]+")) {
				Cars car = list.NameSearch(brandName);
				Cars years = list.ColorSearch(colorName);

				if (car != null) {
					model.setText(car.getModel());
					year.setText(car.getYear());
					color.setText(car.getColor());
					price.setText(car.getPrice());
					lbMsg.setVisible(false);
				} else {
					model.setText("");
					year.setText("");
					color.setText("");
					price.setText("");
					lbMsg.setVisible(true);
					lbMsg.setText("Brand not found. You can insert!");
				}
			}

			else if (!yearNumber.isEmpty() && yearNumber.matches("\\d+") && !modelName.isEmpty()
					&& modelName.matches("[a-zA-Z]+")) {// && !brandName.isEmpty() && brandName.matches("[a-zA-Z]+")
//				Cars models = list.ModelSearch(modelName);
//				Cars car = list.NameSearch(brandName);
//				Cars years = list.YearSearch(yearNumber);
				Cars car = list.YearModelSearch(modelName, yearNumber);

				if (car != null) {
					model.setText(car.getModel());
					year.setText(car.getYear());
					color.setText(car.getColor());
					price.setText(car.getPrice());
					lbMsg.setVisible(false);
				} else {
					model.setText("");
					year.setText("");
					color.setText("");
					price.setText("");
					lbMsg.setVisible(true);
					lbMsg.setText("Brand not found. You can insert!");
				}

			} else if (!colorName.isEmpty() && colorName.matches("[a-zA-Z]+") && !priceName.isEmpty()
					&& priceName.matches("[a-zA-Z]+") && !brandName.isEmpty() && brandName.matches("[a-zA-Z]+")) {
				Cars prices = list.ModelSearch(priceName);
				Cars car = list.NameSearch(brandName);
				Cars colors = list.YearSearch(colorName);
				// Cars car = list.PriceColorSearch(colorName, priceName);
				if (car != null) {
					model.setText(car.getModel());
					year.setText(car.getYear());
					color.setText(car.getColor());
					price.setText(car.getPrice());
					lbMsg.setVisible(false);
				} else {
					model.setText("");
					year.setText("");
					color.setText("");
					price.setText("");
					lbMsg.setVisible(true);
					lbMsg.setText("Brand not found. You can insert!");
				}

			} else if (!modelName.isEmpty() && modelName.matches("[a-zA-Z]+") && !priceName.isEmpty()
					&& priceName.matches("[a-zA-Z]+")) {
				Cars car = list.ModelPriceSearch(modelName, priceName);
				if (car != null) {
					model.setText(car.getModel());
					year.setText(car.getYear());
					color.setText(car.getColor());
					price.setText(car.getPrice());
					lbMsg.setVisible(false);
				} else {
					model.setText("");
					year.setText("");
					color.setText("");
					price.setText("");
					lbMsg.setVisible(true);
					lbMsg.setText("Brand not found. You cant insert instead go and update.!");
				}

			}

			else {
				model.setText("");
				year.setText("");
				color.setText("");
				price.setText("");
				lbMsg.setVisible(false);
				showAlert(AlertType.ERROR, "Invalid Input", "Invalid brand name", "Please enter a non-empty string.");
			}

		});

		delete.setOnAction(e -> {
			String brandName;
			Node temp;
			ButtonType deleteButton, cancelButton;
			Optional<ButtonType> result;
			brandName = brand.getText().toLowerCase();

			if (brandName.isEmpty()) {
				lbMsg.setVisible(true);
				lbMsg.setText("Please enter a brand to delete.");
			} else {
				temp = list.search(brandName);
				// list.getData().list_Car.remove(temp);
				if (temp != null) {
					Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
					confirmAlert.setTitle("Confirmation");
					confirmAlert.setHeaderText(null);
					confirmAlert
							.setContentText("Are you sure that u really  want to delete " + brandName + "!!!!!!!!!?");
					deleteButton = new ButtonType("*Delete*");
					cancelButton = new ButtonType("*Cancel*");
					confirmAlert.getButtonTypes().setAll(deleteButton, cancelButton);
					result = confirmAlert.showAndWait();
					if (result.isPresent() && result.get() == deleteButton) {
						list.delete(temp.data);

						lbMsg.setVisible(true);
						lbMsg.setText("Brand has been successfully deleted.");
						showAlert(AlertType.CONFIRMATION, "yahooo", "the brand deleted",
								"u deleted the brand succesfully!!!.");

					}
				} else {
					lbMsg.setVisible(true);
					lbMsg.setText("Brand not found!!!!.");
					showAlert(AlertType.ERROR, "Invalid Input", "the brand not foud",
							"Please enter an exist Brand!!!.");

				}
			}

		});
	}

	private void showAlert(AlertType AlertType, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}
}
