//3343. Count Number of Balanced Permutations
// Appraoch-A: Brute Force
// TC = O(N! * N){Calculating all permutations*Sum of odd and even idx}; SC = O(N! * N){Storing all permutations}
/*
 1. Generate all unique permutations of its characters.
 2. For each permutation, compute the sum of digits at even indices (0-based) and sum of digits at odd indices.
 3. Count how many permutations have these two sums equal.
 4. 
 */
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class May9_A {
    public int countBalancePermutations(String num){
        char[] chars = num.toCharArray();
        Arrays.sort(chars);  // Sorting helps next_permutation to find all permutations, so we need to start from smallest (hence sorted)
        Set<String> seen = new HashSet<>(); //to skip duplicate permutations
        int count = 0;
        
        do { //Even and Odd Sum
            String s = new String(chars);
            if(seen.contains(s)) continue;
            seen.add(s);
            int evenSum = 0, oddSum = 0;
            
            for(int i = 0; i<chars.length; ++i){
                int digit = chars[i] - '0';
                if(i%2 == 0) evenSum += digit;
                else oddSum += digit;
            }
            if(evenSum == oddSum) count++;

        } while (nextPermutation(chars));

        return count;
    }

    // Helper method for next permutation
    private boolean nextPermutation(char[] arr){
        int i = arr.length -2;
        while(i>= 0 && arr[i] >= arr[i+1]) i--;
        if(i<0) return false;
        int j = arr.length-1;
        while(arr[j] <= arr[i]) j--;
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        reverse(arr, i+1, arr.length-1);
        return true;
    }

    private void reverse(char[] arr, int start, int end){
        while(start <end){
            char tmp = arr[start];
            arr[start++] = arr[end];
            arr[end--] = tmp;
        }
    }
}
