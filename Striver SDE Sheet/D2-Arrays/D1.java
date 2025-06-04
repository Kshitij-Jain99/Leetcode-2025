
import java.util.Arrays;

//Find the duplicate in an array of N+1 integers
// Appraoch-1:Sorting
// TC = O(nlogn + n), SC = O(1)

public class D1 {
    public int findDuplicate(int[] arr){
        int m = arr.length;  //m=n+1
        Arrays.sort(arr);
        for(int i = 0; i<m-1; i++){
            if(arr[i] == arr[i+1]){
                return arr[i];
            }
        }
        return -1;
    }
    
}
