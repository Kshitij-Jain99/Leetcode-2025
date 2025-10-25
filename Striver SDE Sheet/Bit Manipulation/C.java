//LC.318. Maximum Product of Word Lengths: https://leetcode.com/problems/maximum-product-of-word-lengths/description/

public class C{

    //Approach-1: Brute Force
    //TC = O(N^2 * f(L1,L2)), SC = O()
    /*
     1. 
     */
     public int maxProduct(String[] words){
        int n = words.length;
        
        int maxProduct = 0;
        for(int i=0; i<n; i++){
            for(int j = i+1; j<n; j++){
                if(noCommonChars1(words[i], words[j])){
                    maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
                }
            }
        }
        return maxProduct;
     }
    
     //Approach-A: Navie Solution: O(L1 * L2)
     /*
      1. Checking for each charcter, ensure that this character is not in second word.
      */
     public boolean noCommonChars1(String s1, String s2){ //O( f(L1,L2) )
        for(char ch: s1.toCharArray()){
            if(s2.indexOf(ch) != -1) return false;
        }
        return true;
    }

    //Approach-B: Using Bitmasks: O(L1 + L2)
    /*
     1. words only has lower case english letters -> Each word presence can be encoded with a bitmask of 26 characters
     2. If character is present set bit 1 else 0.
     3. To set nth bit: bitmask |= (1 << n)
        - n is the bit number of that character
     4. This way we can compute two bitmasks, charcacter by character.
     */
     public boolean noCommonChars2(String s1, String s2){
        int bitmask1=0, bitmask2=0;
        for(char ch: s1.toCharArray()) bitmask1 |= 1 << bitNumber(ch);
        for(char ch: s2.toCharArray()) bitmask2 |= 1 << bitNumber(ch);

        return (bitmask1 & bitmask2) == 0;
     }

     public int bitNumber2(char ch){
        return (int)ch - (int)'a';
     }

     //Approach-C: Using Bitmasks + Precomputation: O(1)
     /*
      1. Approach-B computes bitmask of each word N times.
      2. Instead each bitmask can be precomputed once, then memorised and used for runtime comparison in a constant time.
      3. Store bitmasks and string lengths in 2 integer arrays.
      4. Java works faster with arrays than hashmaps.
      5. 
      */
        
        public int maxProduct3(String[] words){
            int n = words.length;
            int[] masks = new int[n];
            int[] lens = new int[n];

            int bitmask = 0;
            for(int i = 0; i<n; i++){
                bitmask = 0;
                for(char ch: words[i].toCharArray()){
                    //add character to bitmask
                    bitmask |= 1 << bitNumber3(ch);
                }
                masks[i] = bitmask;
                lens[i] = words[i].length();
            }
        }

        public int bitNumber3(char ch){
            return (int)ch - (int)'a';
        }
}