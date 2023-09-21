package application;

class Node {// class Node for double Linked list especially for brands that takes the car
			// brand object as a data usin at this project.

	public CarBrand data;
	Node prev;
	Node next;

	public Node(CarBrand data) {
		this.data = data;

	}

	public Node() {
		this.data = data;

	}

	public CarBrand getData() {
		return data;
	}

	public void setData(CarBrand data) {
		this.data = data;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;

	}

}
