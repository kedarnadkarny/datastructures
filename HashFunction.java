package datastructures;

import java.util.ArrayList;
import java.util.Arrays;

public class HashFunction {

	String[] theArray;
	int arraySize;
	int itemsInArray= 0;
	
	public static void main(String[] args) {
		HashFunction theFunc = new HashFunction(31);
		//String[] elementsToAdd = {"1", "5", "17", "26"};
		//theFunc.hashFunction1(elementsToAdd, theFunc.theArray);
		
		String[] elementsToAdd2 = {"100", "510", "170", "214", "268", "398",
                "235", "802", "900", "723", "699", "1", "16", "999", "890",
                "725", "998", "978", "988", "990", "989", "984", "320", "321",
                "400", "415", "450", "50", "660", "624"};
		//theFunc.hashFunction2(elementsToAdd2, theFunc.theArray);
		
		//theFunc.findKey("170");
		
		String[] elementsToAdd3 = { "30", "60", "90", "120", "150", "180",
				"210", "240", "270", "300", "330", "360", "390", "420", "450",
				"480", "510", "540", "570", "600", "989", "984", "320", "321",
				"400", "415", "450", "50", "660", "624" };
		
		theFunc.hashFunction2(elementsToAdd3, theFunc.theArray);
		
		theFunc.increaseArraySize(60);
		
		theFunc.displayTheStack();
	}
	
	public void hashFunction1(String[] stringsForArray, String[] theArray) {
		for(int n=0; n< stringsForArray.length; n++) {
			String newElementVal = stringsForArray[n];
			theArray[Integer.parseInt(newElementVal)] = newElementVal;
		}
	}
	
	public boolean isPrime(int number) {
		if(number % 2 == 0)
			return false;
		
		for(int i=3; i*i<=number; i+=2) {
			if(number%i==0)
				return false;
		}
		return true;
	}
	
	public int getNextPrime(int minNumberToCheck) {
		for(int i= minNumberToCheck; true; i++) {
			if(isPrime(i))
				return i;
		}
	}
	
	public void increaseArraySize(int minArraySize) {
		int newArraySize = getNextPrime(minArraySize);
		moveOldArray(newArraySize);
	}
	
	public void moveOldArray(int newArraySize) {
		String[] cleanArray = removeEmptySpacesInArray(theArray);
		
		theArray = new String[newArraySize];
		
		arraySize = newArraySize;
		
		fillArrayWithNeg1();
		
		hashFunction2(cleanArray, theArray);
	}
	
	public String[] removeEmptySpacesInArray(String[] arrayToClean) {
		ArrayList<String> stringList = new ArrayList<String>();
		for(String theString : arrayToClean)
			if(!theString.equals("-1") && !theString.equals(""))
				stringList.add(theString);
		
		return stringList.toArray(new String[stringList.size()]);
	}
	
	public void fillArrayWithNeg1() {
		Arrays.fill(theArray, "-1");
	}
	
	public void hashFunction2(String[] stringsForArray, String[] theArray) {
		for(int n=0; n< stringsForArray.length; n++) {
			String newElementVal = stringsForArray[n];
			int arrayIndex = Integer.parseInt(newElementVal) % 29;
			System.out.println("Modulus Index= " + arrayIndex + " for value " + newElementVal);
			while(theArray[arrayIndex] != "-1") {
				++arrayIndex;
				System.out.println("Collision Try " + arrayIndex + " Instead");
				arrayIndex %= arraySize;
			}
			
			theArray[arrayIndex] = newElementVal;
		}
	}
	
	public String findKey(String key) {
		int arrayIndexHash = Integer.parseInt(key) % 29;
		while(theArray[arrayIndexHash] != "-1") {
			if(theArray[arrayIndexHash]==key) {
				System.out.println(key + " was Found in Index " + arrayIndexHash);
				return theArray[arrayIndexHash];	
			}
			++arrayIndexHash;
			arrayIndexHash %= arraySize;
		}
		return null;
	}
	
	public HashFunction(int size) {
		arraySize = size;
		theArray = new String[size];
		Arrays.fill(theArray, "-1");
	}
	
	public void displayTheStack() {
		int increment = 0;

		for (int m = 0; m < 3; m++) {

			increment += 10;

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				System.out.format("| %3s " + " ", n);

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

			for (int n = increment - 10; n < increment; n++) {

				if (theArray[n].equals("-1"))
					System.out.print("|      ");

				else
					System.out
							.print(String.format("| %3s " + " ", theArray[n]));

			}

			System.out.println("|");

			for (int n = 0; n < 71; n++)
				System.out.print("-");

			System.out.println();

		}
	}
	
}
