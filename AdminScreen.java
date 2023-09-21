package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/* this screen is for the admin that the admin 
 * can do what ever he wants with the orders that
 *  he can reacted or accepted  and the admin can process customers’ 
 *  orders queue and add finished one to a finish ordered stack.
    Make sure to remove the sold cars from the cars data structure. 
    In case a car is not anymore
     available, the admin has an option to remove it from front of 
     queue and add it back to the end
      of queue or to cancel the order completely and here i implement
       the the user interface for the admin screen  and also added an 
       methods that help him*/
public class AdminScreen extends BorderPane {
	private BrandList list;
	private static QueueL queue;
	private static Order order;
	private ComboBox<String> stat;
	private TextField ntxt;
	private CarAgency cara;
	private static Stack DoneOrder;
	private TextArea ta;
	private CarBrand carBrands;
	private int index;

	public BrandList getList() {
		return list;
	}

	public void setList(BrandList list) {
		this.list = list;
	}

	public QueueL getQueue() {
		return queue;
	}

	public void setQueue(QueueL queue) {
		this.queue = queue;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ComboBox<String> getStat() {
		return stat;
	}

	public void setStat(ComboBox<String> stat) {
		this.stat = stat;
	}

	public TextField getntxt() {
		return ntxt;
	}

	public void setntxt(TextField ntxt) {
		this.ntxt = ntxt;
	}

	public CarAgency getCara() {
		return cara;
	}

	public void setCara(CarAgency cara) {
		this.cara = cara;
	}

	public Stack getDoneOrder() {
		return DoneOrder;
	}

	public void setDoneOrder(Stack doneOrder) {
		DoneOrder = doneOrder;
	}

	public TextArea getTa() {
		return ta;
	}

	public void setTa(TextArea ta) {
		this.ta = ta;
	}

	public CarBrand getCarBrands() {
		return carBrands;
	}

	public void setCarBrands(CarBrand carBrands) {
		this.carBrands = carBrands;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	AdminScreen(BrandList list, QueueL queue, Stack sold) {
		this.queue = queue;
		this.DoneOrder = sold;
		this.list = list;

		cara = new CarAgency();
		try {
			cara.readOrders("C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\cars.txt");
			cara.readOrders("C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj2\\src\\application\\orders.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Create GUI components
		ta = new TextArea();
		Button prevBtn = new Button("*Previous*");
		prevBtn.setFont(new Font("Comic Sans MS", 15));
		prevBtn.setTextFill(Color.WHITE);
		prevBtn.setStyle("-fx-background-color: #370F08");

		Button nxtBtn = new Button("*Next*");
		nxtBtn.setFont(new Font("Comic Sans MS", 15));
		nxtBtn.setTextFill(Color.WHITE);
		nxtBtn.setStyle("-fx-background-color: #370F08");

		ntxt = new TextField();
		stat = new ComboBox<>(FXCollections.observableArrayList("Finished", "In Process"));
		stat.setStyle("-fx-background-color: #FFFFFF");

		Button insrtBtn = new Button("*Insert Name*");
		insrtBtn.setFont(new Font("Comic Sans MS", 15));
		insrtBtn.setTextFill(Color.WHITE);
		insrtBtn.setStyle("-fx-background-color: #370F08");
		Button prsBtn = new Button("Process Order");
		prsBtn.setFont(new Font("Comic Sans MS", 15));
		prsBtn.setTextFill(Color.WHITE);
		prsBtn.setStyle("-fx-background-color: #370F08");
		Button rptBtn = new Button("Report Last 10 Sold Cars");
		rptBtn.setFont(new Font("Comic Sans MS", 15));
		rptBtn.setTextFill(Color.WHITE);
		rptBtn.setStyle("-fx-background-color: #370F08");

		prevBtn.setOnAction(e -> prevb());
		nxtBtn.setOnAction(e -> nextb());

		VBox rt = new VBox(10);
		rt.setPadding(new Insets(10));
		rt.setAlignment(Pos.CENTER);
		rt.setStyle("-fx-background-color: #987974");

		HBox prenxt = new HBox(10, nxtBtn);
		prenxt.setAlignment(Pos.CENTER);
		HBox box = new HBox(10, ntxt, stat, insrtBtn);
		box.setAlignment(Pos.CENTER);
		HBox bac = new HBox(10, prsBtn, rptBtn);
		bac.setAlignment(Pos.CENTER);
		rt.getChildren().addAll(ta, prenxt, box, bac);
		setCenter(rt);

		insrtBtn.setOnAction(e -> {
			Order order;
			String customerName;
			order = queue.denqueue();

			if (order != null) {
				sold.push(order);
				customerName = order.getCustomer().getName();
				ntxt.setText(customerName);

			} else {
				showAlert(AlertType.CONFIRMATION, "ooooh", "the empty queue",
						"there are no more element in the queue!!!!!!.");
			}
		});
		stat.setOnAction(e -> {
			String se;
			String seN;
			List<Order> orl;
			Iterator<Order> it;
			se = stat.getValue();
			seN = ntxt.getText();
			Order order;

			if (seN != null && se != null) {
				orl = new ArrayList<>();
				it = orl.iterator();

				while (it.hasNext() == true) {
					order = it.next();
					if (order.getCustomer().getName().equals(seN)) {
						if (se.equals("Finished")) {
							order.setStatus("Finished");
						}
						break;
					}
				}
			}
		});

		nxtBtn.setOnAction(e -> {
			String customerName;
			Order order;
			String se;
			order = queue.denqueue();

			if (order != null) {
				sold.push(order);

				customerName = order.getCustomer().getName();
				ntxt.setText(customerName);

				se = stat.getValue();
				if (se != null)
					if (se.equals("Finished")) {
						order.setStatus("Finished");

					} else if (se.equals("InProcess")) {
						order.setStatus("InProcess");
					}
			} else {

			}
		});

		rptBtn.setOnAction(e -> {
			proder();
			CarAgency c;
			String s;
			try {
				s = reporCars();
				c = new CarAgency();
				c.proder();
				ta.setText(s);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

	private void prevb() {// to go to previous brand node
		if (carBrands != null) {
			String s = queue.getfront().getData().getCustomer().getName();
			// ta.setText(s);
		}
	}

	private void nextb() {// to go to next brand node
		if (carBrands != null) {
			String s = queue.getfront().getNext().getData().getCustomer().getName();

			ta.setText(s);

		}
	}

	public void proder() {// here the admin could process the order using this method.
		Order order;
		if (!queue.isEmpty()) {
			order = queue.denqueue();
			Cars car = order.getCar();
			DoneOrder.push(order);
			CarBrand carB = searchB(car.getBrand());
			if (carB != null) {
				carB.getList_Car().remove(car);
			}
		} else {
			System.out.println("an error occure!!!!!!!!!!");
		}
	}

	public CarBrand searchB(String brand) {// this method is to search for the brand .
		Node currB;
		CarBrand carBrand;
		currB = list.getHead();
		while (currB != null) {
			carBrand = currB.getData();
			if (carBrand.getBrand().equalsIgnoreCase(brand)) {// make it ignore case that is more effeciant.
				return carBrand;
			} else {
				System.out.println("there are some error occure !!!!!!!!!!!");
			}
			currB = currB.getNext();
		}
		return null;
	}

	public String reporCars() throws Exception {

		int counter = 0;
		Order o;
		Cars car;
		StringBuilder report;
		String argu;
		report = new StringBuilder("the Last ten cars solded are:\n");
		Stack tmp = new Stack();
		while (counter < 10 && Main.sold.isEmpty() == false) {
			o = Main.sold.peek();
			car = o.getCar();

			tmp.push(o);
			argu = "-->>" + o.getCustomer().getName() + " " + o.getCustomer().getContactInfo() + " " + car.getBrand()
					+ " " + car.getModel() + "  " + car.getYear() + car.getPrice() + " $" + o.getDate() + " "
					+ o.getStatus();
			report.append(argu).append(" \n ");
			Main.sold.pop();
			counter++;
		}
		// 1 2 3 -> 3 2 1 -> 3 2 1

		while (!tmp.isEmpty()) {
			Main.sold.push(tmp.pop());
		}
		System.out.println(report);

		return report.toString();
	}

	private void showAlert(AlertType AlertType, String title, String header, String content) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		alert.showAndWait();
	}
}
