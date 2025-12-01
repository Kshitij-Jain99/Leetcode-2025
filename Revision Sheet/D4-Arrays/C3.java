// Longest Consecutive Sequence in an Array
// Approach-3{Best}: Using HashSet
// TC = O(N+2N), SC = O(N)
/*
 1.  The time complexity is computed under the assumption that we are using unordered_set and it is taking O(1) for the set operations. 
 2.  If we consider the worst case the set operations will take O(N) in that case and the total time complexity will be approximately O(N2). 
 3. And if we use the set instead of unordered_set, the time complexity for the set operations will be O(logN) and the total time complexity will be O(NlogN).
 */
import java.util.*;

public class C3 {
    public static int longestSuccessiveElements(int[] a){
        int n = a.length;
        if( n == 0) return 0;

        int longest = 1;
        Set<Integer> set = new HashSet<>();

        //put all the array elements into set
        for(int i =0; i<n; i++){
            set.add(a[i]);
        }

        //find the largets sequence
        for(int it: set){
            if(!set.contains(it-1)){
                int cnt =1;
                int x = it;
                while(set.contains(x+1)){
                    x = x+1;
                    cnt = cnt +1;
                }
                longest = Math.max(longest, cnt);
            }
        }
        return longest;
    }
}
