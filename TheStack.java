package datastructures;

import java.util.Arrays;

public class TheStack {
	
	private String[] stackArray;
	
	private int stackSize;
	
	private int topOfStack = -1;
	
	public TheStack(int size) {
		stackSize = size;
		stackArray = new String[size];
		Arrays.fill(stackArray, "-1");
	}
	
	public void push(String input) {
		if(topOfStack + 1 < stackSize) {
			topOfStack++;
			stackArray[topOfStack] = input;
		}
		else System.out.println("Sorry, but the Stack is full");
		display();
		System.out.println("Push " + input + " was added to the stack");
	}
	
	public String pop() {
		if(topOfStack < 0) {
			System.out.println("The stack is empty!");
			return "-1";
		}
		else {
			System.out.println(stackArray[topOfStack] + " popped!");
			stackArray[topOfStack] = "-1";
			return stackArray[topOfStack--];
		}
	}
	
	public String peek() {
		display();
		if(topOfStack < 0) {
			System.out.println("The stack is empty!");
			return "-1";
		}
		else {
			System.out.println(stackArray[topOfStack] + " is at the top!");
			return stackArray[topOfStack];
		}
	}
	
	public void display() {
		for(int i=0; i< stackSize-1; i++) {
			System.out.print(stackArray[i] + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		TheStack stack = new TheStack(10);
		stack.push("10");
		stack.push("20");
		stack.push("30");
		stack.push("40");
		stack.pop();
		stack.display();
		stack.peek();
	}

}
