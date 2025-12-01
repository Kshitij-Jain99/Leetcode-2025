//Merge two Sorted Arrays Without Extra Space
//Approach-1: Brute Force
// TC = O( 2*(n+m) ), SC = O(n+m)

public class C1 {
    public static void merge(long[] arr1, long[] arr2, int n, int m ){
        // Declare 3rd array and 2 pointers
        long[] arr3 = new long[n + m];
        int left = 0, right = 0, idx = 0;

        //insert elements from both arrays into arr3
        while(left<n && right <m){
            if(arr1[left] <= arr2[right]){
                arr3[idx] = arr1[left];
                left++;
                idx++;
            } else{
                arr3[idx] = arr2[right];
                right++;
                idx++;
            }
        }

        // If right pointers reaches end
        while(left < n) {
             arr3[idx++] = arr1[left++];
        }
        //If left pointer reaches end
        while(right < m){
            arr3[idx++] = arr2[right++];
        }

        // Copy elements from arr3 to arr1, arr2
        for(int i = 0; i<n+m; i++){
            if(i<n) arr1[i] = arr3[i];
            else arr2[i-n] = arr3[i];
        }
    }
}
