// Rotate Matrix/ Image
// Approach-1: Brute Force
// TC  = O(N^2), SC = O(N^2)
/*
 1.  Take another dummy matrix of n*n
 */
public class A1 {
    static int[][] rotate(int[][] matrix){
        int n = matrix.length;
        int rotated[][] = new int[n][n];
        for(int i = 0; i<n; i++){
            for(int j = 0 ; j<n; j++){
                rotated[j][n-i-1] = matrix[i][j];
            }
        }
        return rotated;
    }
}
