//838. Push Dominoes
//Approach-C:  Force Simulation
// TC = O(N), SC = O(N){array of size N}
/*
 1. Think of domino forces like gravity waves spreading from R and L, The influence fades linearly outward
 2. Do two passes: left-to-right and right-to-left
 3. Accumulate net force per position and decide state based on net result
 */
public class May2_C {
        public String pushDominoes(String dominoes) {
            int n = dominoes.length();
            int[] forces = new int[n]; //net force acts one each domino
            /*
             Positive = pushed right
             Negative = pushed left
             Zero = remains standing
             */
            int force = 0;
    
            // Left to right pass: apply 'R' forces
            for (int i = 0; i < n; i++) {
                if (dominoes.charAt(i) == 'R')  
                    force = n; //n is used to ensure the force can reach the entire array if needed
                else if (dominoes.charAt(i) == 'L') //blocks the force
                    force = 0;
                else
                    force = Math.max(force - 1, 0);  //Right mov. weaks force by 1
                forces[i] += force;
            }
    
            // Right to left pass: apply 'L' forces (negative)
            force = 0;
            for (int i = n - 1; i >= 0; i--) {
                if (dominoes.charAt(i) == 'L')
                    force = n;
                else if (dominoes.charAt(i) == 'R')
                    force = 0;
                else
                    force = Math.max(force - 1, 0);
                forces[i] -= force; //Leftward force is negative
            }
    // Determine final state
            StringBuilder result = new StringBuilder();
            for (int f : forces) {
                if (f == 0) result.append('.');
                else if (f > 0) result.append('R');
                else result.append('L');
            }
    
            return result.toString();
        }
    }   

