//Reverse Pairs
//Approach-1: Brute Force
// TC = O(N^2), SC = O(1) 

public class F1 {
    public static int countPairs(int[] a, int n){
        int cnt = 0;
        for(int i = 0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(a[i] > 2*a[j]) cnt++;
            }
        }
        return cnt;
    }
    
    public static int team(int[] skill, int n){
        return countPairs(skill, n);
    }

}
