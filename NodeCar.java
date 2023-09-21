package application;

public class NodeCar {//this node is especially for the cars 

	private Cars data;
	private NodeCar next;

	public NodeCar(Cars element) {
		this.data = element;
		this.next = null;
	}

	public NodeCar() {
		this.data = data;
		this.next = null;
	}

	public NodeCar(Order order) {
		this.data = data;
		this.next = null;
	}

	public Cars getData() {
		return data;
	}

	public void setData(Cars data) {
		this.data = data;
	}

	public NodeCar getNext() {
		return next;
	}

	public void setNext(NodeCar next) {
		this.next = next;
	}
}