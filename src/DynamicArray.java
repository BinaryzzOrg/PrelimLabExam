
public class DynamicArray {
	// global integer array used for later
	private static int[] array;
	
	public static int[] GetArray() {
		return array;
	}// end method

	public static void SetArray(int[] array) {
		DynamicArray.array = array;
	}// end method
	
}// end class
