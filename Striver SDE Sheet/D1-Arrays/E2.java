//Sort an array of 0's 1's & 2's
// Appraoch-2: Better
// TC = O(2*N), SC = O(1)
/*
 1. Keep count of 0,1,2 using variables
 2. Now for each count update values of array
 */
import java.util.*;

public class E2 {
    public static void sortArray(ArrayList<Integer> arr, int n){
        int cnt0 = 0, cnt1 = 0 , cnt2 = 0;

        for(int i = 0; i<n; i++){
            if(arr.get(i) == 0) cnt0++;
            else if(arr.get(i) == 1) cnt1++;
            else cnt2++;
        }
        for(int i = 0; i< cnt0; i++) arr.set(i,0);
        for(int i = cnt0; i< cnt0 + cnt1; i++) arr.set(i,1);
        for(int i = cnt0 + cnt1; i<n; i++) arr.set(i,2);
    }
}
