//Sort an array of 0's 1's & 2's
// Appraoch-3: Optimal
// TC = O(N), SC = O(1)
/*
 In each of the step we are sorting one element and at end we will have to sort n elements
 */
import java.util.ArrayList;

public class E3 {
    public static void sortArray(ArrayList<Integer> arr, int n){
        int low = 0, mid  = 0 , high = n-1;

        while(mid  <= high){
            if(arr.get(mid) == 0){
                // swapping arr[low] and arr[mid]
                int temp = arr.get(low);
                arr.set(low, arr.get(mid));
                arr.set(mid, temp);

                low++;
                mid++;
            } else if(arr.get(mid) == 1){
                 mid++;
            } else{
                // swapping arr[mid] and arr[high]
                int temp = arr.get(mid);
                arr.set(mid, arr.get(high));
                arr.set(high, temp);

                high--;
            }
        }
    }
}
//ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(new Integer[] {0, 2, 1, 2, 0, 1}));