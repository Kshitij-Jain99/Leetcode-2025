//2570. Merge Two 2D Arrays by Summing Values
// Appraoch 3: Balanced BST
// TC = O(NlogN + MlogM) = O(NLogN), SC = O(N) 
/*
 1. TreeMap (Java) to keep elements sorted automatically.
 */

 import java.util.*;

 class Mar2_C{
     public List<List<Integer>> mergeArrays(List<List<Integer>> nums1, List<List<Integer>> nums2) {
         TreeMap<Integer, Integer> map = new TreeMap<>();
 
         for (List<Integer> pair : nums1) {
             map.put(pair.get(0), map.getOrDefault(pair.get(0), 0) + pair.get(1));
         }
         for (List<Integer> pair : nums2) {
             map.put(pair.get(0), map.getOrDefault(pair.get(0), 0) + pair.get(1));
         }
 
         List<List<Integer>> result = new ArrayList<>();
         for (var entry : map.entrySet()) {
             result.add(Arrays.asList(entry.getKey(), entry.getValue()));
         }
         return result;
     }
 }