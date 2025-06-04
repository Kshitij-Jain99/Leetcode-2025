//Find the duplicate in an array of N+1 integers
// Appraoch-2: Using freq array
// TC = O(N), SC = O(N)

public class D2 {
    static int findDuplicate(int[] arr){
        int m = arr.length;
        int freq[] = new int[n+1];
        for(int i =0; i<m; i++){
            if(freq[arr[i]] == 0){
                freq[arr[i]]++;
            } else{
                return arr[i];
            }
        }
        return -1;
    }
}
