package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class Main extends Application {/*
										 * this is the main class and thats is at the first it uploaded the files for
										 * order and cars and save them in the stack and queue and in the two single
										 * linked list that one for the car information and the other is for the car
										 * brands and the stack for the car which the user had solded and the queue for
										 * the customes orders.
										 */
	public static BrandList list = new BrandList();
	private FileChooser fileChooser;
	public static QueueL queue = new QueueL();
	public static Stack sold = new Stack();
	public static Order order = new Order();

	public static final File file = new File(
			"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\cars.txt");
	public static final File filee = new File(
			"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\orders.txt");

	@Override
	public void start(Stage primaryStage) throws Exception {// the main scene

		BorderPane pane = new BorderPane();
		Button chooseFileBtn = new Button("WELCOME TO CAR AGENCY   SYSTEM!!!");
		chooseFileBtn.setFont(new Font("Comic Sans MS", 15));
		chooseFileBtn.setStyle("-fx-background-color: #CD853F");

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #987974");
		vbox.setSpacing(30);
		Image labImage = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\70591724_2567413473317892_5024421148528476160_n.jpg");
		ImageView ImageView = new ImageView(labImage);
		ImageView.setFitWidth(400);
		ImageView.setPreserveRatio(true);
		Label l = new Label();
		vbox.getChildren().addAll(ImageView, chooseFileBtn, l);
		MenuItem menuItem2 = new MenuItem("*Car Brand*");
		MenuItem menuItem3 = new MenuItem("*Car Information*");
		MenuItem menuItem4 = new MenuItem("*Make an Order*");
		MenuItem menuItem5 = new MenuItem("*ADMIN*");
		MenuItem menuItem6 = new MenuItem("*Generate Report*");
		Menu menu = new Menu("*CAR AGENCY OPTIONS*");
		menu.getItems().addAll(menuItem2, menuItem3, menuItem4, menuItem5, menuItem6);
		MenuBar menuBar = new MenuBar(menu);
		pane.setTop(menuBar);
		menuItem2.setOnAction(e -> {// location screen
			BrandScreen f;
			try {
				f = new BrandScreen(list);
				pane.setCenter(f);
				primaryStage.show();
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		});
		menuItem3.setOnAction(e -> {// martyr screen
			InformationScreen m = new InformationScreen(list);
			pane.setCenter(m);
			primaryStage.show();

		});
		menuItem4.setOnAction(e -> {// statistics screen
			MakeOrderScreen p = null;
			try {
				p = new MakeOrderScreen(list, queue, sold);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pane.setCenter(p);
			primaryStage.show();

		});
		menuItem6.setOnAction(e -> {// save screen
			GenerateReportScreen p = new GenerateReportScreen(list, queue, sold);
			pane.setCenter(p);
			primaryStage.show();

		});
		menuItem5.setOnAction(e -> {// save screen
			AdminScreen p = new AdminScreen(list, queue, sold);
			try {
				readOrdersFromFile(filee);
				// System.out.println(reportLastSoldCars());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			pane.setCenter(p);
			primaryStage.show();

		});
		pane.setCenter(vbox);
		chooseFileBtn.setOnAction(e -> { // here we can choose the file that we will work on it
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open file -->");
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				System.out.println(selectedFile.getAbsolutePath());
			}

			l.setText("The File Was Choosen:)");
			l.setFont(new Font("Comic Sans MS", 30));
			chooseFileBtn.setStyle("-fx-background-color: #CD853F");

		});

		Scene scene = new Scene(pane, 900, 700);
		primaryStage.setScene(scene);
		primaryStage.setTitle("CAR AGENCY SYSTEM :)");
		primaryStage.show();
	}

	public static void main(String args[]) throws Exception {

		txtFile(file);
		readOrdersFromFile(filee);
		// System.out.println(reportLastSoldCars());
		launch(args);
		// System.out.println("hey");
		System.out.println(list.getDataAsString());
//		return;
	}

	public static void txtFile(File file) throws Exception {

		// here the method that will read the file line by line and
		// connected
		// it
		// directly to the linked list
		CarBrand obj;
		Node temp;
		Scanner in = new Scanner(file);
		while (in.hasNext()) {
			String line = in.nextLine();
			String[] matryr = line.trim().split(",");
			if (matryr.length == 5) {
				// Brand, Model, Year, Color, Price
				String brand = matryr[0].trim();
				String model = matryr[1].trim();
				String year = matryr[2].trim();
				String color = matryr[3].trim();
				String price = matryr[4].trim();
				temp = list.search(brand);
				if (temp == null) {
					obj = new CarBrand(brand);
					obj.list_Car.insertSorted1(new Cars(brand, model, year, color, price));
					list.insertSorted1(obj);
					list.writeToFile(list.getDataAsString());

				} else {
					temp.data.list_Car.insertSorted1(new Cars(brand, model, year, color, price));
				}

			} else
				line = in.nextLine();

		}
	}

	int getLineNumbers(String FileName) {
		int count = -1;
		File f = new File(FileName);
		if (f.exists()) {
			Scanner scanner = null;
			try {
				scanner = new Scanner(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			while (scanner.hasNext()) {
				count++;

			}

		}
		return count;
	}

	public static void readOrdersFromFile(File filename) throws FileNotFoundException {
		Cars car;
		Order order;
		Customer customer;
		if (filee.exists()) {
			Scanner scanner = new Scanner(filee);
			// int count = getLineNumbers(filename);
//			ordersQueue.clearQueue();
//			soldCarsStack.clearStack();
			while (scanner.hasNext()) {
// CustomerName, CustomerMobile, Brand, Model, Year, Color, Price, OrderDate,
// OrderStatus
				String line = scanner.nextLine();
				System.out.println(line);
				String[] orderData = line.split(",");
				String name = orderData[0];
				if (name.equals("CustomerName"))
					continue;
				String mobile = orderData[1].trim();
				String brand = orderData[2].trim();
				String model = orderData[3].trim();
				String year = orderData[4].trim();
				String color = orderData[5].trim();
				String price = orderData[6].trim();
				String date = orderData[7].trim();
				String status = orderData[8].trim();

				// CarBrand carBrand = searchCarBrand(brand);
				if (brand != null) {
					car = new Cars(brand, model, year, color, price);
					customer = new Customer(name, mobile);
					if (car != null) {
						order = new Order(customer, car, date, status);
						if (status.equals("Finished")) {
							sold.push(order);
						} else {

							queue.enqueue(order);

						}
						System.out.println(queue.toString() + "--->>" + sold);

					}
				}
			}
		} else
			System.out.println("File not found!!!!!!!");
	}

	public static String reportLastSoldCars() throws Exception {
		int counter = 0;
		Order order;
		Cars car;
		StringBuilder report;
		String argu;
		report = new StringBuilder("the Last ten cars solded are:\n");
		Stack tmp = new Stack();

		while (counter < 10 && sold.isEmpty() == false) {
			order = sold.peek();
			car = order.getCar();
			tmp.push(order);
			argu = "-->>" + order.getCustomer().getName() + order.getCustomer().getContactInfo() + car.getBrand() + " "
					+ car.getModel() + " " + car.getYear() + car.getPrice() + " $" + order.getDate() + " "
					+ order.getStatus();
			report.append(argu).append(" \n ");
			sold.pop();
			counter++;
		}

		while (!tmp.isEmpty()) {

			sold.push(tmp.pop());
		}

		return report.toString();
	}
}
