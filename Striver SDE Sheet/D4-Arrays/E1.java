//Count the number of subarrays with given xor K
// Approach-1: Brute Force
// TC = O(N^2), SC = O(1)

public class E1 {
    public static int subarrayWithXorK(int[] a, int k){
        int n = a.length;
        int cnt = 0;

        for(int i = 0; i < n; i++){
          int xorr = 0;
          for(int j = i; j < n; j++){
            xorr = xorr ^ a[j];
            if(xorr == k) cnt++;
        }
    }
        return cnt;
    }
}
