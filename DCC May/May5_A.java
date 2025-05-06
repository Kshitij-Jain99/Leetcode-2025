//790. Domino and Tromino Tiling
// Appraoch-A: Recursion-TLE
// TC = O(), SC = O()
/*
 1. i-> column index, We start at column i = 0 with no pending half (i.e. possible = false) and recurse until we reach i = n.
 2. How does the possible flag work?
    A. When possible is false:
      No pending gap at column i; both cells are empty.
      We've have three choices to cover column i:
       a. Vertical domino
           Place a 2 × 1 domino spanning both rows at column i.
           Move to i + 1 with possible = false.
       b. Two horizontal dominoes
           Place one horizontal domino on the top row covering (i, i + 1) and another on the bottom row covering (i, i + 1).
           Move to i + 2 with possible = false.
       c. L-shaped tromino
           Place an L-shape covering both cells at column i and one cell at column i + 1.
           This leaves the other cell at column i + 1 empty (gap).
           Move to i + 2 with possible = true.
    B. When possible is true 
      Exactly one cell at column i is empty (the other was filled by a previous L-shape).
      We must fill this gap so inorder to fill it,
      We've have two ways to handle it:
       a. Complete the mirrored L-shape
          Use the other half of an L-shaped tromino so it covers the gap and two cells at column i.
          No new gap is created.
          Move to i + 1 with possible = false.
       b .Horizontal domino over the gap
          Place a horizontal domino over the empty cell and its neighbor in the same row at column i + 1.
          This fills the gap but creates a new gap at column i + 1.
          Move to i+1 with possible = true.
 3. Why add 2L * dominoes(i + 2, n, true)?
     The factor 2 counts both orientations of the L-shape (mirror images).
    */
public class May5_A {
    final long mod = 1000000007;

    public int numTilings(int n){
        return (int)dominoes(0,n, false);
    }

    private long dominoes(int i, int n, boolean possible){
        /*
Base case: If we’ve exactly filled all columns and no half-tile is left hanging (possible == false), that’s 1 valid tiling.
           If a half-tile is pending (possible == true), it’s invalid → return 0.
         */
        if(i == n) return possible ? 0 : 1;
        if(i>n) return 0; //gone past the end now so no valid way is possible.
 
        if(possible){
            return (dominoes(i+1,n, false) + dominoes(i+1,n, true)) % mod;
        }

        return (dominoes(i+1,n, false) 
                 + dominoes(i+2,n , false)
                 + 2*dominoes(i+2,n, true)) % mod;
    }
}
