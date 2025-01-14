// 2657. Find the Prefix Common Array of Two Arrays
// Approach A: Using a Frequency Array
// TC = O(n) -> iteration, SC = O(n) -> freq array, res arr
/*
 1. Both ( A ) and ( B ) are permutations, meaning all integers from ( 1 ) to ( n ) are present exactly once.
 2. We can use an auxiliary structure to track which elements have been encountered in both arrays.
 3. Best or readibility and simplicity
 */
public class Jan14_A {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        int[] freq = new int[n+1]; //act as HashMap, keys are from 1 to n-> n+1
        int count = 0;
        for(int i =0; i<n; i++){
            if(++freq[A[i]] == 2) count++;
            if(++freq[B[i]] == 2) count++;
            result[i] = count;
        }
        return result;
    }
}
