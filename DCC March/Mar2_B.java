//2570. Merge Two 2D Arrays by Summing Values
// Appraoch 2: Sorting and Merging: Simple and in-place but requires sorting first.
// TC = O(N*LogN), SC = O(1) 

import java.util.*;

public class Mar2_B {
    public List<List<Integer>> mergeArrays(List<List<Integer>> nums1, List<List<Integer>> nums2){
        nums1.addAll(nums2); //Concatenates nums2 into nums1, making one large list in nums1, O(N)
        nums1.sort(Comparator.comparingInt(a -> a.get(0))); //Sorting ensures that elements with the same key are adjacent. O(NLogN)

        List<List<Integer>> result = new ArrayList<>();
        for(List<Integer> pair: nums1){ //o(N)
            //If the last added element in result has the same key (pair.get(0)): Add pair.get(1) (value) to the last element.
            if(!result.isEmpty() && result.get(result.size() - 1).get(0).equals(pair.get(0))){
                result.get(result.size() - 1).set(1, result.get(result.size() - 1).get(1) + pair.get(1));
            }
            else{
                result.add(new ArrayList<>(pair));
            }
        }
        return result;
    }
}
