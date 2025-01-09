//2185. Counting Words With a Given Prefix
//Approach A: Simple loop
// TC = O(N.K) =(N.wordLen)  , SC = O(1)

class Jan9_A {
    public int prefixCount(String[] words, String pref) {
        int count = 0;
        for (String word : words) {
            if (word.indexOf(pref) == 0) {
                count++;
            }
        }
        return count;
    }
}