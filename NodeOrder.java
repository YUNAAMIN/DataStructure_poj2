package application;

public class NodeOrder {//this node is especially for the order

	private Order data;
	private NodeOrder next;

	public NodeOrder(Order order) {
		this.data = order;
		this.next = null;
	}
	public NodeOrder() {
		this.data = data;
		this.next = null;
	}

	public Order getData() {
		return data;
	}

	public void setData(Order data) {
		this.data = data;
	}

	public NodeOrder getNext() {
		return next;
	}

	public void setNext(NodeOrder next) {
		this.next = next;
	}
	

}
