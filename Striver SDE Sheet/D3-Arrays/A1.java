//Search in a 2D Matrix - I
// Appraoch-1: Brute Force
// TC = O(N X M), SC = O(1)

import java.util.*;
public class A1 {
    public static boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target){
        int n = matrix.size(), m = matrix.get(0).size();

        for(int i = 0; i<n; i++){
            for(int j = 0; j<m; j++){
                if(matrix.get(i).get(j) == target) return true;
            }
        }
        return false;
    }
}
