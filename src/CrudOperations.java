import java.util.Scanner;

public class CrudOperations {

	// create object of Scanner
	private static Scanner sc = new Scanner(System.in);

	public static void Initialized() {

		// ask user to Enter the size of array
		System.out.print("Enter size of the array: ");

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
		System.out.println();
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
			System.out.print(value + " ");
		} // end method

		System.out.print("\n");
	}// end method

	/*
	 * Method to insert an element in the array locates which index is vacated
	 * {deleted} insert the user preferred input there
	 */

	public static void Append(int[] array) {

		// store user input
		sc = new Scanner(System.in);

		boolean IsArrayFull = true;
		// iterate the array
		for (int index = 0; index < array.length; index++) {

			if (array[index] == -1) {
				System.out.print("\nEnter a number: ");
				array[index] = sc.nextInt();
				System.out.println("The new element has been added successfully! \n");
				IsArrayFull = false;
				break;
			} // end if
		} // end for loop

		// detect that array is full
		if (IsArrayFull) {
			System.out.println("{Array is full!}\n");
		} // end if

		DisplayArray(array);
	}// end method

	public static void Delete(int[] array) {

		// ask user to enter the index
		System.out.print("\nEnter the index of the element to be deleted: ");
		int indexToRemove = sc.nextInt();

		// if index out of bounds send error message
		if (indexToRemove > array.length || indexToRemove < 0) {
			System.out.println("{Index does not exist!}");
			Delete(array);

		} else {// if index is valid
			// if index value is vacant or empty print error message
			if (array[indexToRemove] == 0) {
				System.out.println("{There's no value to be deleted!} \n");

			} else {// if index value is not empty
				// make the index empty
				array[indexToRemove] = 0;
				// display message
				System.out.println("\nElement at index [" + indexToRemove + "] has been removed successfully!");
				System.out.println(CrudMenuOperations.GetNotice_Msg(2) + "\n");
			} // end if else
		} // end if else

	}// end method

	// splice here
	// ehe
	//

	// unshift here
	// ehe
	//

}// end class
