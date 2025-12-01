//Find the repeating and missing numbers
// Approach-1: Brute Force
// TC = O(n*n), SC = O(1)

public class E1 {
    public static int[] findMissingRepeatingNumbers(int[] a){
        int n = a.length;
        int repeating = -1, missing = -1;

        for(int i = 1; i<=n; i++){ //iterating on elements [1,N], not on array
            int cnt = 0;
            for(int j = 0; j<n; j++){ //counting freq of each element in array
                if(a[j] == i) cnt++;
            }
            if(cnt == 2) repeating = i;
            else if(cnt == 0) missing = i;

            if(repeating != -1 && missing != -1) {
                break;
            }
        }
        int[] ans = {repeating, missing};
        return ans; //return new int[]{repeating, missing};
    }
}
