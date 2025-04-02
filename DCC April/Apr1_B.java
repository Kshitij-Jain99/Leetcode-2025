//2140. Solving Questions With Brainpower
// Appraoch-B: Bottom-Up DP(Iterative) 
// TC = O(N), SC = O(N) where N is the number of questions
//More optimized for large inputs(No recursion overhead, Easily space optimizable)

public class Apr1_B {
    public long mostPoints(int[][] questions){
        long[] dp = new long[questions.length];

        for(int i = questions.length - 1; i>= 0; i--){//wprking backwards
            int index = i + questions[i][1] + 1;  //i+x+1
            if(index < questions.length){
                dp[i] = dp[index] + questions[i][0];
            }  else{
                dp[i] = questions[i][0];
            }
            if(i<questions.length - 1){
                dp[i] = Math.max(dp[i+1], dp[i]);
            }
        }
        return dp[0];
    }
}
