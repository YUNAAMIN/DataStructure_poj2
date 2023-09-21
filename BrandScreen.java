package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/*the brand class that this screen we can by it do 
 * some operations on the brand like insert
 * delete,search,and update the brands that when i
 *  do any things  from this operations it well show 
 *  on the text area  and its especially from the Doubly
 *  linked list that it contains it for easy and
 *  comfortable usage!!!!! */

public class BrandScreen extends BorderPane {
	BrandList list;

	public BrandScreen(BrandList list) throws FileNotFoundException {
		this.list = list;
		BorderPane pane = new BorderPane();
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #987974");
		Label welcomeLabel = new Label("CAR BRANDS");
		welcomeLabel.setFont(new Font("Impact", 50));
		welcomeLabel.setTextFill(Color.web("#370F08"));
		Label l = new Label("BRAND:");
		l.setTextFill(Color.WHITE);

		l.setFont(new Font("Comic Sans MS", 15));
		TextField t = new TextField();
		HBox b = new HBox(10);
		b.getChildren().addAll(l, t);
		b.setAlignment(Pos.CENTER);
		Image image = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\top-car-brands-in-india.png");

		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(300);
		imageView.setFitHeight(200);
		TextArea a = new TextArea();
		File file = new File("C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\cars.txt");
		Scanner scanner = new Scanner(file);
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		Button insert = new Button("Insert ");
		insert.setFont(new Font("Comic Sans MS", 15));
		insert.setTextFill(Color.WHITE);
		insert.setStyle("-fx-background-color: #370F08");
		Button search = new Button("Search ");
		search.setFont(new Font("Comic Sans MS", 15));
		search.setTextFill(Color.WHITE);
		search.setStyle("-fx-background-color: #370F08");
		Button update = new Button("Update");
		update.setStyle("-fx-background-color: #370F08");

		update.setFont(new Font("Comic Sans MS", 15));
		update.setTextFill(Color.WHITE);
		Button delete = new Button("Delete");
		delete.setStyle("-fx-background-color: #370F08");

		delete.setFont(new Font("Comic Sans MS", 15));
		delete.setTextFill(Color.WHITE);
		Label lbMsg = new Label("");
		lbMsg.setTextFill(Color.RED);
		lbMsg.setFont(new Font(20));

		buttonBox.getChildren().addAll(insert, delete, update, search);
		buttonBox.setSpacing(10);
		vbox.getChildren().addAll(imageView, welcomeLabel, b, lbMsg, a, buttonBox);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(10));
		setCenter(vbox);
		Button be = new Button("press if you sure that you want to delete this brand!!!");
		StackPane pan = new StackPane();
		pan.getChildren().add(be);

		search.setOnAction(e -> {

			String brand = t.getText();
			if (!brand.isEmpty() && brand.matches("[a-zA-Z]+")) {
				System.out.println(brand);
				Node persons = list.search(brand);
				if (persons != null) {
					a.setText(persons.getData().list_Car.getAllCar());
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
				alert.setContentText("Please enter a valid brand name (letters only).");
				alert.showAndWait();
			}
		});

		update.setOnAction(e -> {

			String brand = t.getText();
			if (!brand.isEmpty() && brand.matches("[a-zA-Z]+")) {
				System.out.println(brand);
				Node persons = list.search(brand);
				if (persons != null) {
					persons.getData().list_Car.removeAll();
					a.setText("yahooooo The " + brand + " brand has been updated successfully!!!!!1");
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("No Car Selected");
					alert.setHeaderText(null);
					alert.setContentText("oh noooo,No matching brand found!!!");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Invalid Input!!!!!!!!1");
				alert.setHeaderText(null);
				alert.setContentText("Please enter a valid brand name (letters alphabitical only).");
				alert.showAndWait();
			}

		});

		insert.setOnAction(e -> {

			String brand = t.getText();
			if (!brand.isEmpty() && brand.matches("[a-zA-Z]+")) {
				System.out.println(brand);
				Node persons = list.search(brand);
				if (persons == null) {
					CarBrand carBrand = new CarBrand(brand);
					list.insertNode(carBrand);
					a.setText(brand + " has been added to the brand list successfully!");
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Brand Already Exists");
					alert.setHeaderText(null);
					alert.setContentText("The brand already exists in the list!");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Invalid Input");
				alert.setHeaderText(null);
				alert.setContentText("Please enter a valid brand name (letters only).");
				alert.showAndWait();
			}
		});
		delete.setOnAction(e -> {
			String brand = t.getText();
			if (!brand.isEmpty() && brand.matches("[a-zA-Z]+")) {
				System.out.println(brand);
				Node persons = list.search(brand);
				persons.data.list_Car.removeAll();// it delete the content of the brand that i will delete not the node
													// of the brand doubly linked list
				list.remove(persons);

				a.setText("the brand has been deleted succssesfully!!!!!!!!!!");

				if (persons != null) {
					Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
					confirmAlert.setTitle("Confirmation");
					confirmAlert.setHeaderText(null);
					confirmAlert.setContentText("Are you sure you want to delete the brand '" + brand + "'?");

					ButtonType deleteButton = new ButtonType("Delete");
					ButtonType cancelButton = new ButtonType("Cancel");

					confirmAlert.getButtonTypes().setAll(deleteButton, cancelButton);

					Optional<ButtonType> result = confirmAlert.showAndWait();
					if (result.isPresent() && result.get() == deleteButton) {
						list.delete(persons.getData());
						a.setText(brand + " has been deleted from the brand list successfully!");
					}
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
				alert.setContentText("Please enter a valid brand name (letters only)!!!!.");
				alert.showAndWait();
			}
		});
	}

}
