//2185. Counting Words With a Given Prefix (Modified).
//Approach B: Trie
// TC = O(NK + QK)   , SC = O(N.K)
// If we are given with String prefix[] instead of 1 prefix, then:

class TrieNode {
    int numberOfPrefixTillHere;
    boolean isEnd;
    TrieNode[] links;

    public TrieNode() {
        this.numberOfPrefixTillHere = 0;
        this.isEnd = false;
        this.links = new TrieNode[26];
    }
    public void put(char ch) {
        this.links[ch-'a'] = new TrieNode();
    }
    public TrieNode get(char ch) {
        return this.links[ch-'a'];
    }
    public void setEnd() {
        this.isEnd = true;
    }
    public boolean getEnd() {
        return this.isEnd;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode node = root;

        for(char ch: word.toCharArray()) {
            if(node.get(ch) == null) {
                node.put(ch);
            } 
            
            node = node.get(ch);
            
            //increment the count 'numberOfPrefixTillHere' since a new word is passing via here
            node.numberOfPrefixTillHere++;
        }

        node.setEnd();
    }
    
    public int getPrefixCount(String prefix) {
        TrieNode node = root;

        for(char ch: prefix.toCharArray()) {
            if(node.get(ch) == null) {
                //means this prefix doesn't exist in any inserted word in Trie
                return 0;
            }

            node = node.get(ch);
        }

        return node.numberOfPrefixTillHere;
    }
}

class Solution {
    public int prefixCount(String[] words, String pref) {
        //null checks and base case
        if(words == null || words.length == 0) {
            return 0;
        }

        if(pref == null || pref.length() == 0) {
            return 0;
        }

        Trie trie = new Trie();

        for(String word: words) {
            trie.insert(word);
        }

        return trie.getPrefixCount(pref);
    }
}
