//Find the Majority Element that occurs more than N/2 times
// Appraoch-1: Brute Fore
// TC = O(N*N), SC  = O(1)

public class C1 {
    public static int majorityElement(int[] v){
        int n = v.length;
        int count = 0;

        for(int i = 0; i<n-1; i++){
            for(int j = i; j<n; j++){
                if(v[i] == v[j]) count++;
            }
            if(count > n/2) return v[i];
        }
        return 0;
    }
}
