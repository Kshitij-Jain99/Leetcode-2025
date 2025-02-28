//73. Set Matrix Zeroes
// Approach-6: Two pointers approach
// TC =  O(nm)  , SC = O(m+n)  
public class A6 {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] r = new int[m];
        int[] c = new int[n];

        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 0){
                    r[i] = 1;
                    c[j] = 1;
                }
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(r[i] == 1 || c[j] == 1){
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
