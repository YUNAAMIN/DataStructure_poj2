package application;

/*THIS IS the car class that implements brand,model,year,
color,price for the cars and its also implemnts 
comparable with type cars and compares depends on the car created year.*/
public class Cars implements Comparable<Cars> {

	private String Brand;
	private String Model;
	private String Year;
	private String Color;
	private String Price;

	public Cars(String brand, String model, String year, String color, String price) {
		super();
		this.Brand = brand;
		this.Model = model;
		this.Year = year;
		this.Color = color;
		this.Price = price;
	}

	public Cars() {

	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		this.Brand = brand;
	}

	public String getModel() {
		return Model;
	}

	public void setModel(String model) {
		this.Model = model;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {

		int numericPrice = Integer.parseInt(year);
		if (numericPrice > 0)
			this.Price = year;
		else
			System.out.println("the year that u choosen is not available !!!!");
	}

	public String getColor() {
		return Color;
	}

	public void setColor(String color) {
		this.Color = color;
	}

	public String getPrice() {
		return Price;
	}

	public void setPrice(String price) {
		double numericPrice = Double.parseDouble(price);
		if (numericPrice > 0)
			this.Price = price;
		else
			System.out.println("the price that u choosen is not available !!!!");
	}

	@Override
	public String toString() {
		return Brand + "/" + Year + "/" + Color + "/" + Model + "/" + Price;
	}

	@Override
	public int compareTo(Cars object) {
		// TODO Auto-generated method stub
		return this.Year.compareTo((object).getYear());
	}

}
