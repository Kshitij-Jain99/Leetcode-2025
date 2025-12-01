//Find the Majority Element that occurs more than N/2 times
// Appraoch-1: Brute Fore
// TC = O(N*N), SC  = O(1)

public class C1 {
    public static int majorityElement(int[] v){
        int n = v.length;

        for(int i = 0; i<n; i++){
             int count = 0; // Reset count for each element
            for(int j = 0; j<n; j++){
                if(v[i] == v[j]) count++;
            }
            if(count > n/2) return v[i];
        }
        return -1;
    }
}
