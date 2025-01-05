//3407. Substring Matching Pattern.
// Approach B: Optimized(BM and KMP internally), built- in fxn
// TC=O(n + m), SC = O(m)
/*
 1. 
 */
public class A2 {
    public boolean hasMatch(String s, String p){
      int index = p.indexOf('*');  //O(m)
      int p1 = s.indexOf(p.substring(0, index)); //O(index) + O(N)
      int p2 = s.indexOf(p.substring(index + 1), p1 + index);
      if(p1 != -1 && p2 != -1) return true;
      return false;
    }
}
