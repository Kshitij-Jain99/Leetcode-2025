//Count Inversions in an Array
// Approach-2: Using Merge Sort
// TC = O(N*logN), SC = O(N)
/*
 1. not changing the merge sort algorithm except by adding a variable to it.
 2. V. imp: We are altering the array(given data)
 */
import java.util.*;

public class F2 {
     private static int merge(int[] arr, int low, int mid, int high){
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low;
        int right = mid+1;

         int cnt = 0; //Modification 1: cnt variable to count the pairs:

         //storing elements in the temporary array in a sorted manner//
         while(left <= mid && righ <= high){
            if(arr[left] <= arr[right]){
                temp.add(arr[left]);
                left++;
            } else{
                temp.add(arr[right]);
                cnt += (mid-left+1); //Modification 2
                right++;
            }
         }

         // if elements on the left half are still left
         while(left <= mid){
            temp.add(arr[right]);
            right++;
         }

          //  if elements on the right half are still left
           while (right <= high) {
            temp.add(arr[right]);
            right++;
        }
          
          // transfering all elements from temporary to arr
         for(int i = low; i<= high; i++){
            arr[i] = temp.get(i-low);
         }
         return cnt;   // Modification 3
     }

     public static int mergeSort(int[] arr, int low, int high){
        int cnt = 0;
        if(low >= high) return cnt;
        int mid = (low+high) / 2;
        cnt += mergeSort(arr, low, mid);  // left half
        cnt += mergeSort(arr, mid+1, high);  // right half
        cnt += merge(arr, low, mid, high);// merging sorted halves
        return cnt; 
     }
     
    public static int numberOfInversions(int[] a, int n) {
        // Count the number of pairs:
        return mergeSort(a, 0, n - 1);
    }
}
