// Stock buy and sell
// Approach-1: Brute Force
// TC = O(N^2), SC = O(1)

public class F1 {
    public static int maxProfit(int[] arr){
        int maxPro = 0;
        for(int i =0; i< arr.length; i++){
            for(int j = i+1; j< arr.length; j++){
                if(arr[j] > arr[i]) maxPro = Math.max(arr[j] - arr[i], maxPro);
            }
        }
        return maxPro;
    }
}
