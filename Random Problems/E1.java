//LC: 205. Isomorphic Strings

public class E1{
     //Approach-1: Character Index mapping
     // TC = O(n), SC = O(1)
/*
 1. Storing idx of characters in both string.
 2. Two strings are isomorphic if the positions of their characters follow the same pattern.
    Instead of directly mapping characters, we can track the last occurrence index of each character in both strings.
 3. While updating index :Arrays are initialized to 0. Using i+1 ensures that the first occurrence of a character is stored as 1, not 0, avoiding confusion with uninitialized values.
    If we store i directly, the first occurrence of a character would be stored as 0.
    But 0 is also the default value for unvisited characters → false mismatches.
 4. Index comparing Ensures that each character in s maps to exactly one character in t and vice versa without explicitly storing mappings.
 5. 
 */
public boolean isIsomorphic(String s, String t){
    int[] indexS = new int[256]; //size 256 to cover all ASCII characters
    int[] indexT = new int[256];
    
    for(int i =0; i<s.length(); i++){
        if(indexS[s.charAt(i)] != indexT[t.charAt(i)]) return false;
        indexS[s.charAt(i)] = i+1;
        indexT[t.charAt(i)] = i+1;
    }
    return true;
  }
}