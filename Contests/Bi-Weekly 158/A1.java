//3572. Maximize Y‑Sum by Picking a Triplet of Distinct X‑Values
// Appraoch-1: Brute Force: TLE
// TC = O(n^3), SC = O(1)

public class A1 {
    public static int maxSumDistinctTriplet(int[] x, int[] y) {
        int n = x.length;
        int maxSum = -1;

        for(int i = 0; i<n; i++){ //i != j, j != k, k != i taken care in loop init.
            for(int j = i+1; j<n; j++){
                for(int k = j+1; k<n; k++){
                    if(x[i] != x[j] && x[j]!= x[k] && x[k] != x[i]){
                        int currSum = y[i] + y[j] + y[k];
                        if(currSum > maxSum) maxSum = currSum;
                    }
                }
            }
        }
        return maxSum;
    }
}
/*
 1. Chained Comparisons Don't Work in Java: if (x[i] != x[j] != x[k]) is not correct
   -Unlike Python, Java does not support chained comparisons like a != b != c
   -In Python, a != b != c is valid and means a != b and b != c (but not a != c unless chained further).
   -In Java: First, x[i] != x[j] → returns true or false
             Then, it compares the result (true/false) with x[k], which is invalid because you cannot compare a boolean (true/false) with an integer (x[k]).
 2. Correct Way:  if (x[i] != x[j] && x[j] != x[k] && x[k] != x[i])
   -You must explicitly write all pairwise comparisons:

 3. maxSum = Math.max(maxSum, currentSum);
   -More Readibility, Slightly slower performance{mostly negligible}
   -When you don’t need additional logic inside the if block.
 4. if (currentSum > maxSum) {
    maxSum = currentSum;
    // Optional: Additional logic (e.g., track indices)}
   -Slightly faster perfromance{no method call overhead, just a comparison}
   -Better when you need additional logic inside the if block, when new max is found
   -In performance-critical loops (e.g., competitive programming).
 */