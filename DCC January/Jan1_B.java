//1422. Maximum Score After Splitting a String
// Approach B: Optimized
//TC= O(N+N) = O(N) , SC = O(1)

public class Jan1_B {
    public int maxScore(String s){
        int n = s.length();
        int oneCount = 0;
        //Pre-processing stage
        for(int i = 0; i< n; i++){
            if(s.charAt(i) == '1'){
                oneCount++;
            }
        }

        int zeroCount = 0;
        int maxScore = 0;
        //i value refer to the split point of left and right
        for(int i = 0; i < n - 1; i++){ //not upto n as non-empty string split required
            if(s.charAt(i) == '1'){
                oneCount--;
            } else{
                zeroCount++;
            }
            maxScore = Math.max(maxScore, zeroCount + oneCount);
        }
        return maxScore;
    }
}
