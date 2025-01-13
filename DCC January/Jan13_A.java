// 3223. Minimum Length of String After Operations
// Approach A: Freq Count
// TC = O(N), SC = O(1)
/*
 1. If freq = odd -> final freq = 1 
 2. If freq = even -> final freq = 2 
 3. When freq = 0/1/2  -> no change
 */

 public class Jan13_A {
    public int minLength(String s) {
        int[] freq = new int[26]; // to store frequencies of characters
        for (char c : s.toCharArray()) {
            freq[c - 'a']++; // increment frequency of the character
        }

        int length = 0;
        for (int count : freq) {
            if (count % 2 == 1) { 
                length += 1; // odd frequency contributes 1
            } else {
                length += Math.min(2, count); // even frequency contributes fully
            }
        }
        return length;
    }
}
