// 3223. Minimum Length of String After Operations
// Approach A: Freq Count
// TC = O(N), SC = O(1)
/*
 1. If freq = odd -> final freq = 1 
 2. If freq = even -> final freq = 2 
 3. When freq = 0/1/2  -> no change
 */

public class Jan13_A {
    public int minLength(String s){
    int[] freq = new int[26]; //to store freq of characters
    for(char c: s.toCharArray()){
        freq[c - 'a']++; //increment frq of char
    }

    int length = 0;
    for(int count: freq) {
        if(count%2 == 1) { //odd freq
            length += 1; //contribution in len
        } else {
            length += Math.min(2,count); //even freq, contribution in len
        }
    }
    return length;
 }
}