package application;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import application.Node;

public class BrandList {// this class the brand list that contains all the functionality and method that
						// can be usefull for the brands.

	private CarBrand data;
	private Node head;
	private Node tail;
	private int Size;

	public BrandList(CarBrand name) {
		this.head = null;
		this.Size = 0;
	}

	public CarBrand getData() {
		return data;
	}

	public void setData(CarBrand data) {
		this.data = data;
	}

	public Node getTail() {
		return tail;
	}

	public void setTail(Node tail) {
		this.tail = tail;
	}

	public void setSize(int size) {
		Size = size;
	}

	public void setName(CarBrand name) {
		this.data = name;
	}

	public Node getHead() {
		return head;
	}

	public void setHead(Node head) {
		this.head = head;
	}

	public BrandList() {
		Node dummy = new Node(null);
		head = dummy;
	}

	public int getSize() {
		return Size;
	}

	public void insertAtHead(CarBrand data) {
		Node newNode;
		newNode = new Node(data);
		if (head.getNext() != null) {
			newNode.setNext(head.getNext());
			head.getNext().setPrev(newNode);
		}
		head.setNext(newNode);
		newNode.setPrev(head);

	}

	public void insertLast(CarBrand data) {
		Node newNode;
		Node current;
		newNode = new Node(data);
		current = head.getNext();
		while (current.getNext() != null)
			current = current.getNext();
		current.setNext(newNode);
	}

	public void insertSorted(CarBrand data) {
		Node newNode;
		Node current;

		newNode = new Node(data);
		current = head;

		while (current.getNext() != null && current.getNext().getData().compareTo(data) < 0)
			current = current.getNext();

		if (current.getNext() == null) {
			newNode.setPrev(current);
			current.setNext(newNode);
		} else {
			newNode.setNext(current.getNext());
			newNode.setPrev(current);
			current.getNext().setPrev(newNode);
			current.setNext(newNode);
		}
	}

	public CarBrand delete(CarBrand data) {
		Node current;
		CarBrand temp;
		current = head;

		while (current.getNext() != null && current.getNext().getData().compareTo(data) <= 0)
			current = current.getNext();
		if (current != head && current.getNext().getData().compareTo(data) < 0) {
			temp = current.getData();
			if (current.getNext() == null)
				current.getPrev().setNext(null);
			else {
				current.getPrev().setNext(current.getNext());
				current.getNext().setPrev(current.getPrev());
			}

		}
		return null;
	}

	public void printList() {// this method is used to print the list
		Node currentNode;
		currentNode = head;
		while (currentNode != null) {
			currentNode = currentNode.next;
			System.out.print(currentNode.data + " ");

		}
		System.out.println();
	}

	public String getDataAsString() {
		Node curr;
		String full;
		curr = head;
		full = " ";
		try {
			while (curr != null) {
				System.out.print(curr.getData() + " ");
				curr = curr.next;
				full += curr.data + " ";
			}
			full += "/n";
		} catch (NullPointerException ex) {
			full = "";
		}
		return full;
	}

	public void insertSorted1(CarBrand value) {// to insert sorted
		Node newNode = new Node(value);

		if (head.getData() == null) {
			System.out.println("test");
			head = newNode;
			tail = newNode;
		} else if (value.compareTo(head.getData()) < 0) {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		} else if (value.compareTo(tail.getData()) >= 0) {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		} else {
			Node current = head.next;

			while (current != null && current.data.compareTo(value) < 0) {
				current = current.next;
			}

			Node prev = current.prev;

			prev.next = newNode;
			newNode.prev = prev;
			newNode.next = current;
			current.prev = newNode;
		}
		Size++;
	}

	public Node search(String brand) {
		Node temp;
		temp = head;
		while (temp != null) {
			if (temp.data != null && temp.data.getBrand().equalsIgnoreCase(brand)) {
				return temp;
			}
			temp = temp.next;
		}
		return null;

	}

	public NodeCar searchByColor(String color) {
		Node current = head.getNext();
		while (current != null) {
			NodeCar nodeCar = (NodeCar) current.getData().getList_Car().getHead();
			while (nodeCar != null) {
				if (nodeCar.getData().getColor().equalsIgnoreCase(color)) {
					return nodeCar;
				}
				nodeCar = (NodeCar) nodeCar.getNext();
			}
			current = current.getNext();
		}
		return null;
	}
	


	public String getAllCari() {// this method used to get all the cars
		StringBuilder str;
		Node currentNodeCar;
		str = new StringBuilder();
		currentNodeCar = head.getNext();
		while (currentNodeCar != null) {
			str.append(currentNodeCar.getData()).append("\n");
			currentNodeCar = currentNodeCar.getNext();
		}
		return str.toString();

	}


	public Cars NameSearch(String name) {
		Node temp;
		NodeCar temp2;
		temp = head;
		while (temp != null) {
			temp2 = temp.data.list_Car.getHead();
			while (temp2 != null) {
				if (temp2.getData().getBrand().equalsIgnoreCase(name)) {
					return temp2.getData();
				}
				temp2 = temp2.getNext();
			}
			temp = temp.next;

		}
		return null;

	}

	public Cars ColorSearch(String name) {
		Node temp;
		NodeCar temp2;
		temp = head;
		while (temp != null) {
			temp2 = temp.data.list_Car.getHead();
			while (temp2 != null) {
				if (temp2.getData().getColor().equalsIgnoreCase(name)) {
					return temp2.getData();
				}
				temp2 = temp2.getNext();
			}
			temp = temp.next;

		}
		return null;

	}

	public Cars PriceSearch(String name) {
		Node temp;
		NodeCar temp2;
		temp = head;
		while (temp != null) {
			temp2 = temp.data.list_Car.getHead();
			while (temp2 != null) {
				if (temp2.getData().getPrice().equalsIgnoreCase(name)) {
					return temp2.getData();
				}
				temp2 = temp2.getNext();
			}
			temp = temp.next;

		}
		return null;

	}

	public Cars ModelSearch(String name) {
		Node temp;
		NodeCar temp2;
		temp = head;
		while (temp != null) {
			temp2 = temp.data.list_Car.getHead();
			while (temp2 != null) {
				if (temp2.getData().getModel().equalsIgnoreCase(name)) {
					return temp2.getData();
				}
				temp2 = temp2.getNext();

			}
			temp = temp.next;

		}
		return null;

	}

	public Cars PriceColorSearch(String color, String price) {
		Node currB;
		Cars car;
		NodeCar currC;
		currB = head.getNext();
		car = null;
		currC = null;

		while (currB != null) {
			currC = currB.getData().getList_Car().getHead();

			while (currC != null) {
				car = currC.getData();

				if (car.getPrice().equalsIgnoreCase(price) && car.getColor().equalsIgnoreCase(color)) {
					return car;
				}

				currC = currC.getNext();
			}

			currC = currC.getNext();
		}

		return null;
	}

	public Cars ModelPriceSearch(String model, String price) {
		Node currB;
		Cars car;
		NodeCar currC;
		currB = head.getNext();
		car = null;
		currC = null;

		while (currB != null) {
			currC = currB.getData().getList_Car().getHead();

			while (currC != null) {
				car = currC.getData();

				if (car.getPrice().equalsIgnoreCase(price) && car.getModel().equalsIgnoreCase(model)) {
					return car;
				}

				currC = currC.getNext();
			}

			currC = currC.getNext();
		}

		return null;
	}

	public Cars YearModelSearch(String model, String year) {
		Node currB;
		Cars car;
		NodeCar currC;
		currB = head.getNext();
		car = null;
		currC = null;

		while (currB != null) {
			currC = currB.getData().getList_Car().getHead();

			while (currC != null) {
				car = currC.getData();

				if (car.getYear().equalsIgnoreCase(year) && car.getModel().equalsIgnoreCase(model)) {
					return car;
				}

				currC = currC.getNext();
			}

			currB = currB.getNext();
		}

		return null;
	}

	public Cars YearSearch(String name) {
		Node temp;
		NodeCar temp2;
		temp = head;
		while (temp != null) {
			temp2 = temp.data.list_Car.getHead();
			while (temp2 != null) {
				if (temp2.getData().getModel().equalsIgnoreCase(name)) {
					return temp2.getData();
				}
				temp2 = temp2.getNext();

			}
			temp = temp.next;

		}
		return null;

	}

	public String getItem(int index) {// his method to get specific brand ant specific index
		Node currentNode;
		if (index < Size) {
			currentNode = head;
			for (int i = 0; i < index; i++) {
				currentNode = currentNode.next;
			}
			return currentNode.data + "  ";
		}
		return null;
	}

	public void removeAll() {// to remove all the nodes
		tail = null;
		Size = 0;
		head = null;

	}

	public void insertNode(CarBrand martyr) {// this method to nsert node that is the brand
		Node newNode;
		newNode = new Node(martyr);

		if (head != null) {
			newNode.prev = tail;
			tail.next = newNode;
			tail = newNode;

		} else {
			head = newNode;
			tail = newNode;
		}
		Size++;
	}

	public void writeToFile(String filename) {// method that will write the brands to the file
		Node currentNode;
		String currC;
		try (PrintWriter writer = new PrintWriter(filename)) {
			currentNode = getHead();
			while (currentNode != null) {
				currC = currentNode.getData().toString();
				if (currC != null) {
					writer.println(currC.toString());
				}
				currentNode = currentNode.getNext();
			}
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred while writing the data to the file: " + e.getMessage());
		}
	}

	public void remove(Object obj) {// this method is to remove the node that is the brand
		Node currentNode;
		Node prevNode;
		Node nextNode;
		currentNode = head;

		while (currentNode != null) {
			if (currentNode.data.equals(obj)) {
				prevNode = currentNode.prev;
				nextNode = currentNode.next;

				if (prevNode != null) {
					prevNode.next = nextNode;
					Size--;
				} else {
					head = nextNode;
				}

				if (nextNode != null) {
					nextNode.prev = prevNode;
				} else {
					tail = prevNode;
				}

				currentNode.prev = null;
				currentNode.next = null;
				Size--;

				return;
			}

			currentNode = currentNode.next;
			Size--;
		}

	}

	public String get(int indexe) throws Exception {// to get specific node
		Node current;
		if (indexe >= Size || indexe < 0) {
			throw new Exception("Invalid index");
		}

		current = head;
		for (int i = 0; i < indexe; i++) {
			current = current.getNext();
		}
		return current.getData().getBrand();
	}

	public String getAllCar() {// this method used to get all the cars
		StringBuilder str;
		Node currentNodeCar;
		str = new StringBuilder();
		currentNodeCar = head;
		while (currentNodeCar != null) {
			str.append(currentNodeCar.getData()).append("\n");
			currentNodeCar = currentNodeCar.getNext();
		}
		return str.toString();

	}

	
	public boolean isEmpty() {// to check if its empty
		return (Size == 0);
	}
}
