//2140. Solving Questions With Brainpower
// Appraoch-D: Iterative Bottom-Up DP
// TC = O(N), SC = O(1) where N is the number of questions
public class Apr1_D {
    public long mostPoints(int[][] questions) {
        int n = questions.length;
        if(n == 1) return questions[0][0];
        long[] dp = new long[200001];
        for(int i=n-1; i>=0; i--){
            dp[i] = Math.max(questions[i][0]+dp[i + questions[i][1] + 1] , dp[i+1]);
        }
        return dp[0];
    }
}
