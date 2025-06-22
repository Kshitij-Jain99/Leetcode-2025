
import java.util.Arrays;

//322. Coin Change
// Approach-3: Bottom-Up DP(Tabulation)
// TC = O(n*amount), SC = O(amount)

public class C3 {
    public int coinChange(int[] coins, int amount){
        int[] dp = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0; //dp[i] = minimum number of coins needed to make amount i
        for(int i =1; i<= amount; i++){
            for(int j =0; j<coins.length; j++){
                if(i - coins[j] >= 0 && dp[i-coins[j]] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
                } 
            }
        }
        return (dp[amount] == Integer.MAX_VALUE) ? -1 : dp[amount];
    }
}
