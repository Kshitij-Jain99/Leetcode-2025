//3577. Count the Number of Computer Unlocking Permutations
//Approach-2: Greedy validation + math
// TC = O(), SC = O()
/*
 1. To have a valid permutation:
   -The first element must be the smallest.
   -This smallest value must occur only once in the array.
 2. Once validated, the rest of the n - 1 elements can appear in any order. So, total valid permutations = (n - 1)!
 */
public class B2 {
    public int countPermutations(int[] nums) {
        int MOD = (int)1e9 + 7;
        int n = nums.length;
        int mini = Integer.MAX_VALUE;
        for(int num: nums) mini = Math.min(mini, num);

        if(nums[0] != mini) return 0;

        int cnt = 0;
        for(int num: nums) {
            if(num == mini) cnt++;
            if(cnt > 1) return 0;
        }

        long ans = 1;
        for(int i =1; i<n; i++) ans = (ans*i) %MOD;

        return (int) ans;
    }
}
