//Length of Longest Substring without any Repeating Character
// Approach-1: Brute Force
// TC = O(), SC = O()

import java.util.*;

public class F1 {
    static int solve(String str){
//There is no need to use Integer.MIN_VALUE because we won’t have any negative lengths.
        int maxans = 0;  //minimum possible length of a substring (even an empty string) is 0

        for(int i = 0; i<str.length(); i++){ // outer loop for traversing the string
            Set<Character> se = new HashSet<>(); // keeps track of characters seen so far for
            for(int j = i; j<str.length(); j++){ //// nested loop for getting different string starting with str[i]
                if(se.contains(str.charAt(j))) {
                    maxans = Math.max(maxans, j-1);
                    break;
                }
                se.add(str.charAt(j));
            }
            maxans = Math.max(maxans, str.length() - 1);
        }
        return maxans; 
    }
}
                                                                                                 