//1780. Check if Number is a Sum of Powers of Three
// Approach A: Math
// TC = O(log(base 3)N), SC = O(1)

public class Mar4_A {
    public boolean checkPowersOfThree(int n) {
        while (n > 0) {
            if (n % 3 == 2) {
                return false;
            }
            n /= 3;
        }
        return true;
    }
}
