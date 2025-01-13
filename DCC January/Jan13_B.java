// 3223. Minimum Length of String After Operations
// Approach B: Pair reduction
// TC = O(N)->constant pair reduction, SC = O(1)
/*
 1. Count character frequencies.
 2. For each frequency ≥ 3, reduce pairs (-2) iteratively until the frequency is < 3.
 3. Subtract 2 for every removable pair until no more pairs can be removed.
 */
import java.util.HashMap;

public class Jan13_B {
    public int minimumLength(String s){
        HashMap<Character, Integer> count = new HashMap<>();
        for(char c: s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        int minus = 0;
        for(int value: count.values()){
            while(value >= 3){
                minus += 2;
                value -= 2;
            }
        }
        return s.length() - minus;
    }
}
