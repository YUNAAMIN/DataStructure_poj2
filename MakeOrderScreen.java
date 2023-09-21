package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

public class MakeOrderScreen extends BorderPane {/*
													 * this is the make order screen for orders screen that you read the
													 * whole file and take the process and put them in Queue and when
													 * its sold out put them on Stack and we can also put orders
													 */
	InfoCarList cars;
	QueueL queue;
	Stack sold;
	BrandList brandlist;
	private ComboBox<String> colorComboBox;
	private ComboBox<String> modelComboBox;
	private ComboBox<String> yearComboBox;
	private ComboBox<String> brandComboBox;
	// private CarAgency carAgency;
	private TextArea ordersTextArea;
	TextField brand;
	TextField model;
	TextField year;
	TextField color;
	TextField price;
	TextField name;
	TextField number;

	public MakeOrderScreen(BrandList list, QueueL queue, Stack sold) throws Exception {
		this.queue = queue;
		this.sold = sold;
		this.brandlist = list;

		Label lbAdded = new Label("enter number ");

		BorderPane pane = new BorderPane();
		GridPane grid = new GridPane();
		brand = new TextField();
		model = new TextField();
		year = new TextField();
		color = new TextField();
		price = new TextField();
		name = new TextField();
		number = new TextField();
		lbAdded = new Label("enter number ");
//Brand, Model, Year, 
		// Color, and color
		Label brandl = new Label("Brand:");
		Label modell = new Label("Model:");
		Label yearl = new Label("Year:");
		Label colorl = new Label("Color:");
		Label pricel = new Label("Price:");
		brandl.setTextFill(Color.WHITE);
		Label namel = new Label("Name:");
		Label numberl = new Label("Number:");
		modell.setTextFill(Color.WHITE);
		yearl.setTextFill(Color.WHITE);
		pricel.setTextFill(Color.WHITE);
		colorl.setTextFill(Color.WHITE);
		namel.setTextFill(Color.WHITE);
		numberl.setTextFill(Color.WHITE);

		brandl.setFont(new Font("Comic Sans MS", 15));
		modell.setFont(new Font("Comic Sans MS", 15));
		yearl.setFont(new Font("Comic Sans MS", 15));
		colorl.setFont(new Font("Comic Sans MS", 15));
		pricel.setFont(new Font("Comic Sans MS", 15));
		namel.setFont(new Font("Comic Sans MS", 15));
		numberl.setFont(new Font("Comic Sans MS", 15));
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
		grid.add(namel, 0, 6);
		grid.add(name, 1, 6);
		grid.add(numberl, 0, 7);
		grid.add(number, 1, 7);
		grid.setAlignment(Pos.CENTER);

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color:  #987974");
		final Image labImage = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\images.jpg");

		ImageView labImageView = new ImageView(labImage);
		labImageView.setFitWidth(300);
		labImageView.setPreserveRatio(true);
		Label welcomeLabel = new Label("Make own order");
		welcomeLabel.setFont(new Font("Impact", 50));
		welcomeLabel.setTextFill(Color.web("#370F08"));

		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		Button insert = new Button("press to save your order!!! ");
		insert.setFont(new Font("Comic Sans MS", 15));
		insert.setTextFill(Color.WHITE);
		insert.setStyle("-fx-background-color: #370F08");
		Button search = new Button("press to search for Order!!! ");
		search.setFont(new Font("Comic Sans MS", 15));
		search.setTextFill(Color.WHITE);
		search.setStyle("-fx-background-color: #370F08");
		buttonBox.getChildren().addAll(insert, search);
		Label lbMsg = new Label();
		vbox.getChildren().addAll(labImageView, welcomeLabel, grid, lbMsg, buttonBox);
		HBox h = new HBox(10);
		vbox.setPadding(new Insets(10));

		colorComboBox = new ComboBox<>();
		colorComboBox.setPromptText("Select Color");

		colorComboBox.setItems(FXCollections.observableArrayList("Red", "Blue", "Green", "White", "Black", "Grey",
				"Pink", "Yellow", "Purple", "Gold", "Baby Blue", "Orange"));
		colorComboBox.setStyle("-fx-background-color:#987974");
		modelComboBox = new ComboBox<>();
		modelComboBox.setPromptText("Select Model");
		modelComboBox.setItems(FXCollections.observableArrayList("C200", "C300", "X", "X3", "3", "X5", "X6", "MUSTANG",
				"RIO", "OPTIMA"));
		modelComboBox.setStyle("-fx-background-color:#987974");
		brandComboBox = new ComboBox<>();
		brandComboBox.setPromptText("Select Brand");
		brandComboBox.setItems(FXCollections.observableArrayList("MERCEDES", "TESLA", "BMW", "KIA", "FORD", "LIMOZINE",
				"SCODDA", "LAMBHORGHINI", "HUNDAI", "YUNA"));
		brandComboBox.setStyle("-fx-background-color:#987974");

		yearComboBox = new ComboBox<>();
		yearComboBox.setPromptText("Select Year");
		yearComboBox.setItems(FXCollections.observableArrayList("2014", "2015", "2016", "2017", "2018", "2019", "2020",
				"2021", "2021", " 2022", "2023"));
		yearComboBox.setStyle("-fx-background-color:#987974");

		Button orderButton = new Button("*ORDER*");
		orderButton.setFont(new Font("Comic Sans MS", 15));
		orderButton.setTextFill(Color.WHITE);
		orderButton.setStyle("-fx-background-color: #370F08");

		h.getChildren().addAll(brandComboBox, colorComboBox, modelComboBox, yearComboBox, orderButton);
		setAlignment(h, Pos.CENTER);
		VBox vb = new VBox();
		ordersTextArea = new TextArea();
		ordersTextArea.setPrefRowCount(10);
		setTop(h);
		setRight(vbox);
		setLeft(ordersTextArea);
		colorComboBox.setOnAction(e -> {
			String selectedElement = colorComboBox.getValue();

			if (selectedElement != null && !selectedElement.isEmpty()) {
				try {
					File file = new File(
							"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\orders.txt");
					Scanner scanner = new Scanner(file);
					boolean found = false;

					while (scanner.hasNextLine()) {
						String line = scanner.nextLine();
						ordersTextArea.getText();

						if (line.contains(selectedElement)) {
							found = true;
							System.out.println(
									"Matching line:\"MERCEDES, C300 , 2023, White, 250K \\n BMW, X3, 2019, White, 125K\\r \\n TESLA, X, 2019, White, 170K\\r\\n BMW, X3, 2019, White, 125K\\r\\nKIA, RIO, 2023, White, 150K\\r\\n\"\r\n"
											+ "											+ \"\" " + line);
							ordersTextArea.setText(
									"MERCEDES, C300 , 2023, White, 250K \n BMW, X3, 2019, White, 125K\r \n TESLA, X, 2019, White, 170K\r\n BMW, X3, 2019, White, 125K\r\nKIA, RIO, 2023, White, 150K\r\n"
											+ ""

							);
							break;
						}
					}

					scanner.close();

					if (!found) {
						System.out.println("No matching line found.");
					}
				} catch (FileNotFoundException ex) {
					System.out.println("File not found: " + ex.getMessage());
				}
			} else {
				System.out.println("Please select a valid element from the ComboBox.");
			}
		});

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
			String nameText = price.getText();
			String numberText = price.getText();

			if (brandText.isEmpty() == true || modelText.isEmpty() == true || yearText.isEmpty() == true
					|| colorText.isEmpty() == true || priceText.isEmpty() == true || numberText.isEmpty() == true
					|| nameText.isEmpty() == true) {
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
						Cars c = new Cars(brandText, modelText, yearText, colorText, priceText);
						Customer p = new Customer(nameText, numberText);
						obj.list_Car.insertSorted1(c);
						Order o = new Order(p, c, "/11/29/2023", "InProcessOrder");
						list.insertSorted1(obj);
						queue.enqueue(o);
						lbMsg.setVisible(true);
						lbMsg.setText("the order has been successfully added a.");

					} else {
						temp.data.list_Car
								.insertSorted1(new Cars(brandText, modelText, yearText, colorText, priceText));
						Cars c = new Cars(brandText, modelText, yearText, colorText, priceText);
						Customer p = new Customer(nameText, numberText);
						Order o = new Order(p, c, "/11/29/2023", "InProcessOrder");
						queue.enqueue(o);
						lbMsg.setVisible(true);
						lbMsg.setText("Brand already exists. Car has been added to the existing brand.");
						showAlert(AlertType.ERROR, "Invalid Input", "the brand not foud");

					}
				}
			}

		});
		search.setOnAction(e -> {
			String brandName;
			brandName = brand.getText().trim();
			Cars car;
			if (brandName.matches("[a-zA-Z]+") && !brandName.isEmpty()) {
				car = list.NameSearch(brandName);
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
					lbMsg.setText("Brand not founded.plzz You cant insert go update one!");
					Alert(AlertType.ERROR, "Invalid Input", "Invalid brand name",
							"Please enter an exist brand name!!.");

				}
			} else {
				model.setText("");
				year.setText("");
				color.setText("");
				price.setText("");
				lbMsg.setVisible(true);
				lbMsg.setText("Please enter the blank string!!!!");

				Alert(AlertType.ERROR, "Invalid Input", "Invalid brand name", "Please enter the blank string!!!.");
			}

		});
		brandComboBox.setOnAction(e -> {
			String b = brandComboBox.getValue();

			if (b != null && !b.isEmpty() && b.matches("[a-zA-Z]+")) {
				System.out.println(b);
				Node persons = list.search(b);

				if (persons != null) {
					ordersTextArea.setText(persons.getData().list_Car.getAllCar());

				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("No Car Selected");
					alert.setHeaderText(null);
					alert.setContentText("No matching brand found!");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Invalid Input");
				alert.setHeaderText(null);
				alert.setContentText("Please select a valid brand from the dropdown (letters only).");
				alert.showAndWait();
			}
		});
		colorComboBox.setOnAction(e -> {
			String selectedColor = colorComboBox.getValue();

			if (selectedColor != null && !selectedColor.isEmpty() && selectedColor.matches("[a-zA-Z]+")) {
				System.out.println(selectedColor);
				Node persons = list.getHead();

				if (persons != null) {
					ordersTextArea.setText(persons.getData().list_Car.getAllCar());
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("No Car Selected");
					alert.setHeaderText(null);
					alert.setContentText("No matching color found!");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Invalid Input");
				alert.setHeaderText(null);
				alert.setContentText("Please select a valid color from the dropdown (letters only).");
				alert.showAndWait();
			}
		});
	}

	private void Alert(AlertType AlertType, String t, String h, String c) {
		Alert a = new Alert(Alert.AlertType.WARNING);
		a.setTitle(t);
		a.setHeaderText(h);
		a.setContentText(c);
		a.showAndWait();
		System.out.println("the alert appear");
	}

	public void showAlert(AlertType type, String title, String content) {
		Alert alert = new Alert(type);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(content);
		alert.showAndWait();
	}

}