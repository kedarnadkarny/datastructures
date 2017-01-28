package datastructures;

public class BinaryTree {

	Node root;
	
	public void addNode(int key, String name) {
		Node newNode = new Node(key, name);
		
		if(root == null) {
			root = newNode;
		} else {
			Node focusNode = root;
			Node parent;
			while(true) {
				parent = focusNode;
				if(key < focusNode.key) {
					focusNode = focusNode.leftChild;
					if(focusNode == null) {
						parent.leftChild = newNode;
						return;
					}
				} else {
					focusNode = focusNode.rightChild;
					if(focusNode == null) {
						parent.rightChild = newNode;
						return;
					}
				}
			}
		}
	}
  	
	public void inOrderTraverseTree(Node focusNode) {
		if(focusNode != null) {
			inOrderTraverseTree(focusNode.leftChild);
			System.out.println(focusNode);
			inOrderTraverseTree(focusNode.rightChild);
		}
	}
	
	public void preTraverseTree(Node focusNode) {
		if(focusNode != null) {
			System.out.println(focusNode);
			preTraverseTree(focusNode.leftChild);
			preTraverseTree(focusNode.rightChild);
		}
	}
	
	public void postTraverseTree(Node focusNode) {
		if(focusNode != null) {
			postTraverseTree(focusNode.leftChild);
			postTraverseTree(focusNode.rightChild);
			System.out.println(focusNode);
		}
	}
	
	public Node findNode(int key) {
		Node focusNode = root;
		
		while(focusNode.key != key) {
			if(key < focusNode.key) {
				focusNode = focusNode.leftChild;
			} else {
				focusNode = focusNode.rightChild;
			}
			
			if(focusNode == null)
				return null;
		}
		return focusNode;
	}
	
	public boolean removeNode(int key) {
		Node focusNode = root;
		Node parent = root;
		
		boolean isItALeftChild = true;
		
		while(focusNode.key != key) {
			parent = focusNode;
			if(key < focusNode.key) {
				isItALeftChild = true;
				
				focusNode = focusNode.leftChild;
			} else {
				isItALeftChild = false;
				
				focusNode = focusNode.rightChild;
			}
			
			if(focusNode == null)
				return false;
		}
		
		// No children
		if(focusNode.leftChild == null && focusNode.rightChild == null) {
			// Is the root
			if(focusNode == root) {
				root = null;	
			} else if (isItALeftChild) { // FocusNode is a leaf node
				parent.leftChild = null;
			} else {					// FocusNode is a leaf node
				parent.rightChild = null;
			}
		}
		// Has no right child
		else if(focusNode.rightChild == null) {
			if(focusNode == root)				// If root with no right child
				root = focusNode.leftChild;
			
			else if(isItALeftChild)				// If not root but a Left Child
				parent.leftChild = focusNode.leftChild;
			
			else parent.rightChild = focusNode.leftChild; // If not root but a Right Child
		}
		// Has no left child
		else if(focusNode.leftChild == null) {
			if(focusNode == root)				// If not root but a Left Child
				root = focusNode.rightChild;
			
			else if(isItALeftChild)				//If not root but a Left Child
				parent.leftChild = focusNode.rightChild;
			
			else parent.rightChild = focusNode.rightChild;	// If not root but a Right child
		}
		
		else {
			Node replacement = getReplacementNode(focusNode);
			if(focusNode == root) 
				root = replacement;
			
			else if (isItALeftChild)
				parent.leftChild = replacement;
			
			else
				parent.rightChild = replacement;
			
			replacement.leftChild = focusNode.leftChild;
		}
		
		return true;
	}
	
	public Node getReplacementNode(Node replacedNode) {
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		
		Node focusNode = replacedNode.rightChild;
		
		while(focusNode != null) {
			replacementParent = replacement;
			replacement = focusNode;
			focusNode = focusNode.leftChild;
		}
		
		if(replacement != replacedNode.rightChild) {
			replacementParent.leftChild = replacement.rightChild;
			replacement.rightChild = replacedNode.rightChild;
		}
		
		return replacement;
		
	}
	
	public static void main(String[] args) {
		BinaryTree theTree = new BinaryTree();
		theTree.addNode(50, "Boss");
		theTree.addNode(25, "Vice Pres");
		theTree.addNode(15, "Office Manager");
		theTree.addNode(30, "Secretary");
		theTree.addNode(75, "Sales Manager");
		theTree.addNode(85, "Salesman 1");
		
/*		theTree.inOrderTraverseTree(theTree.root);
		System.out.println();
		theTree.preTraverseTree(theTree.root);
		System.out.println();
		theTree.postTraverseTree(theTree.root);*/ 
		
		// System.out.println(theTree.findNode(30));
		
		theTree.removeNode(25);
		theTree.preTraverseTree(theTree.root);
	}
	
}


class Node {
	int key;
	String name;
	
	Node leftChild;
	Node rightChild;
	
	Node(int key, String name) {
		this.key = key;
		this.name = name;
	}
	
	public String toString() {
		return name + " has a key " + key;
	}
}