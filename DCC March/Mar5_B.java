//2579. Count Total Number of Colored Cells
// Approach 2: Pattern
// TC = O(n), SC= O(1)

public class Mar5_B {
    public long colouredCells(int n){
    long count =1;
    long jumpSize = 4;
    while(n> 1){
        count += jumpSize;
        jumpSize += 4;
        n--;
    }
    return count;
    }
}
