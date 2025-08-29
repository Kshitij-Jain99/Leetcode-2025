//127. Word Ladder: https://leetcode.com/problems/word-ladder/description/
import java.util.*;

public class G{
      
    //Appraoch-1: Standard Single direction BFS(Intuition:Level Order Traversal)
    //TC = O(N*M*26 * 1{Set}), SC = O(N)
    /*
     1. Replacing starting word character by character and then check whether the transformed word is present in the wordList.
        If a word is present in the wordList, we try replacing another character in that word by again following similar steps as above, in order to attain the targetWord.
        We do this for all the characters in the startWord and then eventually return the minimum length of transforming the startWord to targetWord.
     2. Implementing BFS to make this efficient.
     3. Set:  In order to mark a word as visited here, we simply delete the word from the hash set. There is no point in visiting someone multiple times during the algorithm. 
     4. pop the first element out of the queue and carry out the BFS traversal
        for each word popped out of the queue, we try to replace every character with ‘a’ - ‘z’, and we get a transformed word and check its presence in set.
     5. If the word is present, we push it in the queue and increase the count of the sequence by 1 and if not, we simply move on to replacing the original character with the next character.
     6. we also need to delete the word from the wordList if it matches with the transformed word to ensure that we do not reach the same point again in the transformation which would only increase our sequence length. 
     7. BFS From begining only.
     */

     class Pair{
        String first;
        int second;
        Pair(String _first, int _second){
            this.first = _first;
            this.second = _second;
        }
     }
     
     public int wordLadderLength(String startWord, String targetWord, String[] wordList){
        Queue<Pair> q = new LinkedList<>();  //Store BFS Transversal(word, steps)
        q.add(new Pair(startWord, 1));  //push start word with 1 length

        Set<String> st = new HashSet<>();  //to store elements present in wordList to carry out search and delete operations in O(1) time
        int len = wordList.length;
        for(int i = 0; i<len; i++) st.add(wordList[i]);
        st.remove(startWord);
        while(!q.isEmpty()){
            String word = q.peek().first;
            int steps = q.peek().second;
            q.remove();

            //return as soon as we reach the targetWord
            if(word.equals(targetWord) == true) return steps;

            //replace each character of the word with every possible character from 'a' to 'z' and check if the new word is present in the set
            for(int i = 0; i<word.length(); i++){
                for(char ch = 'a'; ch <= 'z'; ch++){
                    char replacedCharArray[] = word.toCharArray();
                    replacedCharArray[i] = ch;
                    String replacedWord = new String(replacedCharArray);
                    
                    if(st.contains(replacedWord) == true){
                        st.remove(replacedWord);
                        q.add(new Pair(replacedWord, steps+1));
                    }
                }
            }
        }
        return 0;
     }


    //Approach-2: Bidirectional BFS(Search from both ends): https://leetcode.com/problems/word-ladder/solutions/6987617/video-bidirectional-bfs-solution/
    //TC = O(N*M*26 * 1{Set}), SC = O(N)
    /*
     1. Faster in practice(Shrinks search space, better constant factors,Much faster on large test cases,Meets in the middle, so fewer expansions)
     2. Instead of searching only from the beginWord → endWord, it searches from both ends simultaneously (meet in the middle).
     3. Maintain two sets: beginSet and endSet.
     4. Expand the smaller set at each step (to minimize branching).
        Direction Selection Strategy: When sets have equal size, default to begin_set. Always search from the smaller (or equal) set to maximize efficiency.
     5. Generate all possible one-letter transformations for each word in the current frontier.
        Check for new word:
        a. Check for intersection with end_set
        b. Check for duplicates: Visited or not in wordList
     6. If a generated word exists in the opposite set → path found.
     7. Use a visited set to avoid revisiting.
     */
      public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        int steps = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextSet = new HashSet<>();

            for (String word : beginSet) {
                for (int i = 0; i < word.length(); i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == word.charAt(i)) continue;

                        String newWord = word.substring(0, i) + c + word.substring(i + 1);
                        if (endSet.contains(newWord)) return steps + 1;

                        if (wordSet.contains(newWord) && !visited.contains(newWord)) {
                            visited.add(newWord);
                            nextSet.add(newWord);
                        }
                    }
                }
            }

            beginSet = nextSet;
            steps++;
        }

        return 0;        
    }
}