//3577. Count the Number of Computer Unlocking Permutations
//Approach-1:Brute Force{TLE}
// TC = O(n! * n^2), SC = O((n! * n))
/*
1. Generate all permutations of indices [0, 1, ..., n-1].
2. For each permutation:
   -Check if it starts with computer 0 (since only computer 0 is initially unlocked).
   -Maintain a set of unlocked computers.
   -For each next computer i in the permutation, try to unlock it by checking:
      a. Is there any earlier computer j in the unlocked set such that j < i and complexity[j] < complexity[i]?
      b. If no such j exists → invalid permutation.
3. Count all valid permutations.
 */
import java.util.*;

class B1 {
    public int countPermutations(int[] complexity) {
        int n = complexity.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) list.add(i); //?

        List<List<Integer>> permutations = new ArrayList<>();
        permute(list, 0, permutations);

        long MOD = (long)1e9 + 7; //10^9 + 7 for modulo arithmetic.
        int validCount = 0;

        for (List<Integer> perm : permutations) {
            if (perm.get(0) != 0) continue; // must start with computer 0

            Set<Integer> unlocked = new HashSet<>();
            unlocked.add(0);
            boolean isValid = true;

            for (int i = 1; i < n; i++) {
                int current = perm.get(i);
                boolean canUnlock = false;

                for (int j : unlocked) {
                    if (j < current && complexity[j] < complexity[current]) {
                        canUnlock = true;
                        break;
                    }
                }

                if (!canUnlock) {
                    isValid = false;
                    break;
                }
                unlocked.add(current);
            }

            if (isValid) validCount++;
        }

        return (int)(validCount % MOD);
    }

    //generate all permutations.
    private void permute(List<Integer> list, int start, List<List<Integer>> res) {
        if (start == list.size()) { //start is the current position to fix an element.
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = start; i < list.size(); i++) {
            Collections.swap(list, i, start);
            permute(list, start + 1, res);
            Collections.swap(list, i, start); //Swap back (backtrack) after recursive call.
        }
    }
}
