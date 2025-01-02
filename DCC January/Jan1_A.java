//1422. Maximum Score After Splitting a String..
// Approach A: Brute Force
//TC= O(N*(N-1)) = O(N^2) , SC =O(1)
/*
 At every point we count prev number of zeroes(towards left)
 Count number of ones to right
 Store ans and Maximize it
 */
public class Jan1_A {
    public int maxScore(String s){
        int score = 0;
        int n = s.length();
        int zero = 0;

        for(int i = 0 ;i < n; ++i){
            if(s.charAt(i) == '0'){
                zero++;
            }
            int one = 0;
            for(int j=i+1; j<n ; j++){
                if(s.charAt(j) == '1'){
                    one++;
                }
            }
            score = Math.max(score, one + zero);
        }
        return score;
    }
}
