//2145. Count the Hidden Sequences
//Approach-A: Prefix sum approach/Running sum technique
// TC = O(N), SC = O(1)


/*
 1. Brute Force: If we try for all possible values of hidden[0] from lower to upper, TC = O(N^2) -> TLE
 2. Solve for better than TC = O(N^2)
 3. differences[i] + hidden[i] = hidden[i + 1] Calculate hidden[] using this, assuming hidden[0] = x
     hidden[0] = x
     hidden[1] = x + differences[0]
     hidden[2] = x + differences[0] + differences[1]
     hidden[i] = x + prefix_sum[i]
 4.  lower <= x <= upper  Bound = upper - lower + 1
 5. Lets say hidden[] = [x, x+1, x-2, x+2, x+1] for diff[] = [1,-3,4,-1]
    then Range = maxValue - minValue + 1 = (x+2)-(x-2) + 1 = 5
 6. If range > Bound, then we cannot have any hidden[]
 7. Possible sequences = Bound - range + 1
    This gives us valid hidden[] such that we don't go out of [Lower, upper] bound 
    To ensure all values stay within bounds, we need to choose an x such that:
    lower ≤ x + min_offset
    upper ≥ x + max_offset
    This gives : (x ∈ [lower - min_val, upper - max_val])
 8. So to calcuate range, we need to track minValue and maxValue  
    */
public class Apr21_A {
    public int numberOfArrays(int[] differences, int lower, int upper){
        // Taking long to avoid overflow
        // Working on reconstructed hidden[] array
        long minVal = 0;
        long maxVal = 0;
        long currVal = 0;
        
        for(int ele: differences){
            currVal += ele; //differences[i] + hidden[i] = hidden[i + 1]
            minVal = Math.min(minVal, currVal);
            maxVal = Math.max(maxVal, currVal);
        }
        //number of valid starting values (x) for which the entire array stays in bounds.
        long count = (upper - lower) - (maxVal - minVal) + 1;
        // if there's no valid range, we return 0 instead of a negative count.
        return count> 0? (int) count:0;  
    }
}
/*
Follow Up Qs.:
1.  What If Bounds Differ for Each Index?
    lower[i] ≤ x + prefix_sum[i] ≤ upper[i]
    Use min/max bounds for x across all i to get the valid range.
 */

 /*
  Brute Force:TLE
  public int numberOfArrays(int[] differences, int lower, int upper) {
        int firstLastDiff = 0, n = differences.length, c =0;

        for(int diff: differences) firstLastDiff+=diff;

        for(int i = lower;i<=upper;i++){
            int prev = i;
            for(int j=0;j<n;j++){
                prev = differences[j] + prev;
                if(prev<lower || prev>upper){
                    break;
                }
                if(j == n-1){
                    if(prev != i+firstLastDiff) break;
                    c++;
                }
            }
        }

        return c;
    }
  */