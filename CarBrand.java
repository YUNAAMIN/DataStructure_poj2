package application;

/*here is the car brand class that it has an aggregation 
 * relationship with car informationList that have the car information init
 * so directly we find that the list of information will be inside the doubly
 *  linked list for the car brands as we take in java2 using aggregation relation
 *   ship and its also implements comparable depends on car brand sorted alphabitcly
 *   and there is toString method too that shows us the car brands that we got or especially what we have*/
public class CarBrand implements Comparable<CarBrand> {

	private String Brand;
	InfoCarList list_Car;

	public CarBrand(String brand) {

		this.Brand = brand;
		list_Car = new InfoCarList();

	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		this.Brand = brand;
	}

	public InfoCarList getList_Car() {
		return list_Car;
	} 

	public void setList_Car(InfoCarList list_Car) {
		this.list_Car = list_Car;
	}

	@Override
	public String toString() {
		return "CarBrand [Brand=" + Brand + "]" + list_Car.getAllCar();
	}

	@Override
	public int compareTo(CarBrand o) {
		return this.Brand.compareTo(o.Brand);
	}

}
