//Find the Majority Element that occurs more than N/2 times
// Appraoch-2: Using HashMap
// TC = O(nLogN{N ele. insertion in map -> N*N} + n), SC  = O(n)
import java.util.*;
// If Ordered Map is used TC{worst} -> O(NLogN + N) //TreeMap<Integer, Integer> map = new TreeMap<>();
public class C2 {
    public static int majorityElement(int[] v){
        int n = v.length;

        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i<n; i++){
           map.put(v[i], map.getOrDefault(v[i], 0) + 1);
        }

        for(Map.Entry<Integer, Integer> it: map.entrySet()){
            if(it.getValue() > (n/2)) return it.getKey();
        }
        return -1;
    }
}
