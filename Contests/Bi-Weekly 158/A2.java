//3572. Maximize Y‑Sum by Picking a Triplet of Distinct X‑Values
// Approach-2: Filtering and Selecting
// TC = O(n + k.logk){k = unique elements in x}, SC = O(n{Map} + k{PQ})

/* Using HashMap + PriorityQueue{Max-Heap} +  GreedySelection
 1. HashMap to store the maximum y[i] value for each distinct x[i].
 2. Max-heap (PriorityQueue) to extract the top 3 largest values.
 3. Greedy Selection: The 3 largest y values from different x groups will always give the maximum sum
 */

import java.util.*;
public class A2 {
    public int maxSumDistinctTriplet(int[] x, int[] y){
        Map<Integer, Integer> map = new HashMap<>(); 

        for(int i = 0; i<x.length; i++){ //O(n)
            map.put(x[i], Math.max(map.getOrDefault(x[i], 0), y[i])); //Store the maximum y for each x
        }
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int val: map.values()) maxHeap.add(val);  //O(k.logk)
        
        if(maxHeap.size() < 3) return -1;
        int sum = 0;
        for(int i = 0; i<3; i++) sum += maxHeap.poll();  //O(logk) //Extract the top 3 largest y values
    
        return sum;
    }
   
}
