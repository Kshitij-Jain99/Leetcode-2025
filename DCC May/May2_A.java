//838. Push Dominoes
//Approach-A:  Two-Pointer Simulation Approach
// TC = O(N), SC = O(N){New string}
// Machine coding round type Qs.
/*
  we simulate how each domino falls sequentially, based on the last seen 'R' or 'L'
  Core idea: Treat each stretch of . between two known dominoes as a segment to simulate.
 */
public class May2_A {
    class Domino {
        // when pattern is R...L. Push inwards from both sides.
        //ptrs are '...' between R and L
        public void doubleDominoPush(int left, int right, StringBuilder dominoes) {     
            while (left < right) { 
                //This handles cases for both even and odd dominoes standing in between
                dominoes.setCharAt(left++, 'R');  
                dominoes.setCharAt(right--, 'L');
            }
        }

        // When L...L Everything falls to left.
        public void leftDominoPush(int start, int end, StringBuilder dominoes) {
            while (start <= end) {
                dominoes.setCharAt(start++, 'L');
            }
        }
       //when R...R Everything falls to right.
        public void rightDominoPush(int start, int end, StringBuilder dominoes) {
            while (start <= end) {
                dominoes.setCharAt(start++, 'R');
            }
        }
    }

        public String pushDominoes(String dominoes) {
            Domino d = new Domino();
            int lastL = -1, lastR = -1; // Track latest L and R positions
            int n = dominoes.length();
            StringBuilder sb = new StringBuilder(dominoes);

            for (int pos = 0; pos < n; pos++) {
                //1
                if (sb.charAt(pos) == 'L') {
                    if (lastR > lastL) {  //A
                        d.doubleDominoPush(lastR + 1, pos - 1, sb);
                    } else {              //B
                        d.leftDominoPush(lastL + 1, pos - 1, sb);
                    }
                    lastL = pos;         

                  //2
                } else if (sb.charAt(pos) == 'R') {
                    if (lastR > lastL) {  //D
                        d.rightDominoPush(lastR + 1, pos - 1, sb);
                    }
                    // lastR > lastL SKIPPED
                    lastR = pos;
                }
            }

            // Final case: R...R (at the end)
            if (lastR > lastL) {
                d.rightDominoPush(lastR + 1, n - 1, sb);
            }

            return sb.toString();
        }
}


