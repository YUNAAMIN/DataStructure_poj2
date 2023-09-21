package application;

public class QueueL {// this is the queue class that contains in basic three methods enqueue,dequeue
						// and front or peek and other methods that maybe useful ands its depends on the
						// orders class.

	private NodeOrder front;
	private NodeOrder rear;
	private int size;

	public QueueL() {
		front = rear = null;
		size = 0;
	}

	public void enqueue(Order order) {// like add first method in the linked
		// list.
		NodeOrder newNode;
		newNode = new NodeOrder(order);
		if (isEmpty()) {
			front = newNode;
			rear = newNode;
		} else {
			rear.setNext(newNode);
			rear = newNode;
		}
		size++;

	}

	public Order denqueue() {// remove first so its like fifo
		Order item;
		if (isEmpty()) {

			throw new IllegalStateException("empty");
		}
		item = front.getData();
		front = front.getNext();

		if (front == null) {
			rear = null;
		}
		size--;
		return item;
	}

	public NodeOrder getfrontt() {
		return front;
	}

	public void setfrontt(NodeOrder front) {
		this.front = front;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;

	}

	public NodeOrder getfront() {
		if (isEmpty())
			throw new IllegalStateException("empty");
		else
			return front;

	}

	public void print() {
		QueueL temp;

		temp = new QueueL();
		if (isEmpty())
			System.out.println(" the queue is empty");
		else
			while (!this.isEmpty()) {
				Order rem = this.denqueue();
				temp.enqueue(rem);
			}
		while (!temp.isEmpty()) {
			Order rem = temp.denqueue();
			this.enqueue(rem);
			System.out.println(rem);

		}

	}

	public void clearQueue() {
		this.front = null;
		this.rear = null;
	}

	public NodeOrder getFront() {
		return front;
	}

	public void setFront(NodeOrder front) {
		this.front = front;
	}

	public NodeOrder getRear() {
		return rear;
	}

	public void setRear(NodeOrder rear) {
		this.rear = rear;
	}
	

}