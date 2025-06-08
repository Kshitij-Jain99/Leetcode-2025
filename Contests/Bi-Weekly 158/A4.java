//3572. Maximize Y‑Sum by Picking a Triplet of Distinct X‑Values
// Approach-4{Optimal}: Greedy Style
// TC = O(N), SC = O(1)
/*
 1. initialize three slots (a, b, c) to store the top 3 values and their corresponding unique x[i] values (ind1, ind2, ind3).
 2. For each (x[i], y[i]), we check:
  - If the x[i] matches one of the existing three indices, we update the max if y[i] is better.
  - If it doesn't match any, and y[i] is greater than the current minimum among a, b, and c, we replace the minimum with this new value and update the corresponding index.
 3. At the end, if we have found three distinct x[i] values (i.e., no slot is still zero), we return the sum. Otherwise, return -1.
  	New key, y[i] < min(a,b,c) → do nothing
 */
public class A4 {
    public int maxSumDistinctTriplet(int[] x, int[] y) {
        int a = 0, b = 0, c = 0; //store the maximum values chosen for the three distinct keys.
        int ind1 = 0, ind2 = 0, ind3 = 0; //the current keys (from x[]) corresponding to values a, b, c.

        for (int i = 0; i < x.length; i++) {
            if (x[i] == ind1) {
                a = Math.max(a, y[i]);
            } else if (x[i] == ind2) {
                b = Math.max(b, y[i]);
            } else if (x[i] == ind3) {
                c = Math.max(c, y[i]);

            } else if (y[i] > a || y[i] > b || y[i] > c) {
                int mini = Math.min(a, Math.min(b, c));
                //Replace the weakest one (smallest value) with the new (x[i], y[i]).
                if (mini == a) {
                    a = y[i];
                    ind1 = x[i];
                } else if (mini == b) {
                    b = y[i];
                    ind2 = x[i];
                } else {
                    c = y[i];
                    ind3 = x[i];
                }
            }
        }

        return (a == 0 || b == 0 || c == 0) ? -1 : (a + b + c);
    }
}

