// Longest Consecutive Sequence in an Array
// Approach-2: Using sorting
// TC = O(N.logN + N), SC = O(1)

/*
 1. Here we are distorting the array
 */
import java.util.*;

public class C2 {
    public static int longestSuccessiveElements(int[] a){
        int n = a.length;
        if(n == 0) return 0;

        Arrays.sort(a);
        int lastSmaller = Integer.MIN_VALUE; 
        int cnt = 0; // length of the current sequence
        int longest = 1;

        for(int i = 0; i<n; i++){
            if(a[i] - 1 == lastSmaller){
                cnt += 1;
                lastSmaller = a[i];
            } else if(a[i] != lastSmaller){
                cnt = 1;
                lastSmaller = a[i];
            }
             longest = Math.max(longest, cnt);
        }
        return longest;
    }
}
