//Count Inversions in an Array
// Approach-1: Brute force
// TC = O(N*N), SC = O(1)

public class F1 {
     public static int numberOfInversions(int[] a, int n){
        int cnt =0;
        for(int i =0; i<n; i++){
                for(int j =  i+1;j<n; j++){
                    if(a[i] > a[j]) cnt++;
                }
            }
            return cnt;
        }
}
