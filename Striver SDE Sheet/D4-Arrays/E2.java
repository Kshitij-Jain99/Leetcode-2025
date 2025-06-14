//Count the number of subarrays with given xor K
// Approach-2: Using Hashing
// TC = O(N) or O(N.LogN), SC = O(N)
import java.util.*;
/*
   x ^ k = xr{total subarray(0,i)}
   x -> starting subarray part of xr, k -> ending subarray part of xr
 */
public class E2 {
    public static int subarrayWithXorK(int[] a, int k){
        int n = a.length;
        int xr = 0;
        Map<Integer, Integer> mpp = new HashMap<>();
        mpp.put(xr, 1); //setting the value of 0 as first insertion
        int cnt = 0;

        for(int i = 0; i<n; i++){
            xr = xr ^ a[i];    //prefixXor
            int x = xr ^ k;    //frontXor
            if(mpp.containsKey(x)) cnt += mpp.get(x);  //x exist -> xor of K subarray exist
            
            // insert xr
            // xr is x^k, so xr initally is x now
            if(mpp.containsKey(xr)) mpp.put(xr, mpp.get(xr) + 1);
            else{
                mpp.put(xr,1);
            }
        }
        return cnt;
    }
}
