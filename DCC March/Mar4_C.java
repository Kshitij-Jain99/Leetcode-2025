//1780. Check if Number is a Sum of Powers of Three
// Approach A: Greedy subtraction
// TC = O(1), SC = O(1)
public class Mar4_C {
    public boolean checkPowersOfThree(int n) {
        for(int i=15; i>=0 & n>0; i--){
            if(Math.pow(3, i) <=n) n -=Math.pow(3,i);
        }
        return n==0;
    }
}
