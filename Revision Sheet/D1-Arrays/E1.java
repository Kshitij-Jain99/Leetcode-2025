//Sort an array of 0's 1's & 2's
// Appraoch-1: Brute Force(Merge Sort)
// TC = O(NLogN), SC = O(N)
/*
 1. Merge Sort is a divide and conquers algorithm, it divides the given array into equal parts and then merges the 2 sorted parts. 
 2. There are 2 main functions :
    a. merge() : merges the 2 sorted parts
    b. mergeSort() : divides the array into equal parts
 3. We recursively split the array, and go from top-down until all sub-arrays size becomes 1.
 */
import java.util.ArrayList;

public class E1 {
    public static void merge(int[] arr, int low, int mid, int high){
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low;      // starting index of left half of arr
        int right = mid + 1; // starting index of right half of arr

        // storing elements in the temporary array in a sorted manner
        while(left <= mid && right <= high){
            if(arr[left] <= arr[right]){
                temp.add(arr[left]);
                left++;
            } else{
                temp.add(arr[right]);
                right++;
            }
        }

         // if elements on the left half are still left
         while(left <= mid){
             temp.add(arr[left]);
             right++;
         }

          //  if elements on the right half are still left
         while(right <= high){
            temp.add(arr[right]);
            right++;
         }

          // transfering all elements from temporary to arr
          for(int i = low; i<= high; i++){
            arr[i] = temp.get(i-low);
          }
    }

    public static void mergeSort(int[] arr, int low, int high){
        if(low >= high) return;
        int mid = (low + high)/2;
        mergeSort(arr, low, mid);  //left half
        mergeSort(arr, mid+1, high); //right half
        merge(arr, low, mid, high);
    }
}
