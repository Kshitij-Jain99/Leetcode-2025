//73. Set Matrix Zeroes
// Approach-4: Using Hashset 
// TC =  O(m∗n)  , SC = O(m+n) 
import java.util.HashSet;
import java.util.Set;
public class A4 {
    public void setZeroes(int[][] matrix) {
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        Set<Integer> zeroRow = new HashSet<>();
        Set<Integer> zeroCol = new HashSet<>();

        // get all zero from matrix, set the index into a hashset to void duplicate
        for(int m = 0; m < rowNum; m++){
            for(int n = 0; n < colNum; n++){
                if(matrix[m][n] == 0){
                    zeroRow.add(m);
                    zeroCol.add(n);
                }
            }
        }

        // as the index of zero to set the entire row and column of matrix to 0
        for(Integer i : zeroRow){
            for(int n = 0; n < colNum; n++){
                matrix[i][n] = 0;
            }
        }
        for(int m = 0; m < rowNum; m++){
            for(Integer i : zeroCol){
                matrix[m][i] = 0;
            }
        }
    }
 }
