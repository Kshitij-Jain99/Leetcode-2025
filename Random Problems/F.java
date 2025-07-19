//3614. Process String with Special Operations II
public class F{
 
    //Appraoch-1: Bidirectional String Processing with Length Manipulation
    // TC = O(n), SC = O(1)
/*
  1. The function processes string length forward to determine feasibility.
  2. Then reverses to walk back how k would have been transformed before each operation.
  3. It simulates an inverse transformation — mapping from k in the final string to a specific character in the original.
 */
    public static char processStr(String s, long k){
        long len = 0;

        //First Pass: Forward Simulation (Compute Length after all operations)
        for(int i =0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '*'){
                if(len >0) len--; 
            }
            else if(c == '#') len *= 2;
            else if(c == '%') continue; // '%' doesn't affect length
            else len++;    // a normal character adds 1 to length
        }
        if( k >= len) return '.';    //k is out of range for the final string

        //Second Pass: Reverse Processing (Find k-th Char Without Building the String)
        for(int i = s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if( c == '*' ) len++;   // Undo previous deletion
            else if(c == '%') k = len-k-1;   // Mirror the index
            else if(c == '#') {
                len /= 2;     //# doubled the string before, so in reverse we halve it.
                if( k >= len) k = k-len;  //If k was in the second half, adjust it to point back to the mirrored position in the original half.
            }
            else{ //For normal characters (non-operations), we reduce len
                len--;
                if(len == k) return c;
            }
        }
        return '.';
    }

    //Appraoch-2: Object-Oriented Lazy String Transformation using Decorator Pattern
    // TC = O(depth){ where depth = number of transformations}, SC = O(n){n nested ProcessedString objects for each character or operation in the input}
/*
Imagine you’re recording the history of changes made to a string, and then later, when you need the k-th character, you go backwards through the history to compute only what’s necessary.
 0. Instead of creating or modifying the actual string on every operation (which can be costly, especially for # that doubles the string), we build a virtual view of the transformed string by wrapping transformations in objects.
 1. For each transformation, we can map character in the transformed string back to character in the original string.
    e.g. s = "abc"     // original string
         s1 = "abcabc" // after duplication
    Any character at index i in s1 maps to index i % s.length() in the original string s: s1[i] == s[i % s.length]
 2. By applying this logic recursively for each transformation, we can trace back the character at index k without building the full final string.
 3. To do that, we can define a set of transformation objects that can encapsulate such logic.
 4. While parsing the input string, wrap the ProcessedString according to the rules.
 5. Check if k is out of the bounds and if so, return '.' Else: get the char from the string
 6. This is implementation with classes and approach-1 is implementation without classes.
 */

   public char processStr2(String s, long k) {
        ProcessedString ps = new EmptyString();
        for (char c : s.toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                ps = new AppendedString(ps, c);
            } else if (c == '*') {
                ps = new ShortenedString(ps);
            } else if (c == '#') {
                ps = new DuplicatedString(ps);
            } else {
                ps = new ReversedString(ps);
            }
        }

        return k < ps.length() ? ps.charAt(k) : '.';
    }

    private interface ProcessedString { //Define a common ProcessedString interface that represents the processed string and defines the operations that we need:
        long length(); // check if index is within the bounds

        char charAt(long i); // get char at index
    }

    private static class EmptyString implements ProcessedString {//Define a class for the initial value
        @Override
        public long length() {
            return 0; // empty string, length is 0
        }

        @Override
        public char charAt(long i) {
            return 0; // empty string, no characters
        }
    }

    private static final class AppendedString implements ProcessedString { //Use the decorator pattern to define the types of processed strings
        private final ProcessedString s;
        private final char c;
        private final long length;

        private AppendedString(ProcessedString s, char c) { // append single character
            this.s = s; // the original string that is processed
            this.c = c; // the appended char
            // since length can't change we can cache it to avoid recalculation
            this.length = s.length() + 1; // since one char is appended to the original string
        }

        @Override
        public long length() {
            return length;
        }

        @Override
        public char charAt(long i) {
            // if it's the last (the appended) character, return it
            if (i == length - 1) {
                return c;
            }
            return s.charAt(i); // otherwise delegate to inner string
        }
    }

    private static final class ShortenedString implements ProcessedString { //remove last character (if present)
        private final ProcessedString s;
        private final long length;

        private ShortenedString(ProcessedString s) {
            this.s = s;
            this.length = Math.max(s.length() - 1, 0);
        }

        @Override
        public long length() {
            return length;
        }

        @Override
        public char charAt(long i) {
            return s.charAt(i);
        }
    }

    private static final class DuplicatedString implements ProcessedString { //duplicate string
        private final ProcessedString s;
        private final long length;

        private DuplicatedString(ProcessedString s) {
            this.s = s;
            this.length = 2 * s.length(); // double the length of the original string
        }

        @Override
        public long length() {
            return length;
        }

        @Override
        public char charAt(long i) {
            // string is repeated, use mod to get index in the original string
            return s.charAt(i % s.length());
        }
    }

    private static final class ReversedString implements ProcessedString { // reverse string
        private final ProcessedString s;
        private final long length;

        private ReversedString(ProcessedString s) {
            this.s = s;
            this.length = s.length(); // same length as the original string
        }

        @Override
        public long length() {
            return length;
        }

        @Override
        public char charAt(long i) {
            // string is reversed, get char counting from the end of the string
            return s.charAt(length - 1 - i);
        }
    } 
}