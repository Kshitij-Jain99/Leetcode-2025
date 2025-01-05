//3407. Substring Matching Pattern
// Approach C: Better than B
// TC=O(n + m), SC = O(m)

/*
 1. Handles the case when there's no '*' in pattern explicitly; 
 2. Checks if the left and right parts don't overlap (xi + x.length() <= yi)
 3.  Better readability
 */

public class A3 {
   
        public boolean hasMatch(String s, String p) {
            int si = p.indexOf('*');
            if(si == -1) return s.equals(p);
    
            String x = p.substring(0,si);  //left of *
            String y = p.substring(si+1);  //right of *
    
            int xi = s.indexOf(x); 
            if(xi == -1) return false;
            
            int yi = s.lastIndexOf(y);
            if(yi == -1) return false;
    
            return xi + x.length() <= yi;
        }
    }    

