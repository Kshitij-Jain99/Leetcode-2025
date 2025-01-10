//916. Word Subsets
//Approach A: Brute force with Precomputation
// TC = O(N.LenOfWords + M.LenOfWords), SC = O(26)
import java.util.*;
public class Jan10_A {
    public List<String> wordSubjects(String[] words1, String[] words2){
        //Precomputing max. freq. of each charcter across all words in words2
        int[] req = new int[26];
        for(String word: words2){
            int[] curr = new int[26];
            for(char c: word.toCharArray()){
                curr[c -'a']++;
                req[c -'a'] = Math.max(req[c -'a'], curr[c -'a']);
            }
        }
        //For each word in words1, compute its character frequency and check if it meets the requirement.
        List<String> ans = new ArrayList<>();
        for(String word: words1){
            int[] curr = new int[26];
            for(char c: word.toCharArray())  curr[c -'a']++;

            boolean isValid = true;
            for(int i =0; i<26; i++){
                if(curr[i] < req[i]){
                    isValid = false;
                    break;
                }
            }
            if(isValid) ans.add(word);
        }
        return ans;
    }
}
