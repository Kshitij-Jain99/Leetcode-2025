// 2657. Find the Prefix Common Array of Two Arrays
// Approach B:Using Bitmasking: Most Optimized
// TC = O(n) -> iteration, SC = O(1) -> two bitmask
/*
 1.Numbers in ( A ) and ( B ) are distinct and range from 1 to ( n ). This makes it possible to represent their presence using bits in an integer or long, treating each bit as a flag. 
 This approach efficiently tracks the commonality without extra memory for a frequency array.
 2. Use two bitmasks for A and B
 3. Compute the bitwise AND for common numbers
 4.  count the number of set bits in the AND mask.
 */
public class Jan14_B {
    public int[] findThePrefixCommonArray(int[] A, int[] B){
        long maskA = 0L;  
        long maskB = 0L;
        int ans[] = new int[A.length];

        for(it i=0; i<A.length; i++){
            maskA |= (1L << (A[i]-1)); //Set bit for A[i]
            maskB |= (1L << (B[i]-1)); //Set bit for B[i]

            long commonMask = maskA & maskB; 
            ans[i] = Long.bitCount(commonMask);
        }
        return ans;
    }
}
