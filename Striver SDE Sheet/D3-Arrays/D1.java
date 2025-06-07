//Find the Majority Elements that occurs more than N/3 times
// Appraoch-1: Brute Fore
// TC = O(N*N), SC  = O(1)
import java.util.*;

public class D1 {
    public static List<Integer> majorityElement(int[] v){
        int n = v.length;
        List<Integer> ls = new ArrayList<>(); //list of answers

        for(int i = 0; i < n; i++){
//selected element is v[i]: Checking if v[i] is not already a part of the answer
            if(ls.size() == 0 || ls.get(0) != v[i]){ //prevents counting the same element multiple times
            int cnt = 0;
            for(int j = 0; j<n; j++){
                if(v[i] == v[j]) cnt++;
            }
            if(cnt > (n/3)) ls.add(v[i]);
          
        }
          if(ls.size() == 2) break; //there can be at most 2 elements that appear more than N/3 times.
    
    } 
        return ls;
}
}
