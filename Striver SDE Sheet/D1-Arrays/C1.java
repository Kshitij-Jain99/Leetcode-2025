//31. Next Permutation
// Appraoch-1 : Optimal
// TC = O((3N) = 3*O(N) approx., SC = O(1)
import java.util.*;

public class C1 {
    public static List< Integer > nextGreaterPermutation(List< Integer > A){
        int n = A.size();
        int ind = -1; //break point
        for(int i = n-2; i>= 0; i--){
            if(A.get(i) <A.get(i+1)){
                ind = i;
                break;
            }
        }
        // break point not found, reverse the array
        if(ind == -1){
            Collections.reverse(A);
            return A;
        }
        // find the next greater element to the right of the break point
        // and swap it with the break point
        for(int i = n-1; i>ind; i--){
            if(A.get(i) > A.get(ind)){
                int tmp = A.get(i);
                A.set(i, A.get(ind));
                A.set(ind, tmp);
                break;
            }
        }
        // reverse the right half
        List<Integer> sublist = A.subList(ind+1, n);
        Collections.reverse(sublist);
        return A;
    }

    public static void main(String args[]) {
        List<Integer> A = Arrays.asList(new Integer[] {2, 1, 5, 4, 3, 0, 0});
        List<Integer> ans = nextGreaterPermutation(A);

        System.out.print("The next permutation is: [");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
        }
        System.out.println("]");

    }
}
/*
 class Solution {
    public void nextPermutation(int[] nums) {
        int n = nums.length;

        //1: Longer prefix match->Finding break point
        int idx = -1; //break point
        for(int i = n-2; i>=0; i--){
            if(nums[i] < nums[i+1]){
                idx = i;
                break;
            }
        }

       //2: If break point dne -> we are at last permutation
       if(idx == -1){
        reverseSubarray(nums,0,n-1);
        return;
       }

       //3: Find NGE and swap it
       for(int i = n-1; i>idx; i--){
        if(nums[i] > nums[idx]){
            int tmp = nums[i];
            nums[i] = nums[idx];
            nums[idx] = tmp;
            break;
        }
       }

       //4: Reverse the rem. half
       reverseSubarray(nums, idx+1, n-1);

    }
      public static void reverseSubarray(int[] nums, int start, int end) {
        while (start < end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
        start++;
        end--;
    }
  }
}
 */