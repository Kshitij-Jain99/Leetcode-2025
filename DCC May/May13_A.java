//3335. Total Characters in String After Transformations I
// Appraoch-A: Using frequency count in map
// TC = O(N+T*26), SC = O(26) = O(1)
/*
 1. The freq of characters is changing in each iteration, so use Map
 2. But instead of using Map, we can use array of size 26(more efficeint tha Map) as all elements are lowercase alphabets.
 3. frequency problem over a fixed domain (like a-z, 0-9) → Use arrays.
    If domain is large or unknown → Use Map.
 4. If counts can grow fast → Use modulo.
 */
public class May13_A {
    /*
     private final makes it a constant field — its value cannot change once assigned.
     The underscore (_) is just Java syntax sugar added in Java 7+ for readability:1_000_000_007 = 1000000007.
     1000000007 specifically? -> A large prime number: 
             They reduce collisions in hashing algorithms and avoid integer overflow.
             They maintain mathematical properties required for modular inverses and other number-theoretic techniques.
     */
    private final int M = 1_000_000_007;

    public int lengthAfterTransformations(String s, int t) {
        int[] mp = new int[26]; //more cache-friendly and faster

        //initial string freq count - O(N)
        for (char ch : s.toCharArray()) { 
            mp[ch - 'a']++; //converting char to index '-a' : int index = ch - 'a';  
        }

        // Transformation loop - O(T)
        for (int count = 1; count <= t; count++) {
            /* You shouldn't mutate the current frequency array while iterating over
             it if the transformation depends on previous values.
             Need to create new frequency array for each iteration*/ 
            int[] temp = new int[26];

            for (int i = 0; i < 26; i++) {
                char ch = (char) (i + 'a'); //to convert indx to char '+a'
                int freq = mp[i];

                if (ch != 'z') { //this is a branching transformation and must be handled explicitly.
                    temp[(ch + 1) - 'a'] = (temp[(ch + 1) - 'a'] + freq) % M;
                } else {
                    temp['a' - 'a'] = (temp['a' - 'a'] + freq) % M;
                    temp['b' - 'a'] = (temp['b' - 'a'] + freq) % M;
                }
            }
            // Update the frequency array with current iteration's result
            mp = temp;
        }

        int result = 0;
        for (int i = 0; i < 26; i++) {
            result = (result + mp[i]) % M;
        }

        return result;
    }
}
