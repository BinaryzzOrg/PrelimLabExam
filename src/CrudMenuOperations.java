import java.util.Scanner;

public class CrudMenuOperations {

//	@formatter:off
	final private static String[] NOTICE_MSG = { "Choose if the user hasn't initialized an array",
												"[1]INITIALIZED (not recommended)",
												"(It will only leave an \"X\" sign when you view it)",
												"Shifting remaining elements in left side...", "{Nothing to Compress here!}" };

	final private static String PROGRAM_TITLE = "Welcome to Java Array Operations";
	final private static String[] PRINT_OPERATIONS = { "INITIALIZE", "DISPLAY", "APPEND", "DELETE",
													"SPLICE", "UNSHIFT", "EXIT" };
	
	// boolean flags
	private static boolean repeatProcess = false;
	private static boolean disableOneChoice = false;
//	@formatter:on

	public static String GetNotice_Msg(int index) {
		return NOTICE_MSG[index];
	}// end method

	/*
	 * Method to PRINT the introductory of the program has a parameter of an String
	 * array and a boolean ask the user to input the size validates if user input
	 * really passes the 1-6 choices range handles exception if user input a
	 * non-match data type
	 */

	public static void MenuDisplay(String[] blueprint, boolean disableOneChoice) {

		System.out.println("\tOPERATIONS");
//		@formatter:off
		for (int i = 0; i < blueprint.length; i++) {
			if (i == 0 && !disableOneChoice) {
				System.out.println("\t=>" + "[" + (i + 1) + "]" 
						+ blueprint[i] + " (" + GetNotice_Msg(i) + ")");
				// if true disables the first operation
			} else if (i == 0 && disableOneChoice) {
				System.out.println("\t=>" + GetNotice_Msg(i + 1));
			} else {
				System.out.println("\t=>" + "[" + (i + 1) + "]" + blueprint[i]);
			} // end if else
		} // end for
//		@formatter:on

		System.out.print("Please Choose An Operation: ");
		Operation_Type();
	}// end method

	/*
	 * Method to properly make a condition between operations has a parameter of an
	 * integer for checking the input of user uses the method Initialized_First upon
	 * checking if array is not initialized first
	 */

	public static void Operation_Type() {

		Scanner sc = new Scanner(System.in);

		boolean inputValid = false;
		while (!inputValid) {
			String user_Choice = sc.nextLine();
			switch (user_Choice) {
			case "1":
				// Initialize
				if (disableOneChoice) {
					// do something else
				} else {
					CrudOperations.Initialized();
					disableOneChoice = true;
				}
				MenuDisplay(PRINT_OPERATIONS, inputValid);
				inputValid = true;
				break;
			case "2":
				// Display
				OperationErrorMsg(2);
				break;
			case "3":
				// Append
				OperationErrorMsg(3);
				break;
			case "4":
				// Delete
				OperationErrorMsg(4);
				break;
			case "5":
				// Splice
				break;
			case "6":
				// Unshift
				break;
			case "7":
				System.out.println("Exiting Program...");
				System.exit(0);
				break;
			case "":
				// call it self again
				Operation_Type();
			default:
				System.out.println("{Please type from 1-7 only!}\n");
				MenuDisplay(PRINT_OPERATIONS, disableOneChoice);
				break;
			}// end switch
		} // end while
	}// end method

	/*
	 * Method to get the user input and use a specific algorithm assigned to each
	 * input choice
	 */

	public static boolean OperationErrorMsg(int choice) {

		String errorMsg = "{Initialized First!}\n";
		if (disableOneChoice && choice == 2) {
			CrudOperations.DisplayArray(DynamicArray.GetArray());
		} else if (disableOneChoice && choice == 3) {
			CrudOperations.Append(DynamicArray.GetArray());
		} else if (disableOneChoice && choice == 4) {
			CrudOperations.Delete(DynamicArray.GetArray());
		} else if (disableOneChoice && choice == 5) {
			// splice here
		} else if (disableOneChoice && choice == 5) {
			// unshift
		} else {
			System.out.println(errorMsg);
		} // end if else

		MenuDisplay(PRINT_OPERATIONS, disableOneChoice);
		return true;
	}// end method

	// constructor
	public CrudMenuOperations() {
		System.out.println("**********\"" + PROGRAM_TITLE + "\"**********" + ""
				+ "\n******************************************************");

		// enable looping
		do {
			System.out.println();
			MenuDisplay(PRINT_OPERATIONS, disableOneChoice);
			// continue looping until boolean flag becomes true
		} while (!repeatProcess);
	}// end method
}// end class
