//3567. Minimum Absolute Difference in Sliding Submatrix
// Appraoch-1: Brute Force
// TC =  O((m−k+1)(n−k+1) * k² log k), SC=  O((m − k + 1)(n − k + 1) + (k²))
/*
 1. Loop through all possible top-left corners of k x k windows.
 2. For each submatrix starting at (i, j), do the following:
    Flatten the submatrix into a 1D array.
    Sort that array.
    Iterate through the sorted array and compute the absolute differences between adjacent elements (which are the closest in value).
    Track and return the minimum difference found.
 */
import java.util.*;

public class B1 {
    //minimum absolute difference between any two distinct elements in the k x k submatrix 
    int getMin(int[][] g, int k, int x, int y){
        int m = g.length, n = g[0].length;
        int[] temp = new int[k*k];
        int st = 0;

        //flattening
        for(int i = 0; i<k; i++){
            for(int j = 0; j<k; j++){
                temp[st++] = g[i+x][j+y]; // (x, y) is the top-left corner of the submatrix
            }
        }
        Arrays.sort(temp);
        int diff = Integer.MAX_VALUE;
        for(int i = 1; i<k*k; i++){
            while(i<k*k && temp[i] == temp[i-1]) i++; //duplicates skip
            if(i<k*k) diff = Math.min(diff, Math.abs(temp[i] - temp[i-1]));
        }
        return diff;
    }

    public int[][] minAbsDiff(int[][] grid, int k){
        int m = grid.length, n = grid[0].length;
        int[][] ans = new int[m-k+1][n-k+1]; //each element of ans will correspond to a k*k matrix minDiff

        //geenerate all submatrices
        for(int i = 0; i<= m-k; i++){
            for(int j =0; j<=n-k; j++){
                int temp = getMin(grid,k, i, j);
                ans[i][j] =  temp == Integer.MAX_VALUE?0:temp;
            }
        }
        return ans;

    }
}
