//Implement Pow(x,n) | X raised to the power N
//Approach-1: Brute Force
// TC = O(N), SC = O(1)

public class B1 {
     public static double myPow(double x, long n){
        double ans = 1.0;
        boolean isNegative = n<0; //preserve sign of n 
        n = Math.abs(n);  //to run loop n times

        for(int i = 0; i<n; i++){
            ans = ans*x;
        }
        return isNegative? 1.0/ ans:ans;
    }
}
