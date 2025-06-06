//Find the repeating and missing numbers
// Approach-2: Hashing
// TC = O(2N), SC = O(N)
/*
 1. Whenver counting comes up, Hashing can be used to optimize the code.
 */
public class E2 {
     public static int[] findMissingRepeatingNumbers(int[] a){
        int n = a.length;
        int[] hash = new int[n+1];

        for(int i = 0; i<n; i++) hash[a[i]]++;

        int repeating = -1, missing = -1;
        for(int i = 1; i<=n; i++){
            if(hash[i] == 2 ) repeating = i;
            else if(hash[i] == 0) missing = i;

            if(repeating != -1 && missing != -1) break;
        }
        return new int[]{repeating, missing};
     }

}
