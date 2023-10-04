import java.util.Scanner;

public class CrudOperations {

	// create object of Scanner
	private static Scanner sc = new Scanner(System.in);
	//size of elements
	private static int size;

	/*
	 * Method to start initializing elements
	 * ask for size and stores if through an temp array and passed it to the original array
	 */

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
		//if full, invoke double capacity of array method
		if (arrayChecker(array)) {
			DoubleArrayCapacity(array);
			//invoke append method
			Append(DynamicArray.GetArray());
			//go back after appending
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

	/*
	 * Method to start deletion of an element in the array
	 * takes the index user preferred to delete
	 * decrease size to start deletion
	 */

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

	/*
	 * Method to perform splice operation
	 * Takes N of Elements and starting index of deletion
	 * removes the element based on the starting index and the number of times it go through deleting {through right side}
	 */

	public static void splice_Operation(int[] array) {
		// prompt and store N of elements to be remove
		System.out.print("\nNumber of elements to removed: ");
		int numOfElements = sc.nextInt();
		// prompt and store the starting index for deletion
		System.out.print("Start to delete from what index? : ");
		int starting_Index = sc.nextInt();
		// return if index does not exist
		if (starting_Index > array.length - 1 || starting_Index < 0) {
			System.out.println("{Index does not exist!}");
			return;
		}

		System.out.println("Started deleting  "+ numOfElements + " of elements at starting index of "+ starting_Index + " \n");

		// avoid out of bounds if N of elements is larger than array
		numOfElements = goBackToProperSize(numOfElements, starting_Index);
		// overwrite elem that removed, shift to left side
		splice_Process(array, numOfElements, starting_Index);
		// update the size based on the N of elements to be removed
		size -= numOfElements;

		System.out.println("Successfully deleted  "+ numOfElements + " of elements at starting index of "+ starting_Index + " \n");
	}
	/*
	 * Method to start the splicing process
	 * takes a parameter of array, numOfElements, starting index
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

	/*
	 * method to check if N of Elements reached the total size and makes it go back to proper size
	 * takes a parameter of num of elements and the starting index
	 */

		public static int goBackToProperSize(int numOfElements, int starting_Index) {
			return (numOfElements > size - starting_Index) ? numOfElements = size - starting_Index: numOfElements;
		}
	/*
	 * Checks if size reached the array length
	 * return true if reached
	 * returns false if not
	 */


	public static void Unshift(int[] array) {

		if (arrayChecker(array)) {
			DoubleArrayCapacity(array);
			Unshift(DynamicArray.GetArray());
			return;
		}
		//shift first through right in order to have a vacant at the last index
		moveElementsToRight(size, array);

		// prompts and store user input to first index
		System.out.print("\nEnter the element you want to unshift: ");
		array[0] = sc.nextInt();
		//
		DynamicArray.SetArray(array);
		//indicates addition of array
		System.out.println("Element added successfully!");
		//increase size {indicates added elem through the first index}
		size++;
		//invoke display method
		DisplayArray(array);
	}

	/*
	 * Checks if size reached the array length
	 * return true if reached
	 * returns false if not
	 */

	public static boolean arrayChecker(int[] array) {
		if (size != array.length) {
			return false;
		}
		return true;
	}

	/*
	 * Make the array capacity to much larger {2 times}
	 * make the whole process dynamic
	 */

	public static void DoubleArrayCapacity(int[] array) {
		int[] tempArray = new int[array.length * 2];
		for (int i = 0; i < array.length; i++) {
			tempArray[i] = array[i];
		}
		DynamicArray.SetArray(tempArray);
	}

	/*
	 * Shift elements through left
	 * Used in Delete later
	 */


	public static void moveElementsToLeft(int index, int[] array) {
		/*runs a loop through the last index and shifts elements through the left side of the array
		 * indicates that their are deletion
		 */
		for (int i = index; i < array.length - 1; i++) {
			int temp = array[i];
			array[i] = array[i + 1];
			array[i + 1] = temp;
		}
		//invoke display method
		DynamicArray.SetArray(array);
	}

	/*
	 * Shift elements through right
	 * Used in Unshift later
	 */
	public static void moveElementsToRight(int index, int[] array) {
		/*runs a loop through the last index and shifts elements through the left side of the array
		 * indicates that their are addition of element through first index
		 */
		for (int i = index; i > 0; i--) {
			int temp = array[i];
			array[i] = array[i - 1];
			array[i - 1] = temp;
		}

		DynamicArray.SetArray(array);
	}
//end of class
}