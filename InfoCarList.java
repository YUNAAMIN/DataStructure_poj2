
package application;

import application.NodeCar;

public class InfoCarList {// thid the car information class that if single linked listand the node from
							// type nodeCar.

	private NodeCar head;
	private NodeCar tail;
	private int size;

	public InfoCarList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	public NodeCar getHead() {
		return head;
	}

	public void setHead(NodeCar head) {
		this.head = head;
	}

	public NodeCar getTail() {
		return tail;
	}

	public void setTail(NodeCar tail) {
		this.tail = tail;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void insertAtHead(Cars data) {
		NodeCar newNodeCar;
		newNodeCar = new NodeCar(data);
		newNodeCar.setNext(head);
		head = newNodeCar;
		size++;
	}

	public void insertAtLast(Cars data) {
		NodeCar newNodeCar;
		newNodeCar = new NodeCar(data);
		if (head == null) {
			head = newNodeCar;
			tail = newNodeCar;
		} else {
			tail.setNext(newNodeCar);
			tail = newNodeCar;
		}
		size++;
	}

	public void insertSorted(Cars data) {
		NodeCar newNodeCar;
		NodeCar current;
		newNodeCar = new NodeCar(data);

		if (head == null || data.compareTo(head.getData()) < 0) {
			newNodeCar.setNext(head);
			head = newNodeCar;
		} else {
			current = head;
			while (current.getNext() != null && data.compareTo(current.getNext().getData()) >= 0) {
				current = current.getNext();
			}
			newNodeCar.setNext(current.getNext());
			current.setNext(newNodeCar);
		}
		size++;
	}

	public Cars delete(Cars data) {
		Cars deletedData;
		NodeCar current;
		Cars deleted;
		if (head == null) {
			return null;
		}

		if (data.equals(head.getData())) {
			deletedData = head.getData();
			head = head.getNext();
			size--;
			return deletedData;
		}

		current = head;
		while (!data.equals(current.getNext().getData()) && current.getNext() != null) {
			current = current.getNext();
		}

		if (current.getNext() != null) {
			deleted = current.getNext().getData();
			current.setNext(current.getNext().getNext());
			size--;
			return deleted;
		}

		return null;
	}

	public void printList() {
		NodeCar currentNodeCar;
		currentNodeCar = head;
		while (currentNodeCar != null) {
			System.out.print(currentNodeCar.getData() + "  ");
			currentNodeCar = currentNodeCar.getNext();
		}
		System.out.println();
	}

	public String getAllCar() {
		String str;
		NodeCar currentNodeD;
		str = "";
		currentNodeD = head;
		while (currentNodeD != null) {
			str += currentNodeD.getData() + "\n";
			currentNodeD = currentNodeD.getNext();
		}
		return str;

	}

	public void add(Cars martyr) {
		NodeCar newNodeCar;
		newNodeCar = new NodeCar(martyr);
		if (head == null) {
			head = newNodeCar;
			tail = newNodeCar;
		} else {
			tail.setNext(newNodeCar);
			tail = newNodeCar;
		}
		size++;
	}

	public void insertSorted1(Cars car) {
		NodeCar newNode;
		NodeCar current;
		newNode = new NodeCar(car);
		if (head == null || car.getBrand().compareToIgnoreCase(head.getData().getBrand()) < 0) {
			newNode.setNext(head);
			head = newNode;
		} else {
			current = head;
			while (current.getNext() != null
					&& car.getBrand().compareToIgnoreCase(current.getNext().getData().getBrand()) > 0) {
				current = current.getNext();
			}
			newNode.setNext(current.getNext());
			current.setNext(newNode);
		}
		size++;
	}

	public int getSize() {
		return size;
	}

	public Object getItem(int i) {
		NodeCar curr;
		NodeCar cur;
		if (i < size) {
			cur = head;
			for (int j = 0; j < i; j++) {
				curr = cur.getNext();
			}
			return cur.getData();
		}
		return null;
	}

	public void removeAll() {
		tail = null;
		size = 0;
		head = null;

	}

	public void remove(Object obj) {
		NodeCar current;
		if (head == null) {
			return;
		}

		while (obj.equals(head.getData())) {
			head = head.getNext();
			size--;
		}

		current = head;
		while (current != null && current.getNext() != null) {
			if (obj.equals(current.getNext().getData())) {
				current.setNext(current.getNext().getNext());
				size--;
			} else {
				current = current.getNext();
			}
		}
	}

	public void updateCarInfo(String old, String newM) {
		NodeCar current;
		Cars car;
		current = head;
		while (current != null) {
			car = current.getData();
			if (car.getModel().equalsIgnoreCase(old)) {
				car.setModel(newM);
			}
			current = current.getNext();
		}
	}

	public Cars ModelSearch(String model) {
		NodeCar current;
		Cars car;
		current = head;
		while (current != null) {
			car = current.getData();
			if (car.getModel().equalsIgnoreCase(model)) {
				return car;
			}
			current = current.getNext();
		}
		return null;
	}

	public Cars searchByBrandModelYearIgnoreCase(String brand, String model, String year) {
		String brandL, modelL, yearL;
		String carB, carM, carY;
		Cars car;
		NodeCar current;
		brandL = brand.toLowerCase();
		modelL = model.toLowerCase();
		yearL = year.toLowerCase();

		current = head;
		while (current != null) {
			car = current.getData();
			carB = car.getBrand().toLowerCase();
			carM = car.getModel().toLowerCase();
			carY = car.getYear().toLowerCase();

			if (carY.equalsIgnoreCase(yearL) && carB.equalsIgnoreCase(brandL) && carM.equalsIgnoreCase(modelL)) {
				return car;
			}

			current = current.getNext();
		}

		return null;
	}

	public boolean isEmpty() {

		return (size == 0);
	}


}
