//322. Coin Change
// Approach-2: Top-Down DP(Memoization)
// TC = O(n*amount), SC = O(amount)
/*
  1. Each state dp[amount] stores the minimum number of coins needed to make amount.
  2. O(amount) subproblems × O(n) per call 
 */
import java.util.Arrays;

public class C2 {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int ans = coinCount(coins, amount, dp);
        return (ans == Integer.MAX_VALUE) ? -1 : ans;
    }

    private int coinCount(int[] coins, int amount, int[] dp) {
        if (amount == 0) return 0;
        if (amount < 0) return Integer.MAX_VALUE;
        if (dp[amount] != -1) return dp[amount];

        int minCoins = Integer.MAX_VALUE;

        for (int coin : coins) {
            int res = coinCount(coins, amount - coin, dp);
            if (res != Integer.MAX_VALUE) {
                minCoins = Math.min(minCoins, res + 1);
            }
        }

        dp[amount] = minCoins;
        return dp[amount];
    }
}
