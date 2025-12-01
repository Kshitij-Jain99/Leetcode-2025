//Find the Majority Elements that occurs more than N/3 times
// Appraoch-2: Using Hashing(Map)
// TC = O(N*LogN), SC  = O(N)
import java.util.*;

public class D2 { //better and complete
    public static List<Integer> majorityElement(int[] v){
        int n = v.length;
        List<Integer> ls = new ArrayList<>();

        HashMap<Integer, Integer> mpp = new HashMap<>();
        for(int i =0; i<n; i++){
            mpp.put(v[i], mpp.getOrDefault(v[i], 0) +1);
        }
        for(Map.Entry<Integer, Integer> it : mpp.entrySet()){
            if(it.getValue() > (n/3)) ls.add(it.getKey());
        }
        return ls;

    }
}
/* //fast 
 int mini = (int)(n / 3) + 1;

    for (int i = 0; i < n; i++) {
        int value = mpp.getOrDefault(v[i], 0);
        mpp.put(v[i], value + 1);

        if (mpp.get(v[i]) == mini) {
            ls.add(v[i]);
        }
        if (ls.size() == 2) break;
    }
 */