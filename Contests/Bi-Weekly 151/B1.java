//3468. Find the Number of Copy Arrays
// Approach 1: Common intervals, Prefix Sum
// TC = O(N), SC = O(1)
class B1 {
    public int countArrays(int[] original, int[][] bounds) {
        // to calculate bound of copy[i], calculate copy[i+1] using prevDiff
        int lower = bounds[0][0], upper = bounds[0][1], prevDiff = 0;

        for(int i = 1; i<original.length; i++){
            int diff = original[i] - original[i-1];
            diff += prevDiff;
            lower = Math.max(lower, bounds[i][0] - diff); 
            upper = Math.min(upper, bounds[i][1] - diff);
            prevDiff = diff; //, we need this total accumulated difference for the next iteration, so we store it

            /*Not needed as we have added this condition below
            It allows early exit, more efficient  than final check condition
             * if (lower > upper) {
                return 0;
            }
             */
            
        }

        int result = upper - lower + 1;
        return Math.max(result, 0); //ensures that we never return a negative value—instead, we return 0 if no valid arrays exist.
    }
}