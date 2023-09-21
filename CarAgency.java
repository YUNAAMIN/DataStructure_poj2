
package application;

import java.io.*;
import java.util.*;
import java.lang.Iterable;

/*
 * this is the agency class that contains all the method that i will need
 * reading file and making alco the orders
 */
public class CarAgency {
	public InfoCarList cars;
	public static QueueL queue;
	public static Stack DoneOrder;
	public static BrandList brandlist;

	/*
	 * this is the agency class that contains all the method that i will need
	 * reading file and making alco the orders
	 */
	public CarAgency() {
		this.cars = cars;
		this.queue = queue;
		this.DoneOrder = DoneOrder;
		this.brandlist = brandlist;
	}

	public void saveData(String filename) {// method to save the data.
		Node currB;
		CarBrand carBrand;
		NodeCar currC;
		currB = brandlist.getHead();
		try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
			while (currB != null) {
				carBrand = currB.getData();
				currC = carBrand.getList_Car().getHead();
				while (currC != null) {
					Cars car = currC.getData();
					writer.println(carBrand.getBrand() + "," + car.getModel() + "," + car.getYear() + ","
							+ car.getColor() + "," + car.getPrice() + "$");
					currC = currC.getNext();
				}
				currB = currB.getNext();
			}
		} catch (IOException e) {
			System.out.println(e + "an error occure while saving the data!!!!!!!!1");
		}
	}

	public static void readOrders(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner scanner;
		String line;
		Cars car;
		Customer customer;
		Order order;
		String name;
		if (file.exists()) {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				System.out.println(line);
				String[] data = line.split(",");
				if (data.length <= 9) {
					System.out.println("Invalid data format: " + line);
					continue;
				}
				name = data[0].trim();
				if (name.equals("CustomerName"))
					continue;
				String mobile = data[1].trim();
				String brand = data[2].trim();
				String model = data[3].trim();
				String year = data[4].trim();
				String color = data[5].trim();
				String price = data[6].trim();
				String date = data[7].trim();
				String status = data[8].trim();

				car = new Cars(brand, model, year, color, price);
				customer = new Customer(name, mobile);
				order = new Order(customer, car, date, status);
				if (status.equals("InProcess"))
					DoneOrder.push(order);
				else
					queue.enqueue(order);

				System.out.println(queue + "    " + DoneOrder);
			}
			scanner.close();
		} else {
			System.out.println("File not found: " + filename);
		}
	}

	public void updateB(String newb, String old) {// this method is to update the brands of the car.
		Cars cb;
		cb = brandlist.NameSearch(old);
		if (cb != null) {
			cb.setBrand(newb);
		} else
			System.out.println("there is some errors occure!!!!!!!!");
	}

	public CarBrand searchB(String brand) {// this method is to search the brands of the car.
		Node currB;
		CarBrand carBrand;
		currB = brandlist.getHead();
		while (currB != null) {
			carBrand = currB.getData();
			if (carBrand.getBrand().equalsIgnoreCase(brand)) {
				return carBrand;
			} else {
				System.out.println("there are some error occure !!!!!!!!!!!");
			}
			currB = currB.getNext();
		}
		return null;
	}

	public void add(String brand, String model, String year, String color, String price) {// this method is to adde the
																							// brands model year color
																							// and the price of the car.
		CarBrand carBrand;
		Cars car;
		carBrand = searchB(brand);
		if (carBrand != null) {
			car = new Cars(brand, model, year, color, price);
			carBrand.getList_Car().add(car);
		} else {
			System.out.println("there are some error occure !!!!!!!!!!!");

		}
	}

	public void delete(String brand, String model, String year, String color, String price) {// this method is to delete
																								// the brands models
																								// price year and color
																								// of the car.
		CarBrand carBrand;
		Cars car;
		carBrand = searchB(brand);
		if (carBrand != null) {
			car = searchCar(carBrand, model, year, color, price);
			if (car != null) {
				carBrand.getList_Car().remove(car);
			} else {
				System.out.println("there are some error occure !!!!!!!!!!!");
			}
		} else {
			System.out.println("there are some error occure !!!!!!!!!!!");
		}
	}

	public void updateC(String brand, String oldM, String oldY, String oldP, String oldC, String newM, String newY,
			String newP) {// this method is to update the brands of the car.that is replace the olds ones
							// with the new ones
		CarBrand carBrand;
		Cars car;
		carBrand = searchB(brand);
		if (carBrand != null) {
			car = searchCar(carBrand, oldM, oldY, oldC, oldP);
			if (car != null) {
				car.setModel(newM);
				car.setYear(newY);
				car.setPrice(newP);
			} else {
				System.out.println("there are some error occure !!!!!!!!!!!");
			}
		} else {
			System.out.println("there are some error occure !!!!!!!!!!!");
		}
	}

	public Cars searchCar(CarBrand Brand, String model, String year, String color, String price) {// this method is
																									// to search the
																									// brand model
																									// color yrear
																									// and price of
																									// the car.
		NodeCar currentCar;
		Cars car;
		currentCar = Brand.getList_Car().getHead();
		while (currentCar != null) {
			car = currentCar.getData();
			if (car.getModel().equalsIgnoreCase(model) && car.getYear().equals(year) && car.getColor().equals(color)
					&& car.getPrice().equals(price)) {
				return car;
			}
			currentCar = currentCar.getNext();
		}
		return null;
	}

	public void proder() {// this method is to process the data it will be usifull for the admins.
		Order order;
		Cars car;
		CarBrand carB;
		if (queue!=null) {
			order = queue.denqueue();
			car = order.getCar();
			DoneOrder.push(order);
			carB = searchB(car.getBrand());
			if (carB != null) {
				carB.getList_Car().remove(car);
			} else {
				System.out.println("an error occure!!!!!!!!!!");
			}
		} else {
			System.out.println("an error occure!!!!!!!!!!");
		}
	}

	public String reportLastSoldCars() throws Exception {
		int counter = 0;
		Order order;
		Cars car;
		StringBuilder report;
		String argu;
		report = new StringBuilder("the Last ten cars solded are:\n");

		while (counter < 10 && DoneOrder.isEmpty() == false) {
			order = DoneOrder.peek();
			car = order.getCar();

			argu = order.getCustomer().getName() + order.getCustomer().getContactInfo() + car.getBrand() + " "
					+ car.getModel() + " " + car.getYear() + car.getPrice() + " $" + order.getDate() + " "
					+ order.getStatus();
			report.append(argu).append("\n");
			DoneOrder.pop();
			counter++;
		}

		return report.toString();
	}

	public static void saveOrder(String filename) throws Exception {
		File file;
		FileWriter writer;
		file = new File(filename);
		writer = new FileWriter(file);

		while (!queue.isEmpty()) {
			writer.write(queue.denqueue() + "\n");
		}
		while (!DoneOrder.isEmpty()) {
			writer.write(DoneOrder.pop() + "\n");
		}
		writer.close();
	}

	public static void readOrdersFromFile(String filename) throws Exception {
		File file;
		Scanner scanner;
		String line;
		String orderStatus;
		Order order;
		file = new File(filename);
		if (file.exists()) {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				line = scanner.nextLine();
				if (line.trim().isEmpty()) {
					continue;
				}
				String[] data = line.split(",");
				orderStatus = data[data.length - 1].trim();
				if (orderStatus.equals("Finished")) {
					order = createOrderFromData(data);
					DoneOrder.push(order);
				} else if (orderStatus.equals("InProcess")) {
					order = createOrderFromData(data);
					queue.enqueue(order);
				}
			}
			scanner.close();
		} else {
			throw new FileNotFoundException("File not found EXCEPTIOOON!!: " + filename);
		}
	}

	private static Order createOrderFromData(String[] data) {
		Cars car;
		Customer customer;
		Order order;
		String name = data[0].trim();
		String phone = data[1].trim();
		String brand = data[2].trim();
		String model = data[3].trim();
		String year = data[4].trim();
		String color = data[5].trim();
		String price = data[6].trim();
		String orderDate = data[7].trim();
		String orderStatus = data[8].trim();

		car = new Cars(brand, model, year, color, price);
		customer = new Customer(name, phone);
		order = new Order(customer, car, orderDate, orderStatus);
		return order;
	}
}
