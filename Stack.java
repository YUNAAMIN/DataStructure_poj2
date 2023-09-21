package application;

import java.util.NoSuchElementException;

class Stack {// this is the stack class that contains
	// the basic three methods pop push peek
	// and other method that may be usiful
	private NodeOrder top;
	private int size;

	public void setTop(NodeOrder top) {
		this.top = top;
	}

	public NodeOrder getTop() {
		return top;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Stack() {
		top = null;
		size = 0;
	}

	public void push(Order order) {
		NodeOrder newNode;
		newNode = new NodeOrder(order);
		newNode.setNext(top);
		top = newNode;
		size++;
	}

	public Order pop() {
		Order item;
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty");
		}
		item = top.getData();
		top = top.getNext();
		size--;
		return item;
	}

	public Order peek() {
		if (isEmpty()) {
			throw new IllegalStateException("Stack is empty!!!!!");
		}
		return top.getData();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}

	public void clear() {
		top = null;
		size = 0;
	}

	public Order get(int i) throws Exception {
		NodeOrder current;
		int curIndex;
		if (i >= size || i < 0) {
			throw new IndexOutOfBoundsException("Invalid index check outttt!!!: " + i);
		}
		current = top;
		curIndex = 0;

		while (current != null) {
			if (curIndex == i) {
				return current.getData();
			}
			current = current.getNext();
			curIndex++;
		}

		throw new Exception("ERRRRROOOR!!!!!!!!!!: " + i);
	}

	public void print() {
		Stack temp;
		Order item;
		temp = new Stack();

		if (isEmpty()) {
			System.out.println("The stack is empty!!!!!!");
		} else {
			while (!isEmpty()) {
				item = pop();
				temp.push(item);
				System.out.println(item);
			}

			while (!temp.isEmpty()) {
				push(temp.pop());
			}
		}
	}

}
