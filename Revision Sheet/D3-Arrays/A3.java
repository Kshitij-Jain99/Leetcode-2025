//Search in a 2D Matrix - I
// Appraoch-3: Optimized using Binary Search
// TC = O(log(NxM))(BS on 1D Array of size N*M), SC = O(1)
/*
 1. Flatten 2D array into 1D {for understanding, hypothetically}
 2. Converting 1D coordinates to 2D Coordinates: he cell is (i / m, i % m)(0-based indexing).
 3. 
 */
import java.util.ArrayList;

public class A3 {
    public static boolean searchMatrix(ArrayList<ArrayList<Integer>> matrix, int target){
        int n = matrix.size();
        int m = matrix.get(0).size();

         int low = 0, high = n * m - 1; //Converted coordinates
         while (low <= high) {
            int mid = (low + high) / 2;
            int row = mid / m, col = mid % m;
            if (matrix.get(row).get(col) == target) return true;
            else if (matrix.get(row).get(col) < target) low = mid + 1;
            else high = mid - 1;
    }
    return false;
  }
}