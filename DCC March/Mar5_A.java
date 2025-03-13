//2579. Count Total Number of Colored Cells
// Appraoch 1: Formula
// TC = O(1), SC= O(1)
public class Mar5_A {
    public long colouredCells(int n){
        return 1 + 4*((long)n*(n-1)/2);
        // return (long)n * n + (long)(n - 1) * (n - 1);
    }
}
