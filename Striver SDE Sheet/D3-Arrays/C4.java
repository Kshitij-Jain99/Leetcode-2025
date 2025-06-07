//Find the Majority Element that occurs more than N/2 times
// Appraoch-4(Less Efficient): Sorting and checking middle
// TC = O(N*LogN), SC  = O(1)
import java.util.*;
/*
 1. first sorting the array and then picking the middle element.
 2.  if an element appears more than n/2 times, it must occupy the middle index once the array is sorted.
 */
public class C4 {
      public static int majorityElement(int[] v){
        Arrays.sort(v);
        int candidate = v[v.length/2];

        //optional verification step
        int count = 0;
        for(int num:v){
            if(num == candidate) count++;
        }
        if(count > v.length/2) return candidate;
        else return -1;

      }
}
