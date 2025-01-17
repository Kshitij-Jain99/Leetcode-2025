//2683. Neighboring Bitwise XOR
// Approach A: 
// TC = O(n), SC = O(1)
/*
 1. Here, the validity depends on whether the final value of the bit matches the last element of the derived array.
2. By the end of the loop, the value of bit represents the expected relationship of the original array elements based on the derived array.
3. tracks the state of the original array relationships. 
*/
public class Jan17_B {
    public boolean doesValidArrayExist(int[] derived) {
        int bit = 0; // It acts as a state tracker, toggle between 0 and 1 based on ele of derived array
        for (int i = 1; i < derived.length; i++) {
            if (derived[i - 1] == 1) {
                bit = (bit + 1) % 2; // Toggle the bit if the previous element is 1
            } //This toggling reflects the XOR relationship implied by the derived array.
        }
        if ((derived[derived.length - 1] == 0 && bit != 0) || (derived[derived.length - 1] == 1 && bit != 1)) {
            return false;
        }
        return true;
    }
}
