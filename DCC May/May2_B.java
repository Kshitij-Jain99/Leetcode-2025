//838. Push Dominoes
//Approach-B:  Virtual Boundary+Segment Analysis
// TC = O(N), SC = O(N){New string}

/*
Idea: Add virtual boundaries (L at start and R at end), 
 then look for segments of dots between two known non-dot characters and simulate how dominoes fall between them.
 R'....'R' → all become 'R'
'L'....'L' → all become 'L'
'L'....'R' → remain unchanged
'R'....'L' → half fall to 'R', half to 'L', middle stays '.' if odd

So instead of tracking "lastR" and "lastL" manually and applying logic after we encounter an 'L' or 'R', we can pre-process the string to add:
  a virtual 'L' at the beginning
  a virtual 'R' at the end
In appraoch A, you're always just checking the dominoes between two non-dot characters,
and applying one of 4 behaviors based on their combination.

 */
public class May2_B {
    public String pushDominoes(String dominoes) {
        // Add virtual dominoes at boundaries L and R
        String s = "L" + dominoes + "R";
        int n = s.length();
        StringBuilder result = new StringBuilder();

        // i->last non-dot, j->next non-dot
        for (int i = 0, j = 1; j < n; j++) {
            if (s.charAt(j) == '.') continue;

            int mid = j - i - 1; //number of '.' between i and j

            // Add the left boundary if not virtual, skip first virtual 'L'
            if (i > 0) result.append(s.charAt(i));

            // Case 1: Same direction (e.g., L...L or R...R)
            if (s.charAt(i) == s.charAt(j))
                result.append(String.valueOf(s.charAt(i)).repeat(mid));

            // Case 2: Opposite outwards (e.g., L...R)
            else if (s.charAt(i) == 'L' && s.charAt(j) == 'R')
                result.append(".".repeat(mid));

            // Case 3: Opposite inwards (R...L)
            else {
                int half = mid / 2;
                result.append("R".repeat(half));
                if (mid % 2 == 1) result.append('.');
                result.append("L".repeat(half));
            }

            i = j;
        }

        return result.toString();
    }
}
