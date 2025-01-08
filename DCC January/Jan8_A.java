//3042. Count Prefix and Suffix Pairs I
// Approach A: Brute Force
// TC = O(n^2*L^2), SC = O(1)    //L ->Length of words

class Jan8_A{
    public int countPrefixSuffixPairs(String[] words) {
        int count = 0;
        int len = words.length;

        for(int i = 0; i<len; i++){
            for(int j = i+1; j<len; j++){
                if(isPrefixAndSuffix(words[i], words[j])){
                    count++;
                }
            }
        }
        return count;
    }

    public static boolean isPrefixAndSuffix(String a, String b){
        if((b.indexOf(a) == 0) && (b.lastIndexOf(a) == (b.length()) - a.length())){
            return true;
        }
        return false;
    }
}