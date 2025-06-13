//Length of the longest subarray with zero Sum
// Approach-2: PrefixSum + HashMap
// TC = O(N), SC = O(N)
import java.util.*;

public class D2 {
    int maxLen(int[] A, int n){
        //(prefixSum encountered so far,first index where this prefix sum occurred.)
        HashMap<Integer, Integer> mpp = new HashMap<Integer, Integer>();
        int max = 0;
        int sum = 0;

        for(int i = 0; i<n; i++){
            sum += A[i];
            if(sum == 0) max = i+1;
            else{
                 //sum repeats
                if(mpp.get(sum) != null) max = Math.max(max, i-mpp.get(sum));
                //new sum -> add
                else mpp.put(sum, i);
            } 
        }
        return max;
    }
}
