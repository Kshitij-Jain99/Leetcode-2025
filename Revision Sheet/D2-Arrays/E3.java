//Find the repeating and missing numbers
// Approach-3: Maths- Solving 2 linear Equations for 2 unknowns
// TC = O(N), SC = O(1)
/*
 1. Convert the given problem into mathematical equations and solve those equations.
 2. Must know basic math formulas:
   Sn = (N*(N+1))/2, S2n = (N*(N+1)*(2N+1))/6
 */
public class E3 {
    
    public static int[] findMissingRepeatingNumbers(int[] a) {
        long n = a.length;
        long SN = (n*(n+1))/2;
        long S2N = (n*(n + 1)*(2*n + 1)) / 6;

        long S = 0, S2 = 0;
        for(int i =0; i<n ;i++){
            S += a[i];
            S2 += (long)a[i]*(long)a[i];
        }

        long val1 = S-SN; //X-Y
        long val2 = S2-S2N; 
        val2 = val2/val1; //X+Y

        long x = (val1+val2)/2;
        long y = x-val1;

        return new int[]{(int)x, (int)y};
    }
}
