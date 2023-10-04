import java.util.Scanner;

public class CrudOperations {

	// create object of Scanner
	private static Scanner sc = new Scanner(System.in);
	// first index that has no element
	private static int size;

	public static void Initialized() {

		// ask user to Enter the size of array
		System.out.print("\nEnter size of the array: ");

		size = sc.nextInt();

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
		// print the elements loop through size
		for (int i = 0; i < size; i++) {
			// printing values
			System.out.print("[" + array[i] + "]");
		}
		System.out.println("\n");
	}// end method

	/*
	 * Method to append an element in the array locates which index is vacated
	 * {deleted} insert the user preferred input there
	 */

	public static void Append(int[] array) {

		if (arrayChecker(array)) {
			DoubleArrayCapacity(array);
			System.out.println(array.length);
			System.out.println(size);
			Append(DynamicArray.GetArray());
			return;
		}

		// store user input
		sc = new Scanner(System.in);
		System.out.print("\nEnter the element you want to append: ");
		array[size] = sc.nextInt();

		size++;
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
			if (indexToRemove > size - 1) {
				System.out.println("{There's no value to be deleted!} \n");

			} else {// if index value is not empty
				moveElementsToLeft(indexToRemove, array);
				size--;
				// display message
				System.out.println("Element at index [" + indexToRemove + "] successfully deleted!\n");
			} // end if else
		} // end if else

	}// end method

	public static void Unshift(int[] array) {

		if (arrayChecker(array)) {
			DoubleArrayCapacity(array);
			Unshift(DynamicArray.GetArray());
			return;
		}
		moveElementsToRight(size, array);

		// store user input
		sc = new Scanner(System.in);
		System.out.print("\nEnter the element you want to unshift: ");
		array[0] = sc.nextInt();

		DynamicArray.SetArray(array);
		System.out.println("Element added successfully!");
		size++;
		DisplayArray(array);
	}

	public static boolean arrayChecker(int[] array) {
		if (size != array.length) {
			return false;
		}
		return true;
	}

	public static void DoubleArrayCapacity(int[] array) {
		int[] tempArray = new int[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			tempArray[i] = array[i];
		}
		DynamicArray.SetArray(tempArray);
	}

	public static void moveElementsToLeft(int index, int[] array) {
		for (int i = index; i < array.length - 1; i++) {
			int temp = array[i];
			array[i] = array[i + 1];
			array[i + 1] = temp;
		}

		DynamicArray.SetArray(array);
	}

	public static void moveElementsToRight(int index, int[] array) {
		for (int i = index; i > 0; i--) {
			int temp = array[i];
			array[i] = array[i - 1];
			array[i - 1] = temp;
		}

		DynamicArray.SetArray(array);
	}

	public static void splice_Operation(int[] array) {
		// prompt and store N of elements to be remove
		System.out.print("\nNumber of elements to removed: ");
		int numOfElements = sc.nextInt();
		// prompt and store the starting index for deletion
		System.out.print("Start to delete from what index? : ");
		int starting_Index = sc.nextInt();
		// return if index not exist
		if (starting_Index > array.length - 1 || starting_Index < 0) {
			System.out.println("{Index does not exist!}");
			return;
		}
		// avoid out of bounds if N of elements is larger than array
		numOfElements = goBackToProperSize(numOfElements, starting_Index);
		// overwrite elem that removed, shift to left side
		splice_Process(array, numOfElements, starting_Index);
		// update the size based on the N of elements to be removed
		size -= numOfElements;
		
		System.out.println("Succesfully deleted n of elements!\n");
	}

	public static int goBackToProperSize(int numOfElements, int starting_Index) {
		if (numOfElements > size - starting_Index) {
			numOfElements = size - starting_Index;
		}
		return numOfElements;
	}

	/*
	 * Method to start the splicing process
	 */
	public static void splice_Process(int[] array, int numOfElements, int starting_Index) {
		// loop through the number of times it needed to shift
		int timesIfNeededToShift = size - numOfElements;
		System.out.println();
		while (starting_Index < timesIfNeededToShift) {
			// shift it through the index that is not affected or not in range of deletion
			array[starting_Index] = array[starting_Index + numOfElements];
			// increment to further shift
			starting_Index++;

		}
	}

}// end class
