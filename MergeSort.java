package datastructures;

import java.util.Random;

public class MergeSort {
	
	private static int[] theArray = new int[10];
	private static int[] helper = new int[10];
	
	private static int arraySize = 10;
	
	public static void generateRandomArray() {
		for(int i = 0 ; i < arraySize; i++) {
			theArray[i] = (int)(Math.random()*10)+10;
		}
	}
	
	static void displayArray() {
		for(int i=0; i<arraySize; i++) {
			System.out.print(theArray[i] +  " " );
		}
	}
	
	public static void main(String[] args) {
		generateRandomArray();
		displayArray();
		System.out.println();
		mergeSort(0, arraySize - 1);
		System.out.println("After sorting-");
		displayArray();
	}
	
	
	static void mergeSort(int low, int high) {
		if(low < high) {
			int mid = (low + high) / 2;
			mergeSort(low, mid);
			mergeSort(mid + 1, high);
			merge(low, mid, high);
		}
	}
	
	static void merge(int low, int mid, int high) {
		
		for(int i = low; i <= high; i++) {
			helper[i] = theArray[i];
		}
		
		// To keep track of helper array
		int i = low;
		int j = mid + 1;
		
		// To keep track of theArray
		int k = low;
		
		while(i <= mid && j <= high) {
			if ( helper[i] <= helper[j]) {
				theArray[k] = helper[i];
				i++;
			} else {
				theArray[k] = helper[j];
				j++;
			}
			k++;
		}
		
		while( i<= mid) {
			theArray[k] = helper[i];
			k++;
			i++;
		}
	}
	
}
