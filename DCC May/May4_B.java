//1128. Number of Equivalent Domino Pairs
// Approach B: Frequency Array with Normalized Key Mapping
// TC = O(N), SC = O(1)
/*
1. Each dominoes will have two values range from 1 to 9.
2. The values maybe in reverse order.
3. array{size 100-> maximum value of domino is (9,9) thus the maximum key value is 99.} to store the frequency of each dominoes' value.
4. use the following formula to convert the values of the dominoes into one key.
   key = a*10 + b
    a = minimum value of the domino
    b = maximum value of the domino 
5. store the count of the key in array and add key value
 */
public class May4_B {
    public int numEquivDominoPairs(int[][] dominoes){
      int[] map = new int[100];
      int count = 0;
      for(int[] nums: dominoes){
        int key = (Math.min(nums[0], nums[1])*10) + Math.max(nums[0], nums[1]);
        count += map[key]++;
      }
      return count;
    }
}
