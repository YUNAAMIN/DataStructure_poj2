package application;

public class Customer {// this is the customer class that we can get the customer information like name
						// and the phone number.
	private String Name;
	private String contact;

	public Customer(String name, String contact) {
		this.Name = name;
		this.contact = contact;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		this.Name = name;
	}

	public String getContactInfo() {
		return contact;
	}

	public void setContactInfo(String contactInfo) {
		this.contact = contactInfo;
	}

	@Override
	public String toString() {
		return "Customer [Name=" + Name + ", contact=" + contact + ", getName()=" + getName() + ", getContactInfo()="
				+ getContactInfo() + "]";
	}

}
