package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GenerateReportScreen extends BorderPane {// this is the class that we generate and save the file and save
														// thae changes at the same file that at first that i loaded at
														// the main
	BrandList list;
	InfoCarList li;
	QueueL queue;
	Stack DoneOrder;
	Order order;
	TextArea ta;

	public GenerateReportScreen(BrandList list, QueueL queue, Stack DoneOrder) {
		this.list = list;
		this.queue = queue;
		this.DoneOrder = DoneOrder;
		BorderPane pane = new BorderPane();
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #987974");
		Label welcomeLabel = new Label("Sold Car Report :");
		welcomeLabel.setFont(new Font("Impact", 50));
		welcomeLabel.setTextFill(Color.web("#370F08"));
		Label l = new Label("Export file to save :");
		l.setFont(new Font("Comic Sans MS", 15));
		TextField t = new TextField();
		HBox b = new HBox(10);
		b.setAlignment(Pos.CENTER);
		ta = new TextArea();
		final Image labImage = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\istockphoto-1219808488-612x612.jpg");

		ImageView labImageView = new ImageView(labImage);
		labImageView.setFitWidth(300);
		labImageView.setPreserveRatio(true);
		Button export = new Button("Export to car file!!! ");
		export.setFont(new Font("Comic Sans MS", 15));
		export.setStyle("-fx-background-color: #370F08");
		export.setTextFill(Color.WHITE);
		Button export2 = new Button("Export to order file!!! ");
		export2.setFont(new Font("Comic Sans MS", 15));
		export2.setStyle("-fx-background-color: #370F08");
		export2.setTextFill(Color.WHITE);

		b.getChildren().addAll(export2, export);
		vbox.getChildren().addAll(labImageView, welcomeLabel, ta, b);

		setCenter(vbox);
		export.setOnAction(e -> {
			appendCar();
		});
		export2.setOnAction(e -> {
			appendOrder();
		});

	}

	public void appendCar() {
		NodeCar currentNode;
		try {
			FileWriter fileWriter = new FileWriter("C:\\Users\\Yuna\\Desktop\\cars.txt", true);

			currentNode = list.getData().list_Car.getHead();
			while (currentNode != null) {
				Cars car = currentNode.getData();
				fileWriter.write("\n" + "car-->> " + car.toString() + "\n");
				ta.setText(car.toString() + "\n");

				currentNode = currentNode.getNext();
			}

			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void appendOrder() {
		try {
			FileWriter fileWriter = new FileWriter("C:\\Users\\Yuna\\Desktop\\orders.txt", true);

			while (!queue.isEmpty()) {
				order = queue.denqueue();
				fileWriter.write("\n" + "order-->> " + order.toString() + "\n");
				ta.setText(order.toString() + "\n");
			}

			while (!DoneOrder.isEmpty()) {
				order = DoneOrder.pop();
				fileWriter.write("\n" + "order-->> " + order.toString() + "\n");

				ta.setText(order.toString() + "\n");

			}

			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
