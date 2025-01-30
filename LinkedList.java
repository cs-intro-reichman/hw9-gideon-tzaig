/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		// return the node of this at a given index
		Node current = this.getFirst();

		for (int i = 0; i < index; i ++) {
			current = current.next;
		}
		
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		// index exception 

		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}

		// adding new node to the linked list
		Node newNode = new Node(block);
		Node firstNode = this.getFirst();
		Node lastNode = this.getLast();

		if (index == 0) {
			newNode.next = firstNode;
			this.first = newNode;
		}
		else if (index == this.size) {
			lastNode.next = newNode;
			this.last = newNode;
		}
		else {
			Node beforeNode = this.getNode(index - 1);
			Node nextNode = beforeNode.next;
			
			beforeNode.next = newNode;
			newNode.next = nextNode;
		}
		
		size ++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		// adding new node to the linked list
		Node newNode = new Node(block);
		Node lastNode = this.getLast();

		if (this.size == 0) {
			this.first = newNode;
			this.last = newNode;
		}

		else {
			lastNode.next = newNode;
			this.last = newNode;
		}
		
		size ++;	
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		// adding new node to the linked list
		Node newNode = new Node(block);
		Node firstNode = this.getFirst();

		if (this.size == 0) {
			this.first = newNode;
			this.last = newNode;
		}
		
		else {
			this.first = newNode;
			newNode.next = firstNode;
		}
		
		size ++;
}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		// call the node at the index and return its block content
		Node wantedNode = this.getNode(index);

		return wantedNode.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		// find the node contains this block and return its index
		if (this.size == 0) return -1;
		Node currentNode = this.getFirst();

		for (int i = 0; i < this.getSize(); i ++) {
			if (currentNode.block.equals(block)) return i;
			currentNode = currentNode.next;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		// find the node and disconnect it from the linked list
		if (this.size == 0) return;
		Node prevNode = null;
		Node currentNode = this.getFirst();

		// itaretes till finds the wanted node
		while (currentNode != null && (currentNode.block.equals(node.block) == false)) {
			prevNode = currentNode;
			currentNode = currentNode.next;
		}
		
		// return if not found
		if (currentNode == null) return;
		// if its the first node, disconnect it
		if (prevNode == null) {
			this.first = this.getFirst().next;
		}
		// if its the last node, disconnect it and redefine the last
		else if (currentNode.next == null) {
			prevNode.next = null;
			this.last = prevNode;
		}
		// for every other node
		else {
			prevNode.next = currentNode.next;
		}

		size --;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		// find the node at the index and remove it
		Node toRemoveNode = this.getNode(index);

		remove(toRemoveNode);
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		// find the index of the node contains the block and remove it
		int toRemoveIndex = indexOf(block);
		
		remove(toRemoveIndex);
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		// return a visual representation of this list
		String s = "";
		Node current = first;

		while (current != null) {
		s = s + current.block + " ";
		current = current.next;
		}

		return s;
	}
}