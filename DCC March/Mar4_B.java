//1780. Check if Number is a Sum of Powers of Three
// Approach A: In-Built fxn
// TC = O(log(base 3)N), SC = O(1)

class Mar4_B {
    public boolean checkPowersOfThree(int n) {
        String base3 = Integer.toString(n, 3);
        
        return !base3.contains("2");
    }
}