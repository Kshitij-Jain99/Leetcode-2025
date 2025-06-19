// 84. Largest Rectangle in Histogram
// Approach-1: Brute Force
// TC = O(N^2), SC = O(1)
/*
 1. Taking different bars and finding maximum width possible using the bar.
 */
public class A1 {
    static int largestArea(int arr[], int n){
        int maxArea = 0;
        for(int i = 0; i<n; i++){
            int minHeight = Integer.MIN_VALUE;
            for(int j = i; j<n; j++){
                minHeight = Math.min(minHeight, arr[j]);
                maxArea = Math.max(maxArea, minHeight * (j - i + 1));
            }
        }
        return maxArea;
    }
}
