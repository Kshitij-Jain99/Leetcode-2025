//22. Generate Parentheses: https://leetcode.com/problems/generate-parentheses/description/
import java.util.*;
public class H{

    //Appraoch-1: Brute Force[BFS using queue]
    //TC = O(2^(2n) * n), SC = O(2^(2n) * n) auxilary space for storing ans
/*
 1. Generating all possible strings of length 2n and verifying their validity.
 2. Valid combinations must have n left and n right parentheses.
 3. If left parentheses count < 0 at any point, the string is invalid.
 4. At each character we have 2 choices: add '(' or ')' -> 2^2n unique substrings and for each string we check its validity by linear iteration.
 */
  public List<String> generateParenthesis1(int n){
    List<String> ans = new ArrayList<>();   //store valid strings
    Queue<String> queue = new LinkedList<>(Arrays.asList(""));

    while(!queue.isEmpty()){
        String currString = queue.poll();
 
        if(currString.length() == 2*n){  
            if(isValid(currString)) ans.add(currString); 
            continue;  // Don't generate further from a full-length string
        }

        // Generate next strings by adding '(' or ')'
         queue.add(currString + "(" );
         queue.add(currString + ")" );
    }
   return ans;
  }

  private boolean isValid(String pString){
    int leftCount = 0;
    for(char p : pString.toCharArray()){
        if(p == '(') leftCount++;
        else leftCount--;

        if(leftCount < 0) return false; // More right parentheses than left
    }
    return leftCount == 0; // Valid if all left parentheses are matched
  }
 
    //Appraoch-2: Backtracking
    //TC = O(4^n / sqrt(n)){Using Catalan's Number}, SC = O(2n){recursion stack space} 
/*
 1. Generate only valid substrings instead of all possible strings.
 2. Checking isValid at each step and backtarcking if the string is invalid without fully generating it.
*/  
  public List<String> generateParenthesis2(int n){
    List<String> ans = new ArrayList<>();
    backtracking(ans, new StringBuilder(), 0, 0, n);
    return ans;
  }
   
  private void backtracking(List<String> ans, StringBuilder currString, int leftCount, int rightCount, int n){
    // Base case: If the string is the correct length, we've found a valid combination.
    if(currString.length() == 2*n) {
        ans.add(currString.toString());
        return;
    }

    // --- Add an open parenthesis '(' ---
    // We can add an open parenthesis as long as we haven't used all 'n' of them.
    if(leftCount < n){
        currString.append("(");
        backtracking(ans, currString, leftCount + 1, rightCount, n);
        currString.deleteCharAt(currString.length() - 1); // Backtrack
    }

     // --- Add a close parenthesis ')' ---
    // We can only add a close parenthesis if there's an open one to match it.
    if (rightCount < leftCount) {
          currString.append(")");
          backtracking(ans, currString, leftCount, rightCount + 1, n);
          currString.deleteCharAt(currString.length() - 1); // Backtrack
        }
  }

  //Appraoch-3: Divide and Conquer.
  //TC = O(4^n / sqrt(n)){upper bound of asymptote}, SC = O(n){recursion stack space}
  /* Intuition: Calatan Numbers
   1. Decomposing the problem into smaller subproblems: 
      Generating valid Parenthesis of len. 2n to generating valid parenthesis of smaller lengths.
   2. Using solution of these subproblems to build up for original problem.
   3. Number of valid strings formed by with n pairs of parentheses is given by:
      F(n) = F(0)F(n-1) + F(1)F(n-2) + ... + F(n-1)F(0)
      F(n) = ["(" + F(0) + ")" + F(n-1)] + ["(" + F(n-1) + ")" + F(0)] + ...
   4. This formulae matches the Catalan number sequence. 
      So, nth catalan number gives the number of valid parentheses combinations.
   5. A is a valid parenthesis sequence that is inside the first pair of parentheses.
      B is a valid parenthesis sequence that comes after the first pair.
   6. To generate all combinations for n pairs, we allocate i pairs to form the inner sequence A, then the remaining n - 1 - i pairs must be used to form the outer sequence B  
   */
  public List<String> generateParenthesis3(int n) {
    if(n==0) return new ArrayList(Arrays.asList(""));

    List<String> ans = new ArrayList<>();
    for(int leftCount = 0; leftCount < n; ++leftCount) {  
        //Divide
       for(String leftString: generateParenthesis3(leftCount)) { // recursive call gets all possible valid strings for the inner part A
        for(String rightString: generateParenthesis3(n - 1 - leftCount)) { // second call gets all possible valid strings for the outer part B
            //Conquer: 
            //takes every possible leftString (for A) and every possible rightString (for B) and combines them into the final (A)B format.
            ans.add("(" + leftString + ")" + rightString);
     }
    }
   }
   return ans;
  }
}