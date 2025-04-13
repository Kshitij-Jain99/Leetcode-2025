//3272. Find the Count of Good Integers
// Approach-A: Math + Brute Force (Generate and Test + Combinatorial Counting)
// TC: O(10^(n/2) × n log n), SC:O(10^(n/2) × n)

import java.util.*;
/*
 1. Goal: Count all n-digit numbers whose digits can be rearranged to form:
    A palindrome, Divisible by k, No leading zeros before/after rearrangement
 2. Generate Palindromes:Create all possible n-digit palindromes by mirroring first half
 3. Filter Valid Palindromes: Keep only palindromes divisible by k
 4. Track Unique Digit Sets: Skip duplicates, Sort digits of each palindrome (e.g., "1221" → "1122")
 5. Count Valid Permutations: Count Valid Permutations, Subtract invalid permutations with leading zeros (if any)
 6. Memoization:Cache factorial results to optimize repeated calculations
 7.  Edge Handling: Odd-length palindromes: Middle digit isn't mirrored (e.g., "123" → "121"), Leading zeros: Explicitly excluded in permutation counts
 8. TC: O(10^(n/2) × n log n)
    Palindrome Generation: O(10^(n/2)),
    Divisibility Check: O(1),
    Digit Sorting & Deduplication: O(n log n),
    Permutation Counting: O(n), due to memoization

    SC:O(10^(n/2) × n)
    Palindrome Storage: O(10^(n/2)),
    Hash Set (seenDigitSets): O(10^(n/2)*n),
    Factorial Cache: O(n)
    Frequency Array: O(10)
 */
class Apr12_A {
    // Step 1: Main entry point - counts good integers
    public long countGoodIntegers(int n, int k) {
        return count(n, k);
    }

    // Step 2: Core counting logic
    static long count(long n, long k) {
        long result = 0;
        Set<String> seenDigitSets = new HashSet<>(); // Tracks processed digit sets

        // Step 3: Generate all possible palindromes
        for (long palindrome : generatePalindromes(n)) {
            // Step 4: Check divisibility by k
            if (palindrome % k != 0) continue;

            String palindromeStr = String.valueOf(palindrome);
            // Step 5: Normalize digit order for comparison
            String sortedDigits = sortDigits(palindromeStr);

            // Step 6: Skip duplicate digit sets
            if (seenDigitSets.contains(sortedDigits)) continue;

            seenDigitSets.add(sortedDigits);
            // Step 7: Count valid permutations for this digit set
            result += countValidPermutations(palindromeStr);
        }

        return result;
    }

    // Step 8: Palindrome generation
    static List<Long> generatePalindromes(long n) {
        List<Long> palindromes = new ArrayList<>();
        // Step 9: Calculate half length
        int halfLength = (int) ((n + 1) / 2);
        long start = (long) Math.pow(10, halfLength - 1);
        long end = (long) Math.pow(10, halfLength);

        // Step 10: Generate first halves
        for (long i = start; i < end; i++) {
            String firstHalf = String.valueOf(i);
            String fullPalindrome;

            // Step 11: Construct full palindrome
            if (n % 2 == 0) {
                fullPalindrome = firstHalf + new StringBuilder(firstHalf).reverse().toString();
            } else {
                fullPalindrome = firstHalf + new StringBuilder(firstHalf.substring(0, firstHalf.length() - 1)).reverse().toString();
            }

            palindromes.add(Long.parseLong(fullPalindrome));
        }

        return palindromes;
    }

    // Step 12: Digit sorting utility
    static String sortDigits(String s) {
        char[] digits = s.toCharArray();
        Arrays.sort(digits);
        return new String(digits);
    }

    // Step 13: Permutation counting
    static long countValidPermutations(String s) {
        int[] digitFrequency = new int[10];
        
        // Step 14: Calculate digit frequencies
        for (char c : s.toCharArray()) {
            digitFrequency[c - '0']++;
        }

        // Step 15: Calculate total permutations
        long totalPermutations = factorial(s.length());
        
        // Step 16: Adjust for duplicate digits
        for (int freq : digitFrequency) {
            if (freq > 1) {
                totalPermutations /= factorial(freq);
            }
        }

        // Step 17: Handle leading zero case
        if (digitFrequency[0] == 0) {
            return totalPermutations;
        }

        // Step 18: Calculate invalid permutations (leading zero)
        digitFrequency[0]--;
        long invalidPermutations = factorial(s.length() - 1);
        
        // Step 19: Adjust for duplicates in invalid cases
        for (int freq : digitFrequency) {
            if (freq > 1) {
                invalidPermutations /= factorial(freq);
            }
        }

        // Step 20: Return valid permutations count
        return totalPermutations - invalidPermutations;
    }

    // Step 21: Memoized factorial calculation
    static Map<Integer, Long> factorialCache = new HashMap<>();
    static long factorial(int n) {
        if (n <= 1) return 1;
        if (factorialCache.containsKey(n)) return factorialCache.get(n);
        long result = n * factorial(n - 1);
        factorialCache.put(n, result);
        return result;
    }
}

