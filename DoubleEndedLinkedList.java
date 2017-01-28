package datastructures;

class Neighbor {
	public String homeOwnerName;
	public int houseNumber;
	
	public Neighbor next;
	public Neighbor previous;
	
	public Neighbor(String homeOwnerName, int houseNumber) {
		this.homeOwnerName = homeOwnerName;
		this.houseNumber = houseNumber;
	}
	
	public void display() {
		System.out.println(homeOwnerName + ": " + houseNumber + " Private Drive");
	}
	
	public String toString() {
		return homeOwnerName;
	}
}

public class DoubleEndedLinkedList {

	Neighbor firstLink;
	Neighbor lastLink;
	
	public boolean isEmpty() {
		return (firstLink == null);
	}
	
	public void insertInFirstPosition(String homeOwnerName, int houseNumber) {
		
		Neighbor theNewLink = new Neighbor(homeOwnerName, houseNumber);
		
		if(isEmpty()) {
			lastLink = theNewLink;
		}
		else {
			firstLink.previous = theNewLink;
		}
		
		theNewLink.next = firstLink;
		firstLink = theNewLink;
	}
	
	public void insertInLastPosition(String homeOwnerName, int houseNumber) {
		Neighbor theNewLink = new Neighbor(homeOwnerName, houseNumber);
		
		if(isEmpty()) {
			firstLink = theNewLink;
		}
		else {
			lastLink.next = theNewLink;	
			theNewLink.previous = lastLink;
		}
		
		lastLink = theNewLink;
		
	}
	
	public boolean insertAfterKey(String homeOwnerName, int houseNumber, int key) {
		Neighbor theNewLink = new Neighbor(homeOwnerName, houseNumber);
		
		Neighbor currentNeighbor = firstLink;
		
		// find the current neighbor first
		while(currentNeighbor.houseNumber != key) {
			currentNeighbor = currentNeighbor.next;
			if(currentNeighbor == null)
				return false;
		}
		
		// post connection of new node if key is last
		if(currentNeighbor == lastLink) {
			theNewLink.next = null;
			lastLink = theNewLink;
		}
		// post connection of new node if key is in the middle
		else {
			theNewLink.next = currentNeighbor.next;
			currentNeighbor.next.previous = theNewLink;
		}
		
		theNewLink.previous = currentNeighbor;
		currentNeighbor.next = theNewLink; 
		return true;
		
	}
	
	public void insertInOrder(String homeOwnerName, int houseNumber) {
		Neighbor theNewLink = new Neighbor(homeOwnerName, houseNumber);
		
		Neighbor previousNeighbor = null;
		Neighbor currentNeighbor = firstLink;
		
		while((currentNeighbor != null) && (houseNumber > currentNeighbor.houseNumber)) {
			previousNeighbor = currentNeighbor;
			currentNeighbor = currentNeighbor.next;
		}
		
		if(previousNeighbor == null) {
			firstLink = theNewLink;
		}
		else {
			previousNeighbor.next = theNewLink;
		}
		
		theNewLink.next = currentNeighbor;
	}
	
	
	public static void main(String[] args) {
		DoubleEndedLinkedList LL = new DoubleEndedLinkedList();
		LL.insertInOrder("Mark", 7);
		LL.insertInOrder("Piers", 9);
		LL.insertInOrder("Dory", 6);
		LL.insertInOrder("Petunia", 4);
		
		LL.insertAfterKey("Derek", 12, 4);
		
		//LL.insertInLastPosition("Michael", 11);
		
		LL.display();
		
		System.out.println("This is Iterator");
		NeighborIterator neighbors = new NeighborIterator(LL);
		neighbors.currentNeighbor.display();
		
		System.out.println(neighbors.hasNext());
		
		neighbors.next();
		
		neighbors.currentNeighbor.display();
		
		neighbors.remove();
		
		neighbors.currentNeighbor.display();
	}
	
	public void display() {
		Neighbor theLink = firstLink;
		while(theLink != null) {
			theLink.display();
			System.out.println("Next Link: " + theLink.next);
			theLink = theLink.next;
			System.out.println();
		}
	}
	
}

class NeighborIterator {
	Neighbor currentNeighbor;
	Neighbor previousNeighbor;
	
	DoubleEndedLinkedList theNeighbours;
	
	public NeighborIterator(DoubleEndedLinkedList theNeighbors) {
		this.theNeighbours = theNeighbors;
		currentNeighbor = theNeighbors.firstLink;
		previousNeighbor = theNeighbors.lastLink;
	}
	
	public boolean hasNext() {
		if(currentNeighbor.next != null) {
			return true;
		}
		return false;
	}
	
	public Neighbor next() {
		if(hasNext()) {
			previousNeighbor = currentNeighbor;
			currentNeighbor = currentNeighbor.next;
			
			return currentNeighbor;
		}
		return null;
	}
	
	
	// removes current neighbor
	public void remove() {
		if(previousNeighbor == null) {
			theNeighbours.firstLink = currentNeighbor.next;
		}
		else {
			previousNeighbor.next = currentNeighbor.next;
			if(currentNeighbor.next == null) {
				currentNeighbor = theNeighbours.firstLink;
				previousNeighbor = null;
			}
			else {
				currentNeighbor = currentNeighbor.next;
			}
		}
	}
}





































