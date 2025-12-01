// Rotate Matrix/ Image
// Approach-2: Transpose + Reverse Rows
// TC  = O(2*N^2), SC = O(1)
/*
 1. Transpose the matrix : O(N^2)
 2. Reverse the Rows of the matrix: O(N^2)
 */
public class A2 {
     static void rotate(int[][] matrix){
        // Transpose the matrix
        for(int i =0; i<matrix.length; i++){
            for(int j = i; j< matrix[0].length; j++ ){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i = 0; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp =0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp; 
            }
        }
     }
}
/*
 1. -90° (270°) Rotation (Counter-Clockwise)
    Approach: Transpose + Reverse Columns
    // For ±90° rotations, the type of reversal (rows vs columns) determines direction
    
    // Step-1: Transpose
    // Step 2: Reverse Columns (top-bottom)
    for(int i = 0; i < matrix.length/2; i++) {
        for(int j = 0; j < matrix[0].length; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[matrix.length-1-i][j];
            matrix[matrix.length-1-i][j] = temp;
        }
    }

    2.  +180° Rotation
    Approach: Reverse Rows + Reverse Columns (or vice versa)
    here order of row/column reversal doesn't matter

    // Step 1: Reverse Rows (left-right)
    for(int i = 0; i < matrix.length; i++) {
        for(int j = 0; j < matrix[0].length/2; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[i][matrix[0].length-1-j];
            matrix[i][matrix[0].length-1-j] = temp;
        }
    }
    
    // Step 2: Reverse Columns (top-bottom)
    for(int i = 0; i < matrix.length/2; i++) {
        for(int j = 0; j < matrix[0].length; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = matrix[matrix.length-1-i][j];
            matrix[matrix.length-1-i][j] = temp;
        }
    }

 */