//1128. Number of Equivalent Domino Pairs
// Approach C: 
// TC = O(N), 
 //SC = O(1){hash map stores at most 9 * (9+1) / 2 = 45 unique domino types}
/*
 1. Compute the Cantor value for each domino to normalize it.
 2. Use a hash map to store the frequency of each Cantor value (i.e., each domino type).
 3. For each domino, add the current frequency of its Cantor value to the total count (representing pairs with previous equivalent dominoes), then increment the frequency.
 */
import java.util.*;

public class May4_C {
    public long cantorPair(int a, int b){
        int x = Math.min(a,b), y = Math.max(a,b);
        long sum = x+y;
        return (sum*(sum+1))/2 + y;
    }

    public int numEquivalentDominoPairs(int[][] dominoes){
        int count = 0;
//A hash map where the key is the Cantor Pairing value and the value is the frequency of that domino type.
        Map<Long, Integer> freq = new HashMap<>();
        for(int[] dom: dominoes){
            long pairValue = cantorPair(dom[0], dom[1]);
            count += freq.getOrDefault(pairValue,0);
            freq.put(pairValue, freq.getOrDefault(pairValue, 0) + 1);
        }
        return count;
    }
}
