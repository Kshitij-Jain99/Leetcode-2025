// 2657. Find the Prefix Common Array of Two Arrays
// Approach A: Using two Hashset
// TC = O(n) -> iteration, SC = O(3.n) 

import java.util.HashSet;
import java.util.Set;

public class Jan14_D {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n=A.length;
        Set<Integer> s1=new HashSet<>();
        Set<Integer> s2=new HashSet<>();
        int[] ans=new int[n];
        int cnt=0;
        for(int i=0; i<n; i++){
            s1.add(A[i]);
            s2.add(B[i]);
            if(A[i]==B[i]) cnt++;
            else{
                if(s2.contains(A[i])) cnt++;
                if(s1.contains(B[i])) cnt++;
            }
            ans[i]=cnt;
        }
        return ans;
    }
}
