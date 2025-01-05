//3407. Substring Matching Pattern
// Approach A: Brute force
// TC=O(n*n*(m1 + m2), SC = O(m)-> s1 and s2
/*
 1. '*' can be empty also, only 1 of '*' exist in string.
 2. p = 's1' + '*' + 's2'
 3. No of characters left in string (n-i) >= characters in s1(m1)
 4. No of characters left in string (n-k) >= characters in s2(m2)
 */
public class A1 {
    public boolean hasMatch(String s, String p){
        int n = s.length(), m = p.length();
        String s1 = "", s2 = "";  // s1 = left, s2 = right of '*'
        int i = 0;

        //Extract substring before '*'-> s1
        while(p.charAt(i) != '*'){
            s1 += p.charAt(i);
            i++;
        }
        //now i is '*', so i++ for s2
        i++;

        //Extract substring after '*' -> s2
        while(i < m){
            s2 += p.charAt(i);
            i++;
        }

        int m1 = s1.length(), m2 = s2.length();

        //Iterate over the input string(s) to find a match
        for(i = 0; i<n; i++){
            //Check if s1 matched at the current position
            if(m1 == 0 || (n-i >= m1 && s.substring(i, i+ m1).equals(s1))){
                //If there's no s2, return true since s1 matched
                if(m2 == 0) return true;

                //Check for s2 after s1
                for(int k = i+ m1; k<n; k++){
                    if((n-k >= m2 && s.substring(k, k+m2).equals(s2))){
                        return true;
                    }
                }
            }
        }
        return false;  //no match found
    }

}
