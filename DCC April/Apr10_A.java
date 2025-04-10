//2999. Count the Number of Powerful Integers
//Approach A: Digit DP(Most intuitive)
//TC = O(LogN*2*10)= O(MaxDigits*Tight*ub) = O(limit), SC = O(logN * 2) = O(1)
// String length is at max 15(Constraints -> finish <= 10^15)-> use 16, ub<9 -> use 10
// Code by Aryan Mittal-YT
import java.util.Arrays;

public class Apr10_A {
    Long[][] dp = new Long[20][2];  //[17][2] also works

    //only tight and n are variables
    long solve(String num, String s, int tight, int n, int limit) {
        if (num.length() < s.length()) return 0;
        if (dp[n][tight] != null) return dp[n][tight]; //memo case

        int ub = (tight == 1) ? num.charAt(num.length() - n) - '0' : limit;
        long ans = 0;

        // Safeguard against accessing invalid index in s
        //when 1 element is remaining for idx->n value
        if (n == 1) {
            if (s.length() < n) return 0L;
            int a = s.charAt(s.length() - n) - '0';
            if (a > ub) return 0L;
            return 1L;
        }

        //comparing suffix portion
        if (n <= s.length()) {
            if (tight == 1) {
                if (s.length() < n) return 0L;
                int a = s.charAt(s.length() - n) - '0';
                if (a > ub) return 0L;
                else if (a == ub) {
                    ans += solve(num, s, 1, n - 1, limit);
                    return dp[n][tight] = ans;
                } else {
                    return 1L;
                }
            } else {
                return 1L;
            }
        } 
        // comparing non-suffix (not "s" part of string X)
        else {
            for (int i = 0; i <= ub && i <= limit; i++) {
                int newTight = (tight == 1 && i == ub) ? 1 : 0;
                ans += solve(num, s, newTight, n - 1, limit);
            }
        }

        return dp[n][tight] = ans;
    }

    public long numberOfPowerfulInt(long start, long finish, int limit, String s) {
        String r = Long.toString(finish);
        String l = Long.toString(start - 1);

        // Reset dp for the first solve -> ans(1, finish) ->a
        for (int i = 0; i < 20; i++) {
            Arrays.fill(dp[i], null);
        }
        long a = solve(r, s, 1, r.length(), limit);

        // Reset dp for the second solve -> ans(1, start - 1) -> b
        for (int i = 0; i < 20; i++) {
            Arrays.fill(dp[i], null);
        }
        long b = solve(l, s, 1, l.length(), limit);

        return Math.max(0L, a - b);
    }
}
