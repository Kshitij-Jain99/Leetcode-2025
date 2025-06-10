// Two sum Problem
// Approach-1: Brute Force
// TC = O(N^2), SC = O(1)

public class A1 {
    // I Variant
     public static String twoSumI(int n, int []arr, int target) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) return "YES";
            }
        }
        return "NO";
     }
     //II Variant
     public static int[] twoSumII(int n, int []arr, int target){
        int[] ans = new int[2];
        ans[0] = ans[1] = -1;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    ans[0] = i;
                    ans[1] = j;
                    return ans;
                }
            }
        }
        return ans;
     }
}
