//3614. Process String with Special Operations II
public class F1{
 
    //Appraoch-1: Bidirectional String Processing with Length Manipulation
    // TC = O(n), SC = O(1)
    public static char processStr(String s, long k){
        long len = 0;

        for(int i =0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '*'){
                if(len >0) len--;
            }
            else if(c == '#') len *= 2;
            else if(c == '%') continue;
            else len++;
        }
        if( k >= len) return '.';

        for(int i = s.length()-1; i>=0; i--){
            char c = s.charAt(i);
            if( c == '*' ) len++;
            else if(c == '%') k = len-k-1;
            else if(c == '#') {
                len /= 2;
                if( k >= len) k = k-len;
            }
            else{
                len--;
                if(len == k) return c;
            }
        }
        return '.';
    }
}