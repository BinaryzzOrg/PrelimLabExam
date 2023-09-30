import java.util.Scanner;

public class CrudOperations {

	// create object of Scanner
	private static Scanner sc = new Scanner(System.in);
	//first index that has no element
	private static int index;
	//boolean for checking if array is full or not
	private static boolean arrayIsFull;

	public static void Initialized() {

		// ask user to Enter the size of array
		System.out.print("\nEnter size of the array: ");

		int size = sc.nextInt();

		// Create an array of the specified size
		int[] tempArray = new int[size];

		// Loop to iterate over each index of the array
		for (int index = 0; index < size; index++) {
			System.out.print("Enter element at index " + index + ": ");
			// Read an element and store it at the current index
			tempArray[index] = sc.nextInt();
		} // end for

		DynamicArray.SetArray(tempArray);
		DisplayArray(tempArray);
	}// end method

	/*
	 * Method to display the current state of elements in the array has a parameter
	 * of an integer array prints an "X" format when an index is vacated
	 */

	public static void DisplayArray(int[] array) {

		// print the elements label
		System.out.print("\nThe current elements of the array are: ");
		// print the elements
		for (int value : array) {
//			if (value !=  0) {
				System.out.print(value + " ");
//			}
		} // end method

		System.out.println("\n");
	}// end method

	/*
	 * Method to insert an element in the array locates which index is vacated
	 * {deleted} insert the user preferred input there
	 */

	public static void Append(int[] array) {

		arrayChecker(array);
		
		if (arrayIsFull) {
			DoubleArrayCapacity(array);
			array = DynamicArray.GetArray();
		}
		arrayChecker(array);
		
		// store user input
		sc = new Scanner(System.in);
		System.out.print("\nEnter the element you want to append: ");
		int userInput = sc.nextInt();
		array[index] = userInput;
		
		System.out.println("Element added successfully!");
		DisplayArray(array);
	}// end method

	public static void Delete(int[] array) {

		// ask user to enter the index
		System.out.print("\nEnter the index of the element to be deleted: ");
		int indexToRemove = sc.nextInt();

		// if index out of bounds send error message
		if (indexToRemove > array.length - 1 || indexToRemove < 0) {
			System.out.println("{Index does not exist!}");
			Delete(array);

		} else {// if index is valid
			// if index value is vacant or empty print error message
			if (array[indexToRemove] == 0) {
				System.out.println("{There's no value to be deleted!} \n");

			} else {// if index value is not empty
				// make the index empty
				array[indexToRemove] = 0;
				moveElementsToLeft(indexToRemove, array);
				// display message
				System.out.println("Element at index [" + indexToRemove + "] successfully deleted!\n");
			} // end if else
		} // end if else

	}// end method

	// splice here
	// ehe
	//

	public static void Unshift(int[] array) {
		
		arrayChecker(array);
		
		if (arrayIsFull) {
			DoubleArrayCapacity(array);
			array = DynamicArray.GetArray();
		}
		
		arrayChecker(array);
		moveElementsToRight(index, array);
		
		// store user input
		sc = new Scanner(System.in);
		System.out.print("\nEnter the element you want to unshift: ");
		int userInput = sc.nextInt();
		
		array[0] = userInput;
		DynamicArray.SetArray(array);
		
		System.out.println("Element added successfully!");
		DisplayArray(array);
	}
	

	
	public static void arrayChecker(int[] array) {
		arrayIsFull = true;
		for (int i = 0; i < array.length; i++) {
			if (array[i] == 0) {
				index = i;
				arrayIsFull = false;
				break;
			}
		}
	}
	
	public static void DoubleArrayCapacity(int[] array) {
		int[] tempArray = new int[array.length*2];
		for (int i = 0; i < array.length; i++) {
			tempArray[i] = array[i];
		}
		DynamicArray.SetArray(tempArray);
		arrayIsFull = false;
	}
	
	public static void moveElementsToLeft(int index, int[] array) {
		for (int i = index; i < array.length-1; i++) {
			int temp = array[i];
			array[i] = array[i+1];
			array[i+1] = temp;
		}
		
		DynamicArray.SetArray(array);
	}
	
	public static void moveElementsToRight(int index, int[] array) {
		for (int i = index; i > 0; i--) {
			int temp = array[i];
			array[i] = array[i-1];
			array[i-1] = temp;
		}
		
		DynamicArray.SetArray(array);
	}
	
	
}// end class
