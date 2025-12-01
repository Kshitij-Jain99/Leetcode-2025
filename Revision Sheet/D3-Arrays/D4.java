//Find the Majority Elements that occurs more than N/3 times
// Appraoch-4: Sorting + Counting
// TC = O(N*LogN + N), SC  = O(1)

import java.util.*;

public class D4 {
    public static List<Integer> majorityElement(int[] v){
        Arrays.sort(v);
        List<Integer> res = new ArrayList<>();
        int n = v.length;
        
        for(int i =0; i<n; i++){
            int count = 1;
            while(i+count <n && v[i+count] == v[i]) count++;
            if(count > n/3) res.add(v[i]);
            i += count; //Move the pointer i forward by count to skip over the duplicates we just counted.
        }
        return res;
    }
}
