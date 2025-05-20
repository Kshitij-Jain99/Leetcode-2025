// Stock buy and sell
// Approach-2: Optimal
// TC = O(n), SC = O(1)

public class F2 {
    public static int maxProfit(int[] arr){
        int maxPro = 0;
        int minPrice = Integer.MAX_VALUE; // buy price mim. 
        for(int i = 0; i< arr.length; i++){
            minPrice = Math.min(minPrice, arr[i]); // arr[i] is sell price
            maxPro = Math.max(maxPro, arr[i] - minPrice);
        }
        return maxPro;
    }
}
