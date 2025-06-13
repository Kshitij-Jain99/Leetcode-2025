// Longest Consecutive Sequence in an Array
// Approach-1: Brute Force
// TC = O(N^2), SC = O(1)

public class C1 {
    public static int longestSuccessiveElements(int[] a){
        int n = a.length;
        int longest = 1;

         //pick a element and search for its consecutive numbers
         for(int i = 0; i<n; i++){
            int x = a[i];
            int cnt = 1;

            //search for consecutive numbers: Linear search
            while(linearSearch(a, x+1) == true){
                 x += 1;
                 cnt += 1; 
            }
            longest = Math.max(longest, cnt);
         }
         return longest;
    }
    
    public static boolean linearSearch(int[] a, int num){
        int n = a.length;
        for(int i =0; i<n; i++){
            if(a[i] == num) return true;
        }
        return false;
    }
}
