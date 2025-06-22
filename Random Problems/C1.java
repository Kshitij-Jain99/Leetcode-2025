//322. Coin Change
// Approach-1: Brute Force(Recursion){TLE}
// TC = O(n^amount), SC = O(amount)
/*
 1. Trying all coins combinations.
 2. For each coin, recursively subtract its value from the amount and solve the smaller subproblem.
 3. Track the minimum number of coins needed.
 */
public class C1 {
    public int coinChange(int[] coins, int amount){
        if(amount == 0) return 0;
        int result = helper(coins, amount);
        return result == Integer.MAX_VALUE ? -1: result;
    }

    private int helper(int[] coins, int amount){
        if(amount <0) return -1;
        if(amount == 0) return 0;

        int min = Integer.MAX_VALUE;

        for(int coin : coins){
            int res = helper(coins, amount - coin);
            if(res >= 0 && res < min) min = res + 1;
        }
        return (min == Integer.MAX_VALUE) ? -1 : min;
    }
}
