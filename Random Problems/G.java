//5. Longest Palindromic Substring
//solution: https://leetcode.com/problems/longest-palindromic-substring/solutions/3598120/longest-palindromic-substring/
import java.util.*;

public class G{
    //Appraoch-1: Brute Force:TLE
    //TC = O(N^3), SC = O(N)
    public String longestPalindrome1(String s) {
        int n = s.length();
        String longest = "";
        
        for(int i =0; i<n; i++){
           for(int j = i+1; j<=n; j++){
            String substr = s.substring(i,j);
            if(isPalindrome(substr) && substr.length() > longest.length()){
                longest = substr;
            }
           }
        }
        return longest;
   }
   private boolean isPalindrome(String str){
       int low = 0, high = str.length()-1;
       while(low < high){
        if(str.charAt(low++) != str.charAt(high--)) return false;
       }
       return true;
   } 

   //Appraoch-2:Top Down DP(memoization) 
    //TC = O(n^2), SC = O(n^2)
     private int[][] dp = new int[1001][1001];
    
     public String longestPalindrome2(String s) {
        int n = s.length();
        
        int maxLen = Integer.MIN_VALUE;
        int startingIndex = 0;
        
        // Initialize dp array with -1
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        for (int i = 0; i < n; i++) {  //O(N^2)
            for (int j = i; j < n; j++) {
                if (solve(s, i, j)) {   //already computed values does not increase TC
                    if (j - i + 1 > maxLen) {
                        startingIndex = i;
                        maxLen = j - i + 1;
                    }
                }
            }
        }
        
        return s.substring(startingIndex, startingIndex + maxLen);
    }
    
    private boolean solve(String s, int l, int r) {
        if (l >= r)   //odd and even case both included
            return true;
        
        if (dp[l][r] != -1)  //dp states 
            return dp[l][r] == 1;
        
        if (s.charAt(l) == s.charAt(r)) {
            boolean result = solve(s, l + 1, r - 1); //try for smaller substring
            dp[l][r] = result ? 1 : 0;
            return result;
        }
        
        dp[l][r] = 0;
        return false;
    }

    //Appraoch-3:Bottom Up DP 
    //TC = O(n^2), SC = O(n^2)
        public String longestPalindrome3(String s) {
        int n = s.length();
        if (n <= 1) return s;
        
        int maxLen = 1;
        int startingIndex = 0;
        int end = 0;
        
        boolean[][] dp = new boolean[n][n];
        
        // Fill for all substrings
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;  // Single characters are palindromes
            for (int j = 0; j < i; j++) {
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (i - j + 1 > maxLen) {
                        maxLen = i - j + 1;
                        startingIndex = j;
                        end = i;
                    }
                }
            }
        }
        
        return s.substring(startingIndex, end + 1);
    }

    //Appraoch-4: Two Pointers
    // TC = O(N^2), SC = O(1)
    /*
     1. Treat every idx i as a center:
      a. Once as a single character center(i,i) -> odd length palindromes
      b. Once as pair center(i, i+1) -> even length palindromes
     2. Expand outwards while charcters match
     3. Update the start and end indexes when a loner palindrome is found.

     */
    public String longestPalindrome4(String s) {
        int n = s.length();
        if (n <= 1) return s;

        String maxStr = s.substring(0, 1);

        for (int i = 0; i < n - 1; i++) {
            String odd = expandFromCenter(s, i, i);  //l and r are at i
            String even = expandFromCenter(s, i, i + 1);  //l and r are at i, i+1

            if (odd.length() > maxStr.length()) {
                maxStr = odd;
            }
            if (even.length() > maxStr.length()) {
                maxStr = even;
            }
        }

        return maxStr;
    }

    private String expandFromCenter(String s, int left, int right) {
        int n = s.length();
        while (left >= 0 && right < n && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }


    //Appraoch-5: Manacher Algo.
    // TC = O(N), SC = O(1)
    public String longestPalindrome(String s) {
        StringBuilder sPrime = new StringBuilder("#");
        for (char c : s.toCharArray()) {
            sPrime.append(c).append("#");
        }

        int n = sPrime.length();
        int[] palindromeRadii = new int[n];
        int center = 0;
        int radius = 0;

        for (int i = 0; i < n; i++) {
            int mirror = 2 * center - i;

            if (i < radius) {
                palindromeRadii[i] = Math.min(
                    radius - i,
                    palindromeRadii[mirror]
                );
            }

            while (
                i + 1 + palindromeRadii[i] < n &&
                i - 1 - palindromeRadii[i] >= 0 &&
                sPrime.charAt(i + 1 + palindromeRadii[i]) ==
                    sPrime.charAt(i - 1 - palindromeRadii[i])
            ) {
                palindromeRadii[i]++;
            }

            if (i + palindromeRadii[i] > radius) {
                center = i;
                radius = i + palindromeRadii[i];
            }
        }

        int maxLength = 0;
        int centerIndex = 0;
        for (int i = 0; i < n; i++) {
            if (palindromeRadii[i] > maxLength) {
                maxLength = palindromeRadii[i];
                centerIndex = i;
            }
        }

        int startIndex = (centerIndex - maxLength) / 2;
        String longestPalindrome = s.substring(
            startIndex,
            startIndex + maxLength
        );

        return longestPalindrome;
    }
}
