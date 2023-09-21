package application;

/*this class is the class for the orders 
 * especially here for cars that contains 
 * the information for thae customer like 
 * name and phone number and the car information
 * like brand color  model year and price and
 *  also the order date and status and the class
 *   also have setters and getters */
public class Order {
	private  Customer customer;//to get the the number and the name
	private  Cars car;
	private  String date;
	private  String status;

	public Order(Customer customer, Cars car, String date, String Status) {
		this.customer = customer;
		this.car = car;
		this.date = date;
		this.status = Status;
	}

	public Order() {
		this.customer = customer;
		this.car = car;
		this.date = date;
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Cars getCar() {
		return car;
	}

	public void setCar(Cars car) {
		this.car = car;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {// to string that print out the information as string.
		return "Order-->> customer:" + customer + ", car:" + car + ", date:" + date + ", status:" + status;
	}

}